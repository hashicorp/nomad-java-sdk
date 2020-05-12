package main

import (
	"bytes"
	"errors"
	"fmt"
	"github.com/hashicorp/nomad-java-sdk/generator/java"
	"log"
	"os"
	"reflect"
	"sort"
	"strings"
	"unicode"
)

type Generator struct {
	structs       chan<- reflect.Type
	userDone      chan<- struct{}
	generatorDone <-chan struct{}
}

func NewGenerator(typesNotToGenerate []reflect.Type) *Generator {
	structs := make(chan reflect.Type)
	userDone := make(chan struct{})
	generatorDone := make(chan struct{})
	generator := &Generator{
		structs:       structs,
		userDone:      userDone,
		generatorDone: generatorDone,
	}
	go func() {
		knownTypes := make(map[string]reflect.Type)
		for _, t := range typesNotToGenerate {
			knownTypes[t.Name()] = t
		}
		generating := 0
		doneGenerating := make(chan struct{})
		for userDone != nil || generating > 0 {
			select {
			case t := <-structs:
				if previous, ok := knownTypes[t.Name()]; ok {
					if t != previous && t != nil {
						panic(fmt.Sprintf("%v != %v", t, previous))
					}
				} else {
					generating += 1
					go func() {
						log.Printf("Generating class from %s\n", t)
						err := generator.generateClass(t)
						if err != nil {
							panic(err)
						}
						doneGenerating <- struct{}{}
					}()
					knownTypes[t.Name()] = t
				}
			case <-doneGenerating:
				generating -= 1
			case <-userDone:
				userDone = nil
			}
		}
		close(generatorDone)
	}()
	return generator
}

func (g *Generator) ConsiderType(t reflect.Type) {
	switch t.Kind() {
	case reflect.Bool:
	case reflect.Int:
	case reflect.Int8:
	case reflect.Int16:
	case reflect.Int32:
	case reflect.Int64:
	case reflect.Uint:
	case reflect.Uint8:
	case reflect.Uint16:
	case reflect.Uint32:
	case reflect.Uint64:
	//case reflect.Uintptr:
	case reflect.Float32:
	case reflect.Float64:
	case reflect.Complex64:
	case reflect.Complex128:
	//case reflect.Array:
	case reflect.Chan:
		g.ConsiderType(t.Elem())
	//case reflect.Func:
	case reflect.Interface:
		switch t.String() {
		case "interface {}":
		case "io.ReadCloser":
		case "error":
		default:
			panic("Unknown interface during scan: " + t.String())
		}
	case reflect.Map:
		g.ConsiderType(t.Key())
		g.ConsiderType(t.Elem())
	case reflect.Ptr:
		g.ConsiderType(t.Elem())
	case reflect.Slice:
		g.ConsiderType(t.Elem())
	case reflect.String:
	case reflect.Struct:
		g.structs <- t
	//case reflect.UnsafePointer:
	default:
		panic("Unhandled kind during scan: " + t.Kind().String() + " for " + t.String())
	}
}

func (g *Generator) Close() {
	close(g.userDone)
	<-g.generatorDone
}

