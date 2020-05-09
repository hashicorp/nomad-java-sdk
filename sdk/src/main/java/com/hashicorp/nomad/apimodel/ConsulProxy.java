package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ConsulProxy extends ApiObject {
    private String localServiceAddress;
    private int localServicePort;
    private ConsulExposeConfig exposeConfig;
    private List<ConsulUpstream> upstreams;
    private Map<String, Object> config;

    @JsonProperty("LocalServiceAddress")
    public String getLocalServiceAddress() {
        return localServiceAddress;
    }

    public ConsulProxy setLocalServiceAddress(String localServiceAddress) {
        this.localServiceAddress = localServiceAddress;
        return this;
    }

    @JsonProperty("LocalServicePort")
    public int getLocalServicePort() {
        return localServicePort;
    }

    public ConsulProxy setLocalServicePort(int localServicePort) {
        this.localServicePort = localServicePort;
        return this;
    }

    @JsonProperty("ExposeConfig")
    public ConsulExposeConfig getExposeConfig() {
        return exposeConfig;
    }

    public ConsulProxy setExposeConfig(ConsulExposeConfig exposeConfig) {
        this.exposeConfig = exposeConfig;
        return this;
    }

    @JsonProperty("Upstreams")
    public List<ConsulUpstream> getUpstreams() {
        return upstreams;
    }

    public ConsulProxy setUpstreams(List<ConsulUpstream> upstreams) {
        this.upstreams = upstreams;
        return this;
    }

    public ConsulProxy addUpstreams(ConsulUpstream... upstreams) {
        if (this.upstreams == null)
            this.upstreams = new java.util.ArrayList<>();
        for (ConsulUpstream item : upstreams)
            this.upstreams.add(item);
        return this;
    }

    @JsonProperty("Config")
    public Map<String, Object> getConfig() {
        return config;
    }

    public ConsulProxy setConfig(Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public ConsulProxy addConfig(String key, Object value) {
        if (this.config == null)
            this.config = new java.util.HashMap<>();
        this.config.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulProxy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulProxy.class);
    }

    public static List<ConsulProxy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulProxy.class);
    }
}
