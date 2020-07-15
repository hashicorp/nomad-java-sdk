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
public final class JobListStub extends ApiObject {
    private String id;
    private String parentId;
    private String name;
    private String namespace;
    private List<String> datacenters;
    private String type;
    private int priority;
    private boolean periodic;
    private boolean parameterizedJob;
    private boolean stop;
    private String status;
    private String statusDescription;
    private JobSummary jobSummary;
    private BigInteger createIndex;
    private BigInteger modifyIndex;
    private BigInteger jobModifyIndex;
    private long submitTime;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public JobListStub setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("ParentID")
    public String getParentId() {
        return parentId;
    }

    public JobListStub setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public JobListStub setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public JobListStub setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("Datacenters")
    public List<String> getDatacenters() {
        return datacenters;
    }

    public JobListStub setDatacenters(List<String> datacenters) {
        this.datacenters = datacenters;
        return this;
    }

    public JobListStub addDatacenters(String... datacenters) {
        if (this.datacenters == null)
            this.datacenters = new java.util.ArrayList<>();
        for (String item : datacenters)
            this.datacenters.add(item);
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public JobListStub setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Priority")
    public int getPriority() {
        return priority;
    }

    public JobListStub setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @JsonProperty("Periodic")
    public boolean getPeriodic() {
        return periodic;
    }

    public JobListStub setPeriodic(boolean periodic) {
        this.periodic = periodic;
        return this;
    }

    @JsonProperty("ParameterizedJob")
    public boolean getParameterizedJob() {
        return parameterizedJob;
    }

    public JobListStub setParameterizedJob(boolean parameterizedJob) {
        this.parameterizedJob = parameterizedJob;
        return this;
    }

    @JsonProperty("Stop")
    public boolean getStop() {
        return stop;
    }

    public JobListStub setStop(boolean stop) {
        this.stop = stop;
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public JobListStub setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("StatusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    public JobListStub setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    @JsonProperty("JobSummary")
    public JobSummary getJobSummary() {
        return jobSummary;
    }

    public JobListStub setJobSummary(JobSummary jobSummary) {
        this.jobSummary = jobSummary;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public JobListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public JobListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public JobListStub setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("SubmitTime")
    public long getSubmitTime() {
        return submitTime;
    }

    public JobListStub setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobListStub.class);
    }

    public static List<JobListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobListStub.class);
    }
}
