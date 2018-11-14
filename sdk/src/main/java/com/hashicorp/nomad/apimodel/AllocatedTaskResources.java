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
public final class AllocatedTaskResources extends ApiObject {
    private AllocatedCpuResources cpu;
    private AllocatedMemoryResources memory;
    private List<NetworkResource> networks;

    @JsonProperty("Cpu")
    public AllocatedCpuResources getCpu() {
        return cpu;
    }

    public AllocatedTaskResources setCpu(AllocatedCpuResources cpu) {
        this.cpu = cpu;
        return this;
    }

    @JsonProperty("Memory")
    public AllocatedMemoryResources getMemory() {
        return memory;
    }

    public AllocatedTaskResources setMemory(AllocatedMemoryResources memory) {
        this.memory = memory;
        return this;
    }

    @JsonProperty("Networks")
    public List<NetworkResource> getNetworks() {
        return networks;
    }

    public AllocatedTaskResources setNetworks(List<NetworkResource> networks) {
        this.networks = networks;
        return this;
    }

    public AllocatedTaskResources addNetworks(NetworkResource... networks) {
        if (this.networks == null)
            this.networks = new java.util.ArrayList<>();
        for (NetworkResource item : networks)
            this.networks.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocatedTaskResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocatedTaskResources.class);
    }

    public static List<AllocatedTaskResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocatedTaskResources.class);
    }
}
