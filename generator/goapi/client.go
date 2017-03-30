package goapi

import (
	"github.com/hashicorp/nomad/api"
	"reflect"
)

type EndpointGroup struct {
	t reflect.Type
}

func (group *EndpointGroup) VisitReturnTypes(visit func(reflect.Type)) {
	for i := 0; i < group.t.NumMethod(); i++ {
		m := group.t.Method(i)
		for ti := 0; ti < m.Type.NumOut(); ti++ {
			tt := m.Type.Out(ti)
			visit(tt)
		}
	}
}

func EndpointGroups() <-chan EndpointGroup {
	groups := make(chan EndpointGroup)
	go func() {
		goClient := reflect.ValueOf(&api.Client{})
		for i := 0; i < goClient.NumMethod(); i++ {
			m := goClient.Method(i).Type()
			if m.NumIn() == 0 && m.NumOut() == 1 {
				groups <- EndpointGroup{m.Out(0)}
			}
		}
		close(groups)
	}()
	return groups
}
