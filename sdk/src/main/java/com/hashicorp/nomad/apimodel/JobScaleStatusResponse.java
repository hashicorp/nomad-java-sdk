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
public final class JobScaleStatusResponse extends ApiObject {
    private String jobId;
    private BigInteger jobCreateIndex;
    private BigInteger jobModifyIndex;
    private boolean jobStopped;
    private Map<String, TaskGroupScaleStatus> taskGroups;

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public JobScaleStatusResponse setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("JobCreateIndex")
    public BigInteger getJobCreateIndex() {
        return jobCreateIndex;
    }

    public JobScaleStatusResponse setJobCreateIndex(BigInteger jobCreateIndex) {
        this.jobCreateIndex = jobCreateIndex;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public JobScaleStatusResponse setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("JobStopped")
    public boolean getJobStopped() {
        return jobStopped;
    }

    public JobScaleStatusResponse setJobStopped(boolean jobStopped) {
        this.jobStopped = jobStopped;
        return this;
    }

    @JsonProperty("TaskGroups")
    public Map<String, TaskGroupScaleStatus> getTaskGroups() {
        return taskGroups;
    }

    public JobScaleStatusResponse setTaskGroups(Map<String, TaskGroupScaleStatus> taskGroups) {
        this.taskGroups = taskGroups;
        return this;
    }

    public JobScaleStatusResponse addTaskGroups(String key, TaskGroupScaleStatus value) {
        if (this.taskGroups == null)
            this.taskGroups = new java.util.HashMap<>();
        this.taskGroups.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobScaleStatusResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobScaleStatusResponse.class);
    }

    public static List<JobScaleStatusResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobScaleStatusResponse.class);
    }
}
