package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ConsulSidecarService extends ApiObject {
    private String port;
    private ConsulProxy proxy;

    @JsonProperty("Port")
    public String getPort() {
        return port;
    }

    public ConsulSidecarService setPort(String port) {
        this.port = port;
        return this;
    }

    @JsonProperty("Proxy")
    public ConsulProxy getProxy() {
        return proxy;
    }

    public ConsulSidecarService setProxy(ConsulProxy proxy) {
        this.proxy = proxy;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulSidecarService fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulSidecarService.class);
    }

    public static List<ConsulSidecarService> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulSidecarService.class);
    }
}
