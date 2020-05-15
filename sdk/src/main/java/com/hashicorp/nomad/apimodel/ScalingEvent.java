package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ScalingEvent extends ApiObject {
    private Long count;
    private boolean error;
    private String message;
    private Map<String, Object> meta;
    private String evalId;
    private BigInteger time;
    private BigInteger createIndex;

    @JsonProperty("Count")
    public Long getCount() {
        return count;
    }

    public ScalingEvent setCount(Long count) {
        this.count = count;
        return this;
    }

    @JsonProperty("Error")
    public boolean getError() {
        return error;
    }

    public ScalingEvent setError(boolean error) {
        this.error = error;
        return this;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public ScalingEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, Object> getMeta() {
        return meta;
    }

    public ScalingEvent setMeta(Map<String, Object> meta) {
        this.meta = meta;
        return this;
    }

    public ScalingEvent addMeta(String key, Object value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public ScalingEvent setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("Time")
    public BigInteger getTime() {
        return time;
    }

    public ScalingEvent setTime(BigInteger time) {
        this.time = time;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public ScalingEvent setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ScalingEvent fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ScalingEvent.class);
    }

    public static List<ScalingEvent> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ScalingEvent.class);
    }
}
