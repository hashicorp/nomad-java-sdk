package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class HostMemoryStats {
    private BigInteger total;
    private BigInteger available;
    private BigInteger used;
    private BigInteger free;

    @JsonProperty("Total")
    public BigInteger getTotal() {
        return total;
    }

    public HostMemoryStats setTotal(BigInteger total) {
        this.total = total;
        return this;
    }

    @JsonProperty("Available")
    public BigInteger getAvailable() {
        return available;
    }

    public HostMemoryStats setAvailable(BigInteger available) {
        this.available = available;
        return this;
    }

    @JsonProperty("Used")
    public BigInteger getUsed() {
        return used;
    }

    public HostMemoryStats setUsed(BigInteger used) {
        this.used = used;
        return this;
    }

    @JsonProperty("Free")
    public BigInteger getFree() {
        return free;
    }

    public HostMemoryStats setFree(BigInteger free) {
        this.free = free;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static HostMemoryStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, HostMemoryStats.class);
    }

    public static List<HostMemoryStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, HostMemoryStats.class);
    }
}
