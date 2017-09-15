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
public final class JobStabilityResponse extends ApiObject {
    private BigInteger jobModifyIndex;

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public JobStabilityResponse setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobStabilityResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobStabilityResponse.class);
    }

    public static List<JobStabilityResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobStabilityResponse.class);
    }
}
