package java

import (
	"fmt"
	"io"
	"strings"
)

type BeanProperty struct {
	Name             string
	JavaType         JavaType
	GetterAnnotation string
}

func (p *BeanProperty) methodSuffix() string {
	return strings.ToUpper(p.Name[0:1]) + p.Name[1:]
}

func (p *BeanProperty) DeclareField(out io.Writer) error {
	_, err := fmt.Fprintf(out, "    private %s %s;\n", p.JavaType, p.Name)
	return err
}

func (p *BeanProperty) DeclareGetter(out io.Writer) error {
	fmt.Fprintf(out, "    public %s get%s() {\n", p.JavaType, p.methodSuffix())
	fmt.Fprintf(out, "        return %s;\n", p.Name)
	fmt.Fprintf(out, "    }\n")
	return nil
}

func (p *BeanProperty) DeclareSetter(out io.Writer, className string) error {
	fmt.Fprintf(out, "    public %s set%s(%s %s) {\n", className, p.methodSuffix(), p.JavaType, p.Name)
	fmt.Fprintf(out, "        this.%s = %s;\n", p.Name, p.Name)
	fmt.Fprintf(out, "        return this;\n")
	fmt.Fprintf(out, "    }\n")
	return nil
}

func (p *BeanProperty) declareListAddMethod(out io.Writer, className string, elementType JavaType) error {
	fmt.Fprintf(out, "    public %s add%s(%s... %s) {\n", className, p.methodSuffix(), elementType, p.Name)
	fmt.Fprintf(out, "        if (this.%s == null)\n", p.Name)
	fmt.Fprintf(out, "            this.%s = new java.util.ArrayList<>();\n", p.Name)
	fmt.Fprintf(out, "        for (%s item : %s)\n", elementType, p.Name)
	fmt.Fprintf(out, "            this.%s.add(item);\n", p.Name)
	fmt.Fprintf(out, "        return this;\n")
	fmt.Fprintf(out, "    }\n")
	return nil
}

func (p *BeanProperty) declareMapAddMethod(out io.Writer, className string, keyType JavaType, valueType JavaType) error {
	fmt.Fprintf(out, "    public %s add%s(%s key, %s value) {\n", className, p.methodSuffix(), keyType, valueType)
	fmt.Fprintf(out, "        if (this.%s == null)\n", p.Name)
	fmt.Fprintf(out, "            this.%s = new java.util.HashMap<>();\n", p.Name)
	fmt.Fprintf(out, "        this.%s.put(key, value);\n", p.Name)
	fmt.Fprintf(out, "        return this;\n")
	fmt.Fprintf(out, "    }\n")
	return nil
}

func (p *BeanProperty) DeclareMethods(out io.Writer, className string) error {
	out.Write([]byte("\n"))
	fmt.Fprintf(out, "    %s\n", p.GetterAnnotation)
	p.DeclareGetter(out)
	out.Write([]byte("\n"))
	p.DeclareSetter(out, className)
	switch p.JavaType.(type) {
	case ReferenceType:
		ref := p.JavaType.(ReferenceType)
		switch ref.name.FullyQualified {
		case listFullyQualifiedName:
			out.Write([]byte("\n"))
			p.declareListAddMethod(out, className, ref.Arguments[0])
		case mapFullyQualifiedName:
			out.Write([]byte("\n"))
			p.declareMapAddMethod(out, className, ref.Arguments[0], ref.Arguments[1])
		}
	}
	return nil
}
