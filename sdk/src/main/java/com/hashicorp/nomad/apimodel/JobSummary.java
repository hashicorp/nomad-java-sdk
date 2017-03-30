package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public final class JobSummary {
    private String jobId;
    private Map<String, TaskGroupSummary> summary;
    private JobChildrenSummary children;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public JobSummary setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("Summary")
    public Map<String, TaskGroupSummary> getSummary() {
        return summary;
    }

    public JobSummary setSummary(Map<String, TaskGroupSummary> summary) {
        this.summary = summary;
        return this;
    }

    public JobSummary addSummary(String key, TaskGroupSummary value) {
        if (this.summary == null)
            this.summary = new java.util.HashMap<>();
        this.summary.put(key, value);
        return this;
    }

    @JsonProperty("Children")
    public JobChildrenSummary getChildren() {
        return children;
    }

    public JobSummary setChildren(JobChildrenSummary children) {
        this.children = children;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public JobSummary setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public JobSummary setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobSummary fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobSummary.class);
    }

    public static List<JobSummary> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobSummary.class);
    }
}
