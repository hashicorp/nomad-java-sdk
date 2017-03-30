package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class JobDispatchResponse {
    private String dispatchedJobId;
    private String evalId;
    private BigInteger evalCreateIndex;
    private BigInteger jobCreateIndex;

    @JsonProperty("DispatchedJobID")
    public String getDispatchedJobId() {
        return dispatchedJobId;
    }

    public JobDispatchResponse setDispatchedJobId(String dispatchedJobId) {
        this.dispatchedJobId = dispatchedJobId;
        return this;
    }

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public JobDispatchResponse setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("EvalCreateIndex")
    public BigInteger getEvalCreateIndex() {
        return evalCreateIndex;
    }

    public JobDispatchResponse setEvalCreateIndex(BigInteger evalCreateIndex) {
        this.evalCreateIndex = evalCreateIndex;
        return this;
    }

    @JsonProperty("JobCreateIndex")
    public BigInteger getJobCreateIndex() {
        return jobCreateIndex;
    }

    public JobDispatchResponse setJobCreateIndex(BigInteger jobCreateIndex) {
        this.jobCreateIndex = jobCreateIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobDispatchResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobDispatchResponse.class);
    }

    public static List<JobDispatchResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobDispatchResponse.class);
    }
}
