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
public final class DeploymentUpdateResponse extends ApiObject {
    private String evalId;
    private BigInteger evalCreateIndex;
    private BigInteger deploymentModifyIndex;
    private BigInteger revertedJobVersion;

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public DeploymentUpdateResponse setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("EvalCreateIndex")
    public BigInteger getEvalCreateIndex() {
        return evalCreateIndex;
    }

    public DeploymentUpdateResponse setEvalCreateIndex(BigInteger evalCreateIndex) {
        this.evalCreateIndex = evalCreateIndex;
        return this;
    }

    @JsonProperty("DeploymentModifyIndex")
    public BigInteger getDeploymentModifyIndex() {
        return deploymentModifyIndex;
    }

    public DeploymentUpdateResponse setDeploymentModifyIndex(BigInteger deploymentModifyIndex) {
        this.deploymentModifyIndex = deploymentModifyIndex;
        return this;
    }

    @JsonProperty("RevertedJobVersion")
    public BigInteger getRevertedJobVersion() {
        return revertedJobVersion;
    }

    public DeploymentUpdateResponse setRevertedJobVersion(BigInteger revertedJobVersion) {
        this.revertedJobVersion = revertedJobVersion;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DeploymentUpdateResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DeploymentUpdateResponse.class);
    }

    public static List<DeploymentUpdateResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DeploymentUpdateResponse.class);
    }
}
