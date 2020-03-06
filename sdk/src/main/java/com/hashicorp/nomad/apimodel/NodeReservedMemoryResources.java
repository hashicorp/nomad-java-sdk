package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class NodeReservedMemoryResources extends ApiObject {
    private BigInteger memoryMb;

    @JsonProperty("MemoryMB")
    public BigInteger getMemoryMb() {
        return memoryMb;
    }

    public NodeReservedMemoryResources setMemoryMb(BigInteger memoryMb) {
        this.memoryMb = memoryMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeReservedMemoryResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeReservedMemoryResources.class);
    }

    public static List<NodeReservedMemoryResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeReservedMemoryResources.class);
    }
}
