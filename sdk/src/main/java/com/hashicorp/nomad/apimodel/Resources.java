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
public final class Resources extends ApiObject {
    private Integer cpu;
    private Integer memoryMb;
    private Integer diskMb;
    private Integer iops;
    private List<NetworkResource> networks;
    private List<RequestedDevice> devices;

    @JsonProperty("CPU")
    public Integer getCpu() {
        return cpu;
    }

    public Resources setCpu(Integer cpu) {
        this.cpu = cpu;
        return this;
    }

    @JsonProperty("MemoryMB")
    public Integer getMemoryMb() {
        return memoryMb;
    }

    public Resources setMemoryMb(Integer memoryMb) {
        this.memoryMb = memoryMb;
        return this;
    }

    @JsonProperty("DiskMB")
    public Integer getDiskMb() {
        return diskMb;
    }

    public Resources setDiskMb(Integer diskMb) {
        this.diskMb = diskMb;
        return this;
    }

    @JsonProperty("IOPS")
    public Integer getIops() {
        return iops;
    }

    public Resources setIops(Integer iops) {
        this.iops = iops;
        return this;
    }

    @JsonProperty("Networks")
    public List<NetworkResource> getNetworks() {
        return networks;
    }

    public Resources setNetworks(List<NetworkResource> networks) {
        this.networks = networks;
        return this;
    }

    public Resources addNetworks(NetworkResource... networks) {
        if (this.networks == null)
            this.networks = new java.util.ArrayList<>();
        for (NetworkResource item : networks)
            this.networks.add(item);
        return this;
    }

    @JsonProperty("Devices")
    public List<RequestedDevice> getDevices() {
        return devices;
    }

    public Resources setDevices(List<RequestedDevice> devices) {
        this.devices = devices;
        return this;
    }

    public Resources addDevices(RequestedDevice... devices) {
        if (this.devices == null)
            this.devices = new java.util.ArrayList<>();
        for (RequestedDevice item : devices)
            this.devices.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Resources fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Resources.class);
    }

    public static List<Resources> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Resources.class);
    }
}
