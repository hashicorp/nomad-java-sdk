package java

import (
	"fmt"
	"strings"
)

type TypeName struct {
	FullyQualified string
	Simple         string
}

func NewTypeName(fullyQualified string) TypeName {
	return TypeName{
		FullyQualified: fullyQualified,
		Simple:         fullyQualified[strings.LastIndex(fullyQualified, ".")+1:],
	}
}

type JavaType interface {
	Name() TypeName
	AsReferenceType() ReferenceType
	fmt.Formatter
}

type ReferenceType struct {
	name      TypeName
	Arguments []JavaType
}

func NewReferenceType(fullyQualifiedName string, args ...JavaType) ReferenceType {
	return ReferenceType{NewTypeName(fullyQualifiedName), args}
}

func (t ReferenceType) Name() TypeName                 { return t.name }
func (t ReferenceType) AsReferenceType() ReferenceType { return t }

func (t ReferenceType) Format(f fmt.State, c rune) {
	fmt.Fprint(f, t.name.Simple)
	if len(t.Arguments) > 0 {
		fmt.Fprint(f, "<")
		for i, a := range t.Arguments {
			if i != 0 {
				fmt.Fprint(f, ", ")
			}
			a.AsReferenceType().Format(f, c)
		}
		fmt.Fprint(f, ">")
	}
}

type PrimitiveType struct {
	name  string
	Boxed ReferenceType
}

func NewPrimitiveType(name string, simpleBoxedClassName string) PrimitiveType {
	return PrimitiveType{name, NewReferenceType("java.lang." + simpleBoxedClassName)}
}

func (t PrimitiveType) Name() TypeName                 { return TypeName{t.name, t.name} }
func (t PrimitiveType) AsReferenceType() ReferenceType { return t.Boxed }

func (t PrimitiveType) Format(f fmt.State, c rune) {
	fmt.Fprint(f, t.name)
}
