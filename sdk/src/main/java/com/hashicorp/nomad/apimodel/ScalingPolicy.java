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
public final class ScalingPolicy extends ApiObject {
    private String id;
    private String namespace;
    private Map<String, String> target;
    private Long min;
    private long max;
    private Map<String, Object> policy;
    private Boolean enabled;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public ScalingPolicy setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public ScalingPolicy setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("Target")
    public Map<String, String> getTarget() {
        return target;
    }

    public ScalingPolicy setTarget(Map<String, String> target) {
        this.target = target;
        return this;
    }

    public ScalingPolicy addTarget(String key, String value) {
        if (this.target == null)
            this.target = new java.util.HashMap<>();
        this.target.put(key, value);
        return this;
    }

    @JsonProperty("Min")
    public Long getMin() {
        return min;
    }

    public ScalingPolicy setMin(Long min) {
        this.min = min;
        return this;
    }

    @JsonProperty("Max")
    public long getMax() {
        return max;
    }

    public ScalingPolicy setMax(long max) {
        this.max = max;
        return this;
    }

    @JsonProperty("Policy")
    public Map<String, Object> getPolicy() {
        return policy;
    }

    public ScalingPolicy setPolicy(Map<String, Object> policy) {
        this.policy = policy;
        return this;
    }

    public ScalingPolicy addPolicy(String key, Object value) {
        if (this.policy == null)
            this.policy = new java.util.HashMap<>();
        this.policy.put(key, value);
        return this;
    }

    @JsonProperty("Enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public ScalingPolicy setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public ScalingPolicy setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public ScalingPolicy setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ScalingPolicy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ScalingPolicy.class);
    }

    public static List<ScalingPolicy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ScalingPolicy.class);
    }
}
