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
public final class CpuStats {
    private double systemMode;
    private double userMode;
    private double totalTicks;
    private BigInteger throttledPeriods;
    private BigInteger throttledTime;
    private double percent;
    private List<String> measured;

    @JsonProperty("SystemMode")
    public double getSystemMode() {
        return systemMode;
    }

    public CpuStats setSystemMode(double systemMode) {
        this.systemMode = systemMode;
        return this;
    }

    @JsonProperty("UserMode")
    public double getUserMode() {
        return userMode;
    }

    public CpuStats setUserMode(double userMode) {
        this.userMode = userMode;
        return this;
    }

    @JsonProperty("TotalTicks")
    public double getTotalTicks() {
        return totalTicks;
    }

    public CpuStats setTotalTicks(double totalTicks) {
        this.totalTicks = totalTicks;
        return this;
    }

    @JsonProperty("ThrottledPeriods")
    public BigInteger getThrottledPeriods() {
        return throttledPeriods;
    }

    public CpuStats setThrottledPeriods(BigInteger throttledPeriods) {
        this.throttledPeriods = throttledPeriods;
        return this;
    }

    @JsonProperty("ThrottledTime")
    public BigInteger getThrottledTime() {
        return throttledTime;
    }

    public CpuStats setThrottledTime(BigInteger throttledTime) {
        this.throttledTime = throttledTime;
        return this;
    }

    @JsonProperty("Percent")
    public double getPercent() {
        return percent;
    }

    public CpuStats setPercent(double percent) {
        this.percent = percent;
        return this;
    }

    @JsonProperty("Measured")
    public List<String> getMeasured() {
        return measured;
    }

    public CpuStats setMeasured(List<String> measured) {
        this.measured = measured;
        return this;
    }

    public CpuStats addMeasured(String... measured) {
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

    public static CpuStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CpuStats.class);
    }

    public static List<CpuStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CpuStats.class);
    }
}
