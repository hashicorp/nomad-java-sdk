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
public final class HostDiskStats extends ApiObject {
    private String device;
    private String mountpoint;
    private BigInteger size;
    private BigInteger used;
    private BigInteger available;
    private double usedPercent;
    private double inodesUsedPercent;

    @JsonProperty("Device")
    public String getDevice() {
        return device;
    }

    public HostDiskStats setDevice(String device) {
        this.device = device;
        return this;
    }

    @JsonProperty("Mountpoint")
    public String getMountpoint() {
        return mountpoint;
    }

    public HostDiskStats setMountpoint(String mountpoint) {
        this.mountpoint = mountpoint;
        return this;
    }

    @JsonProperty("Size")
    public BigInteger getSize() {
        return size;
    }

    public HostDiskStats setSize(BigInteger size) {
        this.size = size;
        return this;
    }

    @JsonProperty("Used")
    public BigInteger getUsed() {
        return used;
    }

    public HostDiskStats setUsed(BigInteger used) {
        this.used = used;
        return this;
    }

    @JsonProperty("Available")
    public BigInteger getAvailable() {
        return available;
    }

    public HostDiskStats setAvailable(BigInteger available) {
        this.available = available;
        return this;
    }

    @JsonProperty("UsedPercent")
    public double getUsedPercent() {
        return usedPercent;
    }

    public HostDiskStats setUsedPercent(double usedPercent) {
        this.usedPercent = usedPercent;
        return this;
    }

    @JsonProperty("InodesUsedPercent")
    public double getInodesUsedPercent() {
        return inodesUsedPercent;
    }

    public HostDiskStats setInodesUsedPercent(double inodesUsedPercent) {
        this.inodesUsedPercent = inodesUsedPercent;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostDiskStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostDiskStats.class);
    }

    public static List<HostDiskStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostDiskStats.class);
    }
}
