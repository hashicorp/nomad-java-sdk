Nomad Java SDK [![Build Status](https://travis-ci.org/hashicorp/nomad-java-sdk.svg?branch=master)](https://travis-ci.org/hashicorp/nomad-java-sdk) [![Maven Central](https://img.shields.io/maven-central/v/com.hashicorp.nomad/nomad-sdk.svg)](https://mvnrepository.com/artifact/com.hashicorp.nomad/nomad-sdk)
==============

A Java SDK for interacting with [HashiCorp's Nomad] through its [HTTP API].

[HashiCorp's Nomad]: https://www.nomadproject.io/
[HTTP API]: https://www.nomadproject.io/api/index.html

As of v0.8.x, this SDK requires at least a Java 8 runtime.

As of version 0.6.3, the Nomad Java API version will match the nomad version
it was built to work with.


Using
-----

Start by building a `NomadApiConfiguration` and creating an
`NomadApiClient` with it.

```.java
NomadApiConfiguration config =
        new NomadApiConfiguration.Builder()
                .setAddress("http://192.168.100.100:4646")
                .build();

NomadApiClient apiClient = new NomadApiClient(config);
```

Methods are grouped into into API classes according to their function,
and these APIs can be accessed from the client. For example, to list the jobs
running on the cluster, use the `list` method on the `JobsApi`:

```.java
JobsApi jobsApi = apiClient.getJobsApi()

ServerQueryResponse<List<JobListStub>> responseFuture = jobsApi.list();
```

The result is a `ServerQueryResponse`. The API has a few
different response types, depending on the type of query. The response
classes have some methods for getting metadata about the response,
and a `getValue()` method that returns the response value.
The generic type parameter in the response class indicates the response
value type, so in this case, `getValue` will return a list of
`JobListStub`s.

### Request Options

Endpoints that interact with server APIs accept `QueryOptions` or
`WriteOptions`, which let you specify additional options when making a
request.

`QueryOptions` supports [stale queries], and [blocking queries]. It also
supports repeated performing blocking queries until a condition is met.

[cross-region requests]: https://www.nomadproject.io/docs/http/index.html#cross-region-requests
[blocking queries]: https://www.nomadproject.io/docs/http/index.html#blocking-queries
[stale queries]: https://www.nomadproject.io/docs/http/index.html#consistency-modes

#### Regions

Both `QueryOptions` or `WriteOptions` allow you to specify a region to
support [cross-region requests]. Requests sent to a Nomad server are
bound to a particular region; if no region is specified, the server
assumes the request is bound for its own region. You can specify an
explicit region per-request using the options, and you can specify a
client-wide default in the client configuration. You can also rely on
the default behaviour.

### Note on Terminology

Nomad *agents* can operate as *Nomad servers* which perform scheduling,
or *Nomad clients* which connect to servers and run the task groups they
are assigned, or both (see the [Nomad glossary]). Regardless of their client
and/or server roles in the Nomad cluster, all agents have an embedded
HTTP server that serves the Nomad [HTTP API]. This Java API makes use of an
*HTTP client* to connect to that API, and is thus a Nomad HTTP *API client*.

[Nomad glossary]: https://www.nomadproject.io/docs/internals/architecture.html#glossary

So be aware that there are two conflicting meanings of "client" in
scope. `NomadApiClient` is the main API client class, and has nothing to
do with the *Nomad client* concept. The `ClientApi` class, on the other
hand, is the API for interacting with Nomad client agents.


Building
--------

The SDK is built with Maven. A JAR can be build with `mvn package`,
provided an appropriate Nomad executable is available for tests
as described below.

### Testing

The tests make use of Nomad's
[`mock_driver`](https://github.com/hashicorp/nomad/blob/master/drivers/mock/driver.go),
a driver for test purposes that isn't built into Nomad by default.
To build Nomad with `mock_driver` support, you will need Go, a properly
configured `GOPATH`, and the [Nomad source], which you can clone with
git or with `go get`, e.g.:

```.sh
go get github.com/hashicorp/nomad
```

[Nomad source]: https://github.com/hashicorp/nomad

You will then need to pass the `nomad_test` flag passed to the Go
compiler when building Nomad, e.g. the follow will put a Nomad
executable in `$GOPATH/bin/`:

```.sh
go install -tags nomad_test github.com/hashicorp/nomad
```

You can then run the tests with this executable on the `PATH`, e.g.:

```.sh
PATH="$GOPATH/bin:$PATH" mvn test
```

Tests for Nomad Enterprise features don't run by default, but can be
enabled by setting the `NomadEnterprise` property when running the tests
(note that this requires an Enterprise `nomad` executable with
`mock_driver` support:

```.sh
mvn test -DNomadEnterprise
```
