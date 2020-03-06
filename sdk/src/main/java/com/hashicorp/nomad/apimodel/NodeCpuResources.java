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
public final class NodeCpuResources extends ApiObject {
    private long cpuShares;

    @JsonProperty("CpuShares")
    public long getCpuShares() {
        return cpuShares;
    }

    public NodeCpuResources setCpuShares(long cpuShares) {
        this.cpuShares = cpuShares;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeCpuResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeCpuResources.class);
    }

    public static List<NodeCpuResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeCpuResources.class);
    }
}
