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
public final class ReschedulePolicy extends ApiObject {
    private Integer attempts;
    private Long interval;
    private Long delay;
    private String delayFunction;
    private Long maxDelay;
    private Boolean unlimited;

    @JsonProperty("Attempts")
    public Integer getAttempts() {
        return attempts;
    }

    public ReschedulePolicy setAttempts(Integer attempts) {
        this.attempts = attempts;
        return this;
    }

    @JsonProperty("Interval")
    public Long getInterval() {
        return interval;
    }

    public ReschedulePolicy setInterval(Long interval) {
        this.interval = interval;
        return this;
    }

    @JsonProperty("Delay")
    public Long getDelay() {
        return delay;
    }

    public ReschedulePolicy setDelay(Long delay) {
        this.delay = delay;
        return this;
    }

    @JsonProperty("DelayFunction")
    public String getDelayFunction() {
        return delayFunction;
    }

    public ReschedulePolicy setDelayFunction(String delayFunction) {
        this.delayFunction = delayFunction;
        return this;
    }

    @JsonProperty("MaxDelay")
    public Long getMaxDelay() {
        return maxDelay;
    }

    public ReschedulePolicy setMaxDelay(Long maxDelay) {
        this.maxDelay = maxDelay;
        return this;
    }

    @JsonProperty("Unlimited")
    public Boolean getUnlimited() {
        return unlimited;
    }

    public ReschedulePolicy setUnlimited(Boolean unlimited) {
        this.unlimited = unlimited;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ReschedulePolicy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ReschedulePolicy.class);
    }

    public static List<ReschedulePolicy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ReschedulePolicy.class);
    }
}
