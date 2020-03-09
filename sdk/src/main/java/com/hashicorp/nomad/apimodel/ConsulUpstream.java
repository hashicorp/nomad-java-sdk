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
public final class ConsulUpstream extends ApiObject {
    private String destinationName;
    private int localBindPort;

    @JsonProperty("DestinationName")
    public String getDestinationName() {
        return destinationName;
    }

    public ConsulUpstream setDestinationName(String destinationName) {
        this.destinationName = destinationName;
        return this;
    }

    @JsonProperty("LocalBindPort")
    public int getLocalBindPort() {
        return localBindPort;
    }

    public ConsulUpstream setLocalBindPort(int localBindPort) {
        this.localBindPort = localBindPort;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulUpstream fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulUpstream.class);
    }

    public static List<ConsulUpstream> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulUpstream.class);
    }
}
