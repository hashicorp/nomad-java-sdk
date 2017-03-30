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
public final class Allocation {
    private String id;
    private String evalId;
    private String name;
    private String nodeId;
    private String jobId;
    private Job job;
    private String taskGroup;
    private Resources resources;
    private Map<String, Resources> taskResources;
    private Map<String, String> services;
    private AllocationMetric metrics;
    private String desiredStatus;
    private String desiredDescription;
    private String clientStatus;
    private String clientDescription;
    private Map<String, TaskState> taskStates;
    private String previousAllocation;
    private BigInteger createIndex;
    private BigInteger modifyIndex;
    private BigInteger allocModifyIndex;
    private long createTime;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public Allocation setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public Allocation setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public Allocation setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("NodeID")
    public String getNodeId() {
        return nodeId;
    }

    public Allocation setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public Allocation setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("Job")
    public Job getJob() {
        return job;
    }

    public Allocation setJob(Job job) {
        this.job = job;
        return this;
    }

    @JsonProperty("TaskGroup")
    public String getTaskGroup() {
        return taskGroup;
    }

    public Allocation setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
        return this;
    }

    @JsonProperty("Resources")
    public Resources getResources() {
        return resources;
    }

    public Allocation setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    @JsonProperty("TaskResources")
    public Map<String, Resources> getTaskResources() {
        return taskResources;
    }

    public Allocation setTaskResources(Map<String, Resources> taskResources) {
        this.taskResources = taskResources;
        return this;
    }

    public Allocation addTaskResources(String key, Resources value) {
        if (this.taskResources == null)
            this.taskResources = new java.util.HashMap<>();
        this.taskResources.put(key, value);
        return this;
    }

    @JsonProperty("Services")
    public Map<String, String> getServices() {
        return services;
    }

    public Allocation setServices(Map<String, String> services) {
        this.services = services;
        return this;
    }

    public Allocation addServices(String key, String value) {
        if (this.services == null)
            this.services = new java.util.HashMap<>();
        this.services.put(key, value);
        return this;
    }

    @JsonProperty("Metrics")
    public AllocationMetric getMetrics() {
        return metrics;
    }

    public Allocation setMetrics(AllocationMetric metrics) {
        this.metrics = metrics;
        return this;
    }

    @JsonProperty("DesiredStatus")
    public String getDesiredStatus() {
        return desiredStatus;
    }

    public Allocation setDesiredStatus(String desiredStatus) {
        this.desiredStatus = desiredStatus;
        return this;
    }

    @JsonProperty("DesiredDescription")
    public String getDesiredDescription() {
        return desiredDescription;
    }

    public Allocation setDesiredDescription(String desiredDescription) {
        this.desiredDescription = desiredDescription;
        return this;
    }

    @JsonProperty("ClientStatus")
    public String getClientStatus() {
        return clientStatus;
    }

    public Allocation setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
        return this;
    }

    @JsonProperty("ClientDescription")
    public String getClientDescription() {
        return clientDescription;
    }

    public Allocation setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
        return this;
    }

    @JsonProperty("TaskStates")
    public Map<String, TaskState> getTaskStates() {
        return taskStates;
    }

    public Allocation setTaskStates(Map<String, TaskState> taskStates) {
        this.taskStates = taskStates;
        return this;
    }

    public Allocation addTaskStates(String key, TaskState value) {
        if (this.taskStates == null)
            this.taskStates = new java.util.HashMap<>();
        this.taskStates.put(key, value);
        return this;
    }

    @JsonProperty("PreviousAllocation")
    public String getPreviousAllocation() {
        return previousAllocation;
    }

    public Allocation setPreviousAllocation(String previousAllocation) {
        this.previousAllocation = previousAllocation;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public Allocation setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public Allocation setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @JsonProperty("AllocModifyIndex")
    public BigInteger getAllocModifyIndex() {
        return allocModifyIndex;
    }

    public Allocation setAllocModifyIndex(BigInteger allocModifyIndex) {
        this.allocModifyIndex = allocModifyIndex;
        return this;
    }

    @JsonProperty("CreateTime")
    public long getCreateTime() {
        return createTime;
    }

    public Allocation setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Allocation fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Allocation.class);
    }

    public static List<Allocation> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Allocation.class);
    }
}
