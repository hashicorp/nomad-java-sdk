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
public final class MigrateStrategy extends ApiObject {
    private Integer maxParallel;
    private String healthCheck;
    private Long minHealthyTime;
    private Long healthyDeadline;

    @JsonProperty("MaxParallel")
    public Integer getMaxParallel() {
        return maxParallel;
    }

    public MigrateStrategy setMaxParallel(Integer maxParallel) {
        this.maxParallel = maxParallel;
        return this;
    }

    @JsonProperty("HealthCheck")
    public String getHealthCheck() {
        return healthCheck;
    }

    public MigrateStrategy setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
        return this;
    }

    @JsonProperty("MinHealthyTime")
    public Long getMinHealthyTime() {
        return minHealthyTime;
    }

    public MigrateStrategy setMinHealthyTime(Long minHealthyTime) {
        this.minHealthyTime = minHealthyTime;
        return this;
    }

    @JsonProperty("HealthyDeadline")
    public Long getHealthyDeadline() {
        return healthyDeadline;
    }

    public MigrateStrategy setHealthyDeadline(Long healthyDeadline) {
        this.healthyDeadline = healthyDeadline;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static MigrateStrategy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, MigrateStrategy.class);
    }

    public static List<MigrateStrategy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, MigrateStrategy.class);
    }
}