func (generator *Generator) generateClass(t reflect.Type) error {
	className := className(t)

	usedTypes := make(map[string]string)
	var usingType = func(name java.TypeName) {
		usedTypes[name.Simple] = name.FullyQualified
	}
	usingType(java.NewTypeName("java.io.IOException"))
	usingType(java.NewTypeName("java.util.List"))

	var collectTypes func(java.JavaType) error
	collectTypes = func(t java.JavaType) error {
		name := t.Name()
		if previous, exists := usedTypes[name.Simple]; exists {
			if name.FullyQualified != previous {
				return errors.New(fmt.Sprintf("Name clash between %s and %s", previous, name.FullyQualified))
			}
		} else {
			usingType(name)
		}
		if rt, ok := t.(java.ReferenceType); ok {
			for _, a := range rt.Arguments {
				if err := collectTypes(a); err != nil {
					return err
				}
			}
		}
		return nil
	}

	numProperties := t.NumField()
	properties := make([]java.BeanProperty, 0, numProperties)
	for i := 0; i < numProperties; i++ {
		if t.Field(i).Name == "WriteMeta" || t.Field(i).Name == "QueryMeta" {
			continue
		}
		if property, ok := generator.javaBeanProperty(className, t.Field(i)); ok {
			properties = append(properties, property)
			collectTypes(property.JavaType)
		}
	}

	out, err := os.Create(className + ".java")
	if err != nil {
		return err
	}

	fmt.Fprint(out, "package com.hashicorp.nomad.apimodel;\n\n")

	fmt.Fprint(out, "import com.fasterxml.jackson.annotation.JsonProperty;\n")
	fmt.Fprint(out, "import com.hashicorp.nomad.javasdk.ApiObject;\n")
	fmt.Fprint(out, "import com.hashicorp.nomad.javasdk.NomadJson;\n\n")

	imports := make([]string, 0, len(usedTypes))
	for s, f := range usedTypes {
		if len(f) > len(s) && !strings.HasPrefix(f, "java.lang.") {
			imports = append(imports, f)
		}
	}
	if len(imports) > 0 {
		sort.Strings(imports)
		for _, f := range imports {
			fmt.Fprintf(out, "import %s;\n", f)
		}
		fmt.Fprintln(out)
	}
	fmt.Fprintln(out, "/**")
	fmt.Fprintln(out, " * This is a generated JavaBean representing a request or response structure.")
	fmt.Fprintln(out, " *")
	fmt.Fprintln(out, " * @see <a href=\"https://www.nomadproject.io/docs/http/index.html\">Nomad HTTP API</a> documentation associated with the endpoint you are using.")
	fmt.Fprintln(out, " */")
	fmt.Fprintln(out, "public final class", className, "extends ApiObject {")
	for _, p := range properties {
		p.DeclareField(out)
	}
	for _, p := range properties {
		p.DeclareMethods(out, className)
	}

	fmt.Fprintf(out, "\n")
	fmt.Fprintf(out, "    @Override\n")
	fmt.Fprintf(out, "    public String toString() {\n")
	fmt.Fprintf(out, "        return NomadJson.serialize(this);\n")
	fmt.Fprintf(out, "    }\n")

	fmt.Fprintf(out, "\n")
	fmt.Fprintf(out, "    public static %s fromJson(String json) throws IOException {\n", className)
	fmt.Fprintf(out, "        return NomadJson.deserialize(json, %s.class);\n", className)
	fmt.Fprintf(out, "    }\n")

	fmt.Fprintf(out, "\n")
	fmt.Fprintf(out, "    public static List<%s> fromJsonArray(String json) throws IOException {\n", className)
	fmt.Fprintf(out, "        return NomadJson.deserializeList(json, %s.class);\n", className)
	fmt.Fprintf(out, "    }\n")

	fmt.Fprintln(out, "}")

	out.Close()
	return nil
}

func (g *Generator) javaBeanProperty(className string, f reflect.StructField) (java.BeanProperty,bool) {
	nameInJson := f.Tag.Get("json")
	if i := strings.IndexByte(nameInJson, ','); i >= 0 {
		nameInJson = nameInJson[0:i]
	}
	if nameInJson == "-" {
		return java.BeanProperty{}, false
	}
	if nameInJson == "" {
		nameInJson = f.Name
	}
	propName := propertyName(nameInJson)
	return java.BeanProperty{
		Name:             propName,
		MethodSuffix:     methodSuffix(nameInJson),
		JavaType:         g.javaType(className, propName, f.Type),
		GetterAnnotation: "@JsonProperty(\"" + nameInJson + "\")",
	}, true
}

func className(t reflect.Type) string {
	return javaName(t.Name(), true)
}

// To meet JavaBean conventions, lowercase the first letter of the name
// and lowercase the non-first letters of multi-letter acronyms
func propertyName(nameInJson string) string {
	raw := javaName(nameInJson, false)
	if java.IsTypeName(raw) {
		raw += "Val"
	}
	return raw
}

// methodSuffix is prefixed by get- and set- in the getter and the setter
// it is therefore less restrictive than propertyName
func methodSuffix(nameInJson string) string {
	return javaName(nameInJson, true)
}

func javaName(nameInJson string, firstCharacterUppercase bool) string {
	jsonRunes := []rune(nameInJson)
	buffer := bytes.NewBuffer(make([]byte, 0, len(nameInJson)))
	var previousWasUpper bool
	for i, ch := range jsonRunes {
		isUpper := unicode.IsUpper(ch)
		if i == 0 {
			if firstCharacterUppercase && !isUpper {
				ch = unicode.ToUpper(ch)
			} else if !firstCharacterUppercase && isUpper {
				ch = unicode.ToLower(ch)
			}
		} else if isUpper && (previousWasUpper && !(i+1 < len(jsonRunes) && unicode.IsLower(jsonRunes[i+1]))) {
			ch = unicode.ToLower(ch)
		}
		buffer.WriteRune(ch)
		previousWasUpper = isUpper
	}
	variableName := buffer.String()
	if java.IsKeyword(variableName) {
		return nameInJson
	} else {
		return variableName
	}
}

