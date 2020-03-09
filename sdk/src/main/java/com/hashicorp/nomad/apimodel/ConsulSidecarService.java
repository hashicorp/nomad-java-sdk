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
    private List<String> tags;
    private String port;
    private ConsulProxy proxy;

    @JsonProperty("Tags")
    public List<String> getTags() {
        return tags;
    }

    public ConsulSidecarService setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public ConsulSidecarService addTags(String... tags) {
        if (this.tags == null)
            this.tags = new java.util.ArrayList<>();
        for (String item : tags)
            this.tags.add(item);
        return this;
    }

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
