Java SDK Class Generator
========================

This generator code was thrown together to quickly generate classes for the
Nomad Java SDK. It uses Golang reflection to scan interfaces in Nomad's `api`
and generates Java classes from the Go structs it finds.


Running
-------

If both the nomad and nomad-java-sdk repos are in /github.com/hashicorp under
your `GOPATH`, you can run the generator by running `./scripts/generate.sh`
in the root directory of this repo.
