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
public final class ResourceUsage extends ApiObject {
    private MemoryStats memoryStats;
    private CpuStats cpuStats;
    private List<DeviceGroupStats> deviceStats;

    @JsonProperty("MemoryStats")
    public MemoryStats getMemoryStats() {
        return memoryStats;
    }

    public ResourceUsage setMemoryStats(MemoryStats memoryStats) {
        this.memoryStats = memoryStats;
        return this;
    }

    @JsonProperty("CpuStats")
    public CpuStats getCpuStats() {
        return cpuStats;
    }

    public ResourceUsage setCpuStats(CpuStats cpuStats) {
        this.cpuStats = cpuStats;
        return this;
    }

    @JsonProperty("DeviceStats")
    public List<DeviceGroupStats> getDeviceStats() {
        return deviceStats;
    }

    public ResourceUsage setDeviceStats(List<DeviceGroupStats> deviceStats) {
        this.deviceStats = deviceStats;
        return this;
    }

    public ResourceUsage addDeviceStats(DeviceGroupStats... deviceStats) {
        if (this.deviceStats == null)
            this.deviceStats = new java.util.ArrayList<>();
        for (DeviceGroupStats item : deviceStats)
            this.deviceStats.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ResourceUsage fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ResourceUsage.class);
    }

    public static List<ResourceUsage> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ResourceUsage.class);
    }
}
