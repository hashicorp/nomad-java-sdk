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
public final class SchedulerConfiguration extends ApiObject {
    private String schedulerAlgorithm;
    private PreemptionConfig preemptionConfig;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("SchedulerAlgorithm")
    public String getSchedulerAlgorithm() {
        return schedulerAlgorithm;
    }

    public SchedulerConfiguration setSchedulerAlgorithm(String schedulerAlgorithm) {
        this.schedulerAlgorithm = schedulerAlgorithm;
        return this;
    }

    @JsonProperty("PreemptionConfig")
    public PreemptionConfig getPreemptionConfig() {
        return preemptionConfig;
    }

    public SchedulerConfiguration setPreemptionConfig(PreemptionConfig preemptionConfig) {
        this.preemptionConfig = preemptionConfig;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public SchedulerConfiguration setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public SchedulerConfiguration setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SchedulerConfiguration fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SchedulerConfiguration.class);
    }

    public static List<SchedulerConfiguration> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SchedulerConfiguration.class);
    }
}
