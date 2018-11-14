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
public final class SchedulerConfigurationResponse extends ApiObject {
    private SchedulerConfiguration schedulerConfig;

    @JsonProperty("SchedulerConfig")
    public SchedulerConfiguration getSchedulerConfig() {
        return schedulerConfig;
    }

    public SchedulerConfigurationResponse setSchedulerConfig(SchedulerConfiguration schedulerConfig) {
        this.schedulerConfig = schedulerConfig;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static SchedulerConfigurationResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, SchedulerConfigurationResponse.class);
    }

    public static List<SchedulerConfigurationResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, SchedulerConfigurationResponse.class);
    }
}
