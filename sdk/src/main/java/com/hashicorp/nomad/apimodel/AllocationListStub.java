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
public final class AllocationListStub extends ApiObject {
    private String id;
    private String evalId;
    private String name;
    private String namespace;
    private String nodeId;
    private String jobId;
    private String jobType;
    private BigInteger jobVersion;
    private String taskGroup;
    private String desiredStatus;
    private String desiredDescription;
    private String clientStatus;
    private String clientDescription;
    private Map<String, TaskState> taskStates;
    private AllocDeploymentStatus deploymentStatus;
    private String followupEvalId;
    private RescheduleTracker rescheduleTracker;
    private BigInteger createIndex;
    private BigInteger modifyIndex;
    private long createTime;
    private long modifyTime;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public AllocationListStub setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("EvalID")
    public String getEvalId() {
        return evalId;
    }

    public AllocationListStub setEvalId(String evalId) {
        this.evalId = evalId;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public AllocationListStub setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public AllocationListStub setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("NodeID")
    public String getNodeId() {
        return nodeId;
    }

    public AllocationListStub setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public AllocationListStub setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("JobType")
    public String getJobType() {
        return jobType;
    }

    public AllocationListStub setJobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    @JsonProperty("JobVersion")
    public BigInteger getJobVersion() {
        return jobVersion;
    }

    public AllocationListStub setJobVersion(BigInteger jobVersion) {
        this.jobVersion = jobVersion;
        return this;
    }

    @JsonProperty("TaskGroup")
    public String getTaskGroup() {
        return taskGroup;
    }

    public AllocationListStub setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
        return this;
    }

    @JsonProperty("DesiredStatus")
    public String getDesiredStatus() {
        return desiredStatus;
    }

    public AllocationListStub setDesiredStatus(String desiredStatus) {
        this.desiredStatus = desiredStatus;
        return this;
    }

    @JsonProperty("DesiredDescription")
    public String getDesiredDescription() {
        return desiredDescription;
    }

    public AllocationListStub setDesiredDescription(String desiredDescription) {
        this.desiredDescription = desiredDescription;
        return this;
    }

    @JsonProperty("ClientStatus")
    public String getClientStatus() {
        return clientStatus;
    }

    public AllocationListStub setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
        return this;
    }

    @JsonProperty("ClientDescription")
    public String getClientDescription() {
        return clientDescription;
    }

    public AllocationListStub setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
        return this;
    }

    @JsonProperty("TaskStates")
    public Map<String, TaskState> getTaskStates() {
        return taskStates;
    }

    public AllocationListStub setTaskStates(Map<String, TaskState> taskStates) {
        this.taskStates = taskStates;
        return this;
    }

    public AllocationListStub addTaskStates(String key, TaskState value) {
        if (this.taskStates == null)
            this.taskStates = new java.util.HashMap<>();
        this.taskStates.put(key, value);
        return this;
    }

    @JsonProperty("DeploymentStatus")
    public AllocDeploymentStatus getDeploymentStatus() {
        return deploymentStatus;
    }

    public AllocationListStub setDeploymentStatus(AllocDeploymentStatus deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
        return this;
    }

    @JsonProperty("FollowupEvalID")
    public String getFollowupEvalId() {
        return followupEvalId;
    }

    public AllocationListStub setFollowupEvalId(String followupEvalId) {
        this.followupEvalId = followupEvalId;
        return this;
    }

    @JsonProperty("RescheduleTracker")
    public RescheduleTracker getRescheduleTracker() {
        return rescheduleTracker;
    }

    public AllocationListStub setRescheduleTracker(RescheduleTracker rescheduleTracker) {
        this.rescheduleTracker = rescheduleTracker;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public AllocationListStub setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public AllocationListStub setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @JsonProperty("CreateTime")
    public long getCreateTime() {
        return createTime;
    }

    public AllocationListStub setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    @JsonProperty("ModifyTime")
    public long getModifyTime() {
        return modifyTime;
    }

    public AllocationListStub setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocationListStub fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocationListStub.class);
    }

    public static List<AllocationListStub> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocationListStub.class);
    }
}
