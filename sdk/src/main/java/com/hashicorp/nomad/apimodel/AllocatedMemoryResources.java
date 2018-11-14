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
public final class AllocatedMemoryResources extends ApiObject {
    private long memoryMb;

    @JsonProperty("MemoryMB")
    public long getMemoryMb() {
        return memoryMb;
    }

    public AllocatedMemoryResources setMemoryMb(long memoryMb) {
        this.memoryMb = memoryMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocatedMemoryResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocatedMemoryResources.class);
    }

    public static List<AllocatedMemoryResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocatedMemoryResources.class);
    }
}
