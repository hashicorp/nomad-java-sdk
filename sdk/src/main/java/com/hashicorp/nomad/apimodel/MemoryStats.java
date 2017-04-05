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
public final class MemoryStats extends ApiObject {
    private BigInteger rss;
    private BigInteger cache;
    private BigInteger swap;
    private BigInteger maxUsage;
    private BigInteger kernelUsage;
    private BigInteger kernelMaxUsage;
    private List<String> measured;

    @JsonProperty("RSS")
    public BigInteger getRss() {
        return rss;
    }

    public MemoryStats setRss(BigInteger rss) {
        this.rss = rss;
        return this;
    }

    @JsonProperty("Cache")
    public BigInteger getCache() {
        return cache;
    }

    public MemoryStats setCache(BigInteger cache) {
        this.cache = cache;
        return this;
    }

    @JsonProperty("Swap")
    public BigInteger getSwap() {
        return swap;
    }

    public MemoryStats setSwap(BigInteger swap) {
        this.swap = swap;
        return this;
    }

    @JsonProperty("MaxUsage")
    public BigInteger getMaxUsage() {
        return maxUsage;
    }

    public MemoryStats setMaxUsage(BigInteger maxUsage) {
        this.maxUsage = maxUsage;
        return this;
    }

    @JsonProperty("KernelUsage")
    public BigInteger getKernelUsage() {
        return kernelUsage;
    }

    public MemoryStats setKernelUsage(BigInteger kernelUsage) {
        this.kernelUsage = kernelUsage;
        return this;
    }

    @JsonProperty("KernelMaxUsage")
    public BigInteger getKernelMaxUsage() {
        return kernelMaxUsage;
    }

    public MemoryStats setKernelMaxUsage(BigInteger kernelMaxUsage) {
        this.kernelMaxUsage = kernelMaxUsage;
        return this;
    }

    @JsonProperty("Measured")
    public List<String> getMeasured() {
        return measured;
    }

    public MemoryStats setMeasured(List<String> measured) {
        this.measured = measured;
        return this;
    }

    public MemoryStats addMeasured(String... measured) {
        if (this.measured == null)
            this.measured = new java.util.ArrayList<>();
        for (String item : measured)
            this.measured.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static MemoryStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, MemoryStats.class);
    }

    public static List<MemoryStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, MemoryStats.class);
    }
}
