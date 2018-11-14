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
public final class NodeReservedCpuResources extends ApiObject {
    private BigInteger cpuShares;

    @JsonProperty("CpuShares")
    public BigInteger getCpuShares() {
        return cpuShares;
    }

    public NodeReservedCpuResources setCpuShares(BigInteger cpuShares) {
        this.cpuShares = cpuShares;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeReservedCpuResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeReservedCpuResources.class);
    }

    public static List<NodeReservedCpuResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeReservedCpuResources.class);
    }
}
