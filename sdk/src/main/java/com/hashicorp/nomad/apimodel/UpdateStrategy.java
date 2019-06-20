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
public final class UpdateStrategy extends ApiObject {
    private Long stagger;
    private Integer maxParallel;
    private String healthCheck;
    private Long minHealthyTime;
    private Long healthyDeadline;
    private Long progressDeadline;
    private Integer canary;
    private Boolean autoRevert;
    private Boolean autoPromote;

    @JsonProperty("Stagger")
    public Long getStagger() {
        return stagger;
    }

    public UpdateStrategy setStagger(Long stagger) {
        this.stagger = stagger;
        return this;
    }

    @JsonProperty("MaxParallel")
    public Integer getMaxParallel() {
        return maxParallel;
    }

    public UpdateStrategy setMaxParallel(Integer maxParallel) {
        this.maxParallel = maxParallel;
        return this;
    }

    @JsonProperty("HealthCheck")
    public String getHealthCheck() {
        return healthCheck;
    }

    public UpdateStrategy setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
        return this;
    }

    @JsonProperty("MinHealthyTime")
    public Long getMinHealthyTime() {
        return minHealthyTime;
    }

    public UpdateStrategy setMinHealthyTime(Long minHealthyTime) {
        this.minHealthyTime = minHealthyTime;
        return this;
    }

    @JsonProperty("HealthyDeadline")
    public Long getHealthyDeadline() {
        return healthyDeadline;
    }

    public UpdateStrategy setHealthyDeadline(Long healthyDeadline) {
        this.healthyDeadline = healthyDeadline;
        return this;
    }

    @JsonProperty("ProgressDeadline")
    public Long getProgressDeadline() {
        return progressDeadline;
    }

    public UpdateStrategy setProgressDeadline(Long progressDeadline) {
        this.progressDeadline = progressDeadline;
        return this;
    }

    @JsonProperty("Canary")
    public Integer getCanary() {
        return canary;
    }

    public UpdateStrategy setCanary(Integer canary) {
        this.canary = canary;
        return this;
    }

    @JsonProperty("AutoRevert")
    public Boolean getAutoRevert() {
        return autoRevert;
    }

    public UpdateStrategy setAutoRevert(Boolean autoRevert) {
        this.autoRevert = autoRevert;
        return this;
    }

    @JsonProperty("AutoPromote")
    public Boolean getAutoPromote() {
        return autoPromote;
    }

    public UpdateStrategy setAutoPromote(Boolean autoPromote) {
        this.autoPromote = autoPromote;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static UpdateStrategy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, UpdateStrategy.class);
    }

    public static List<UpdateStrategy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, UpdateStrategy.class);
    }
}
