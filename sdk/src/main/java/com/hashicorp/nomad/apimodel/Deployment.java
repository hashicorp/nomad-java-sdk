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
public final class Deployment extends ApiObject {
    private String id;
    private String namespace;
    private String jobId;
    private BigInteger jobVersion;
    private BigInteger jobModifyIndex;
    private BigInteger jobSpecModifyIndex;
    private BigInteger jobCreateIndex;
    private boolean isMultiregion;
    private Map<String, DeploymentState> taskGroups;
    private String status;
    private String statusDescription;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public Deployment setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public Deployment setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public Deployment setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("JobVersion")
    public BigInteger getJobVersion() {
        return jobVersion;
    }

    public Deployment setJobVersion(BigInteger jobVersion) {
        this.jobVersion = jobVersion;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public Deployment setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("JobSpecModifyIndex")
    public BigInteger getJobSpecModifyIndex() {
        return jobSpecModifyIndex;
    }

    public Deployment setJobSpecModifyIndex(BigInteger jobSpecModifyIndex) {
        this.jobSpecModifyIndex = jobSpecModifyIndex;
        return this;
    }

    @JsonProperty("JobCreateIndex")
    public BigInteger getJobCreateIndex() {
        return jobCreateIndex;
    }

    public Deployment setJobCreateIndex(BigInteger jobCreateIndex) {
        this.jobCreateIndex = jobCreateIndex;
        return this;
    }

    @JsonProperty("IsMultiregion")
    public boolean getIsMultiregion() {
        return isMultiregion;
    }

    public Deployment setIsMultiregion(boolean isMultiregion) {
        this.isMultiregion = isMultiregion;
        return this;
    }

    @JsonProperty("TaskGroups")
    public Map<String, DeploymentState> getTaskGroups() {
        return taskGroups;
    }

    public Deployment setTaskGroups(Map<String, DeploymentState> taskGroups) {
        this.taskGroups = taskGroups;
        return this;
    }

    public Deployment addTaskGroups(String key, DeploymentState value) {
        if (this.taskGroups == null)
            this.taskGroups = new java.util.HashMap<>();
        this.taskGroups.put(key, value);
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public Deployment setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("StatusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    public Deployment setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public Deployment setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public Deployment setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Deployment fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Deployment.class);
    }

    public static List<Deployment> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Deployment.class);
    }
}
