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
public final class NodeReservedResources extends ApiObject {
    private NodeReservedCpuResources cpu;
    private NodeReservedMemoryResources memory;
    private NodeReservedDiskResources disk;
    private NodeReservedNetworkResources networks;

    @JsonProperty("Cpu")
    public NodeReservedCpuResources getCpu() {
        return cpu;
    }

    public NodeReservedResources setCpu(NodeReservedCpuResources cpu) {
        this.cpu = cpu;
        return this;
    }

    @JsonProperty("Memory")
    public NodeReservedMemoryResources getMemory() {
        return memory;
    }

    public NodeReservedResources setMemory(NodeReservedMemoryResources memory) {
        this.memory = memory;
        return this;
    }

    @JsonProperty("Disk")
    public NodeReservedDiskResources getDisk() {
        return disk;
    }

    public NodeReservedResources setDisk(NodeReservedDiskResources disk) {
        this.disk = disk;
        return this;
    }

    @JsonProperty("Networks")
    public NodeReservedNetworkResources getNetworks() {
        return networks;
    }

    public NodeReservedResources setNetworks(NodeReservedNetworkResources networks) {
        this.networks = networks;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeReservedResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeReservedResources.class);
    }

    public static List<NodeReservedResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeReservedResources.class);
    }
}
