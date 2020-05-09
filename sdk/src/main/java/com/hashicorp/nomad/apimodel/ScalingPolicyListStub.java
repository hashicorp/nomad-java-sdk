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
public final class ScalingPolicyListStub extends ApiObject {
    private String id;
    private boolean enabled;
    private Map<String, String> target;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public ScalingPolicyListStub setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Enabled")
    public boolean getEnabled() {
        return enabled;
    }

    public ScalingPolicyListStub setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @JsonProperty("Target")
    public Map<String, String> getTarget() {
        return target;
    }

    public ScalingPolicyListStub setTarget(Map<String, String> target) {
        this.target = target;
        return this;
    }

    public ScalingPolicyListStub addTarget(String key, String value) {
        if (this.target == null)
            this.target = new java.util.HashMap<>();
        this.target.put(key, value);
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public ScalingPolicyListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public ScalingPolicyListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ScalingPolicyListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ScalingPolicyListStub.class);
    }

    public static List<ScalingPolicyListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ScalingPolicyListStub.class);
    }
}
