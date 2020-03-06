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
public final class NodeResources extends ApiObject {
    private NodeCpuResources cpu;
    private NodeMemoryResources memory;
    private NodeDiskResources disk;
    private List<NetworkResource> networks;
    private List<NodeDeviceResource> devices;

    @JsonProperty("Cpu")
    public NodeCpuResources getCpu() {
        return cpu;
    }

    public NodeResources setCpu(NodeCpuResources cpu) {
        this.cpu = cpu;
        return this;
    }

    @JsonProperty("Memory")
    public NodeMemoryResources getMemory() {
        return memory;
    }

    public NodeResources setMemory(NodeMemoryResources memory) {
        this.memory = memory;
        return this;
    }

    @JsonProperty("Disk")
    public NodeDiskResources getDisk() {
        return disk;
    }

    public NodeResources setDisk(NodeDiskResources disk) {
        this.disk = disk;
        return this;
    }

    @JsonProperty("Networks")
    public List<NetworkResource> getNetworks() {
        return networks;
    }

    public NodeResources setNetworks(List<NetworkResource> networks) {
        this.networks = networks;
        return this;
    }

    public NodeResources addNetworks(NetworkResource... networks) {
        if (this.networks == null)
            this.networks = new java.util.ArrayList<>();
        for (NetworkResource item : networks)
            this.networks.add(item);
        return this;
    }

    @JsonProperty("Devices")
    public List<NodeDeviceResource> getDevices() {
        return devices;
    }

    public NodeResources setDevices(List<NodeDeviceResource> devices) {
        this.devices = devices;
        return this;
    }

    public NodeResources addDevices(NodeDeviceResource... devices) {
        if (this.devices == null)
            this.devices = new java.util.ArrayList<>();
        for (NodeDeviceResource item : devices)
            this.devices.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeResources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeResources.class);
    }

    public static List<NodeResources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeResources.class);
    }
}
