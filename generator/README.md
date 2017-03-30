Java SDK Class Generator
========================

This generator code was thrown together to quickly generate classes for the
Nomad Java SDK. It uses Golang reflection to scan interfaces in Nomad's `api`
and generates Java classes from the Go structs it finds.


Running
-------

If both the nomad and nomad-java-sdk repos are in /github.com/hashicorp under
your `GOPATH`, you can run the generator from the root directory of the
nomad-java-sdk repo with:

```.sh
go run generator/*.go src/main/java/com/hashicorp/nomad/apimodel/
```
