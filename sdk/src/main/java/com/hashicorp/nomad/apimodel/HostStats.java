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
public final class HostStats extends ApiObject {
    private HostMemoryStats memory;
    private List<HostCpuStats> cpu;
    private List<HostDiskStats> diskStats;
    private List<DeviceGroupStats> deviceStats;
    private BigInteger uptime;
    private double cpuTicksConsumed;

    @JsonProperty("Memory")
    public HostMemoryStats getMemory() {
        return memory;
    }

    public HostStats setMemory(HostMemoryStats memory) {
        this.memory = memory;
        return this;
    }

    @JsonProperty("CPU")
    public List<HostCpuStats> getCpu() {
        return cpu;
    }

    public HostStats setCpu(List<HostCpuStats> cpu) {
        this.cpu = cpu;
        return this;
    }

    public HostStats addCpu(HostCpuStats... cpu) {
        if (this.cpu == null)
            this.cpu = new java.util.ArrayList<>();
        for (HostCpuStats item : cpu)
            this.cpu.add(item);
        return this;
    }

    @JsonProperty("DiskStats")
    public List<HostDiskStats> getDiskStats() {
        return diskStats;
    }

    public HostStats setDiskStats(List<HostDiskStats> diskStats) {
        this.diskStats = diskStats;
        return this;
    }

    public HostStats addDiskStats(HostDiskStats... diskStats) {
        if (this.diskStats == null)
            this.diskStats = new java.util.ArrayList<>();
        for (HostDiskStats item : diskStats)
            this.diskStats.add(item);
        return this;
    }

    @JsonProperty("DeviceStats")
    public List<DeviceGroupStats> getDeviceStats() {
        return deviceStats;
    }

    public HostStats setDeviceStats(List<DeviceGroupStats> deviceStats) {
        this.deviceStats = deviceStats;
        return this;
    }

    public HostStats addDeviceStats(DeviceGroupStats... deviceStats) {
        if (this.deviceStats == null)
            this.deviceStats = new java.util.ArrayList<>();
        for (DeviceGroupStats item : deviceStats)
            this.deviceStats.add(item);
        return this;
    }

    @JsonProperty("Uptime")
    public BigInteger getUptime() {
        return uptime;
    }

    public HostStats setUptime(BigInteger uptime) {
        this.uptime = uptime;
        return this;
    }

    @JsonProperty("CPUTicksConsumed")
    public double getCpuTicksConsumed() {
        return cpuTicksConsumed;
    }

    public HostStats setCpuTicksConsumed(double cpuTicksConsumed) {
        this.cpuTicksConsumed = cpuTicksConsumed;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostStats.class);
    }

    public static List<HostStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostStats.class);
    }
}