func (generator *Generator) javaType(class, property string, t reflect.Type) java.JavaType {
	if typ, ok := typeHack(class, property); ok {
		return typ
	}

	if t.PkgPath() == "" {
		switch t.Kind() {
		case reflect.Bool:
			return java.PrimitiveBoolean
		case reflect.Int:
			return java.PrimitiveInt
		case reflect.Int8:
			return java.PrimitiveShort // no signed 8-bit type in Java, use wider type
		case reflect.Int16:
			return java.PrimitiveShort
		case reflect.Int32:
			return java.PrimitiveInt
		case reflect.Int64:
			return java.PrimitiveLong
		case reflect.Uint:
			return java.PrimitiveLong // the only unsigned type in Java is byte, use wider type
		case reflect.Uint8:
			return java.PrimitiveByte
		case reflect.Uint16:
			return java.PrimitiveInt // the only unsigned type in Java is byte, use wider type
		case reflect.Uint32:
			return java.PrimitiveLong // the only unsigned type in Java is byte, use wider type
		case reflect.Uint64:
			// the only unsigned type in Java is byte, use wider type
			return java.BigInteger
		//case reflect.Uintptr:
		case reflect.Float32:
			return java.PrimitiveFloat
		case reflect.Float64:
			return java.PrimitiveDouble
		//case reflect.Complex64:
		//case reflect.Complex128:
		//case reflect.Array:
		//case reflect.Chan:
		//case reflect.Func:
		case reflect.Interface:
			switch t.String() {
			case "interface {}":
				return java.Object
			default:
				panic("Unknown interface to convert to Java" + t.String())
			}
		case reflect.Map:
			return java.NewMapType(
				generator.javaType(class, property, t.Key()),
				generator.javaType(class, property, t.Elem()))
		case reflect.Ptr:
			switch t.Elem().Kind() {
			case reflect.Bool:
				return java.Boolean
			case reflect.Int:
				return java.Integer
			case reflect.Int8:
				// probably a weight [-100,100], use wider type
				return java.PrimitiveShort
			case reflect.Float64:
				return java.PrimitiveDouble
			case reflect.Int64:
				return java.Long
			case reflect.String:
				return java.String
			case reflect.Struct:
				return generator.javaType(class, property, t.Elem())
			case reflect.Uint64:
				// the only unsigned type in Java is byte, use wider type
				return java.BigInteger
			default:
				panic("Pointer to " + t.Elem().Kind().String())
			}
		case reflect.Slice:
			if t.Elem().Kind() == reflect.Uint8 {
				return java.NewReferenceType("byte[]")
			} else {
				return java.NewListType(generator.javaType(class, property, t.Elem()))
			}
		case reflect.String:
			return java.String
		//case reflect.Struct:
		//case reflect.UnsafePointer:
		default:
			panic("Unknown kind " + t.Kind().String() + " for " + t.String())
		}
	}

	switch t.String() {
	case "time.Duration":
		return java.PrimitiveLong
	case "time.Time":
		return java.Date
	default:
		switch t.Kind() {
		case reflect.Struct:
			generator.structs <- t
			return java.NewReferenceType(className(t))
		case reflect.String: // This probably indicates an enumeration type; unfortunately we can't capture the values with reflection :(
			return java.String
		case reflect.Int:
			return java.Integer
		case reflect.Map:
			return java.NewMapType(
				generator.javaType(class, property, t.Key()),
				generator.javaType(class, property, t.Elem()))
		default:
			panic("Unknown kind " + t.Kind().String() + " for " + t.String() + " in package " + t.PkgPath())
		}
	}
}

func typeHack(className, propertyName string) (java.JavaType, bool) {
	// these are timestamps from Consul's autopilot code, which encode times differently than the rest of Nomad's API
	if className == "ServerHealth" && propertyName == "lastContact" {
		return java.String, true
	} else if className == "AutopilotConfiguration" {
		if propertyName == "lastContactThreshold" || propertyName == "serverStabilizationTime" {
			return java.String, true
		}
	}

	return nil, false
}
