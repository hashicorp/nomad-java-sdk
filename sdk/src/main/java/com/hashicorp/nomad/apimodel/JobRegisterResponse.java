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
public final class JobRegisterResponse extends ApiObject {
    private String evalId;
    private BigInteger evalCreateIndex;
    private BigInteger jobModifyIndex;
    private String warnings;

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public JobRegisterResponse setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("EvalCreateIndex")
    public BigInteger getEvalCreateIndex() {
        return evalCreateIndex;
    }

    public JobRegisterResponse setEvalCreateIndex(BigInteger evalCreateIndex) {
        this.evalCreateIndex = evalCreateIndex;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public JobRegisterResponse setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("Warnings")
    public String getWarnings() {
        return warnings;
    }

    public JobRegisterResponse setWarnings(String warnings) {
        this.warnings = warnings;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobRegisterResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobRegisterResponse.class);
    }

    public static List<JobRegisterResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobRegisterResponse.class);
    }
}
