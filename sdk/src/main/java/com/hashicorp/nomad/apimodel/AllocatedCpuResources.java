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
public final class AllocatedCpuResources extends ApiObject {
    private long cpuShares;

    @JsonProperty("CpuShares")
    public long getCpuShares() {
        return cpuShares;
    }

    public AllocatedCpuResources setCpuShares(long cpuShares) {
        this.cpuShares = cpuShares;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocatedCpuResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocatedCpuResources.class);
    }

    public static List<AllocatedCpuResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocatedCpuResources.class);
    }
}
