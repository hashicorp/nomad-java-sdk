package java

const (
	listFullyQualifiedName = "java.util.List"
	mapFullyQualifiedName  = "java.util.Map"
)

var (
	PrimitiveBoolean = NewPrimitiveType("boolean", "Boolean")
	PrimitiveByte    = NewPrimitiveType("byte", "Byte")
	PrimitiveShort   = NewPrimitiveType("short", "Short")
	PrimitiveInt     = NewPrimitiveType("int", "Integer")
	PrimitiveLong    = NewPrimitiveType("long", "Long")
	PrimitiveFloat   = NewPrimitiveType("float", "Float")
	PrimitiveDouble  = NewPrimitiveType("double", "Double")

	Boolean    = NewReferenceType("java.lang.Boolean")
	Integer    = NewReferenceType("java.lang.Integer")
	Long       = NewReferenceType("java.lang.Long")
	Object     = NewReferenceType("java.lang.Object")
	String     = NewReferenceType("java.lang.String")
	BigInteger = NewReferenceType("java.math.BigInteger")
	Date       = NewReferenceType("java.util.Date")
)

func NewListType(element JavaType) ReferenceType {
	return NewReferenceType(listFullyQualifiedName, element)
}

func NewMapType(key JavaType, value JavaType) ReferenceType {
	return NewReferenceType(mapFullyQualifiedName, key, value)
}
