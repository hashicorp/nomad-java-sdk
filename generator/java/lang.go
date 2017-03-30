package java

func IsKeyword(s string) bool {
	switch s {
	case "new":
		return true
	case "public":
		return true
	default:
		return false
	}
}
