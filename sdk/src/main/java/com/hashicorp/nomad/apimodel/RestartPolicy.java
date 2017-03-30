package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class RestartPolicy {
    private Long interval;
    private Integer attempts;
    private Long delay;
    private String mode;

    @JsonProperty("Interval")
    public Long getInterval() {
        return interval;
    }

    public RestartPolicy setInterval(Long interval) {
        this.interval = interval;
        return this;
    }

    @JsonProperty("Attempts")
    public Integer getAttempts() {
        return attempts;
    }

    public RestartPolicy setAttempts(Integer attempts) {
        this.attempts = attempts;
        return this;
    }

    @JsonProperty("Delay")
    public Long getDelay() {
        return delay;
    }

    public RestartPolicy setDelay(Long delay) {
        this.delay = delay;
        return this;
    }

    @JsonProperty("Mode")
    public String getMode() {
        return mode;
    }

    public RestartPolicy setMode(String mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RestartPolicy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RestartPolicy.class);
    }

    public static List<RestartPolicy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RestartPolicy.class);
    }
}
