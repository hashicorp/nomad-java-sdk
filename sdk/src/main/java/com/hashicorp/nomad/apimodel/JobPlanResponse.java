package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class JobPlanResponse {
    private BigInteger jobModifyIndex;
    private List<Evaluation> createdEvals;
    private JobDiff diff;
    private PlanAnnotations annotations;
    private Map<String, AllocationMetric> failedTgAllocs;
    private Date nextPeriodicLaunch;

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public JobPlanResponse setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("CreatedEvals")
    public List<Evaluation> getCreatedEvals() {
        return createdEvals;
    }

    public JobPlanResponse setCreatedEvals(List<Evaluation> createdEvals) {
        this.createdEvals = createdEvals;
        return this;
    }

    public JobPlanResponse addCreatedEvals(Evaluation... createdEvals) {
        if (this.createdEvals == null)
            this.createdEvals = new java.util.ArrayList<>();
        for (Evaluation item : createdEvals)
            this.createdEvals.add(item);
        return this;
    }

    @JsonProperty("Diff")
    public JobDiff getDiff() {
        return diff;
    }

    public JobPlanResponse setDiff(JobDiff diff) {
        this.diff = diff;
        return this;
    }

    @JsonProperty("Annotations")
    public PlanAnnotations getAnnotations() {
        return annotations;
    }

    public JobPlanResponse setAnnotations(PlanAnnotations annotations) {
        this.annotations = annotations;
        return this;
    }

    @JsonProperty("FailedTGAllocs")
    public Map<String, AllocationMetric> getFailedTgAllocs() {
        return failedTgAllocs;
    }

    public JobPlanResponse setFailedTgAllocs(Map<String, AllocationMetric> failedTgAllocs) {
        this.failedTgAllocs = failedTgAllocs;
        return this;
    }

    public JobPlanResponse addFailedTgAllocs(String key, AllocationMetric value) {
        if (this.failedTgAllocs == null)
            this.failedTgAllocs = new java.util.HashMap<>();
        this.failedTgAllocs.put(key, value);
        return this;
    }

    @JsonProperty("NextPeriodicLaunch")
    public Date getNextPeriodicLaunch() {
        return nextPeriodicLaunch;
    }

    public JobPlanResponse setNextPeriodicLaunch(Date nextPeriodicLaunch) {
        this.nextPeriodicLaunch = nextPeriodicLaunch;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobPlanResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobPlanResponse.class);
    }

    public static List<JobPlanResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobPlanResponse.class);
    }
}
