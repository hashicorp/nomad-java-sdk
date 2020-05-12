package java

func IsKeyword(s string) bool {
	switch s {
	case "new":
		return true
	case "public":
		return true
	case "native":
		return true
	default:
		return false
	}
}

func IsTypeName(s string) bool {
	switch s {
	case "int":
		return true
	case "string":
		return true
	case "float":
		return true
	case "bool":
		return true
	default:
		return false
	}
}
