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
public final class PreemptionConfig extends ApiObject {
    private boolean systemSchedulerEnabled;
    private boolean batchSchedulerEnabled;
    private boolean serviceSchedulerEnabled;

    @JsonProperty("SystemSchedulerEnabled")
    public boolean getSystemSchedulerEnabled() {
        return systemSchedulerEnabled;
    }

    public PreemptionConfig setSystemSchedulerEnabled(boolean systemSchedulerEnabled) {
        this.systemSchedulerEnabled = systemSchedulerEnabled;
        return this;
    }

    @JsonProperty("BatchSchedulerEnabled")
    public boolean getBatchSchedulerEnabled() {
        return batchSchedulerEnabled;
    }

    public PreemptionConfig setBatchSchedulerEnabled(boolean batchSchedulerEnabled) {
        this.batchSchedulerEnabled = batchSchedulerEnabled;
        return this;
    }

    @JsonProperty("ServiceSchedulerEnabled")
    public boolean getServiceSchedulerEnabled() {
        return serviceSchedulerEnabled;
    }

    public PreemptionConfig setServiceSchedulerEnabled(boolean serviceSchedulerEnabled) {
        this.serviceSchedulerEnabled = serviceSchedulerEnabled;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static PreemptionConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, PreemptionConfig.class);
    }

    public static List<PreemptionConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, PreemptionConfig.class);
    }
}
