package main

import (
	"github.com/hashicorp/nomad-java-sdk/generator/goapi"
	"github.com/hashicorp/nomad/api"
	"log"
	"os"
	"reflect"
)

func main() {
	if err := os.Chdir(os.Args[1]); err != nil {
		log.Fatal("Not a directory:", os.Args[1])
	}

	typesNotToGenerate := []reflect.Type{
		reflect.TypeOf(api.QueryMeta{}),
		reflect.TypeOf(api.WriteMeta{}),
	}

	generator := NewGenerator(typesNotToGenerate)
	for group := range goapi.EndpointGroups() {
		group.VisitReturnTypes(func(t reflect.Type) {
			generator.ConsiderType(t)
		})
	}
	generator.Close()
}
