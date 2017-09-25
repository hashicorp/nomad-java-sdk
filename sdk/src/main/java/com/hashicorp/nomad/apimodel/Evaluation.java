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
public final class Evaluation extends ApiObject {
    private String id;
    private int priority;
    private String type;
    private String triggeredBy;
    private String namespace;
    private String jobId;
    private BigInteger jobModifyIndex;
    private String nodeId;
    private BigInteger nodeModifyIndex;
    private String deploymentId;
    private String status;
    private String statusDescription;
    private long wait;
    private String nextEval;
    private String previousEval;
    private String blockedEval;
    private Map<String, AllocationMetric> failedTgAllocs;
    private Map<String, Boolean> classEligibility;
    private boolean escapedComputedClass;
    private boolean annotatePlan;
    private Map<String, Integer> queuedAllocations;
    private BigInteger snapshotIndex;
    private BigInteger createIndex;
    private BigInteger modifyIndex;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public Evaluation setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Priority")
    public int getPriority() {
        return priority;
    }

    public Evaluation setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public Evaluation setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("TriggeredBy")
    public String getTriggeredBy() {
        return triggeredBy;
    }

    public Evaluation setTriggeredBy(String triggeredBy) {
        this.triggeredBy = triggeredBy;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public Evaluation setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("JobID")
    public String getJobId() {
        return jobId;
    }

    public Evaluation setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public Evaluation setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @JsonProperty("NodeID")
    public String getNodeId() {
        return nodeId;
    }

    public Evaluation setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("NodeModifyIndex")
    public BigInteger getNodeModifyIndex() {
        return nodeModifyIndex;
    }

    public Evaluation setNodeModifyIndex(BigInteger nodeModifyIndex) {
        this.nodeModifyIndex = nodeModifyIndex;
        return this;
    }

    @JsonProperty("DeploymentID")
    public String getDeploymentId() {
        return deploymentId;
    }

    public Evaluation setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public Evaluation setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("StatusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    public Evaluation setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    @JsonProperty("Wait")
    public long getWait() {
        return wait;
    }

    public Evaluation setWait(long wait) {
        this.wait = wait;
        return this;
    }

    @JsonProperty("NextEval")
    public String getNextEval() {
        return nextEval;
    }

    public Evaluation setNextEval(String nextEval) {
        this.nextEval = nextEval;
        return this;
    }

    @JsonProperty("PreviousEval")
    public String getPreviousEval() {
        return previousEval;
    }

    public Evaluation setPreviousEval(String previousEval) {
        this.previousEval = previousEval;
        return this;
    }

    @JsonProperty("BlockedEval")
    public String getBlockedEval() {
        return blockedEval;
    }

    public Evaluation setBlockedEval(String blockedEval) {
        this.blockedEval = blockedEval;
        return this;
    }

    @JsonProperty("FailedTGAllocs")
    public Map<String, AllocationMetric> getFailedTgAllocs() {
        return failedTgAllocs;
    }

    public Evaluation setFailedTgAllocs(Map<String, AllocationMetric> failedTgAllocs) {
        this.failedTgAllocs = failedTgAllocs;
        return this;
    }

    public Evaluation addFailedTgAllocs(String key, AllocationMetric value) {
        if (this.failedTgAllocs == null)
            this.failedTgAllocs = new java.util.HashMap<>();
        this.failedTgAllocs.put(key, value);
        return this;
    }

    @JsonProperty("ClassEligibility")
    public Map<String, Boolean> getClassEligibility() {
        return classEligibility;
    }

    public Evaluation setClassEligibility(Map<String, Boolean> classEligibility) {
        this.classEligibility = classEligibility;
        return this;
    }

    public Evaluation addClassEligibility(String key, boolean value) {
        if (this.classEligibility == null)
            this.classEligibility = new java.util.HashMap<>();
        this.classEligibility.put(key, value);
        return this;
    }

    @JsonProperty("EscapedComputedClass")
    public boolean getEscapedComputedClass() {
        return escapedComputedClass;
    }

    public Evaluation setEscapedComputedClass(boolean escapedComputedClass) {
        this.escapedComputedClass = escapedComputedClass;
        return this;
    }

    @JsonProperty("AnnotatePlan")
    public boolean getAnnotatePlan() {
        return annotatePlan;
    }

    public Evaluation setAnnotatePlan(boolean annotatePlan) {
        this.annotatePlan = annotatePlan;
        return this;
    }

    @JsonProperty("QueuedAllocations")
    public Map<String, Integer> getQueuedAllocations() {
        return queuedAllocations;
    }

    public Evaluation setQueuedAllocations(Map<String, Integer> queuedAllocations) {
        this.queuedAllocations = queuedAllocations;
        return this;
    }

    public Evaluation addQueuedAllocations(String key, int value) {
        if (this.queuedAllocations == null)
            this.queuedAllocations = new java.util.HashMap<>();
        this.queuedAllocations.put(key, value);
        return this;
    }

    @JsonProperty("SnapshotIndex")
    public BigInteger getSnapshotIndex() {
        return snapshotIndex;
    }

    public Evaluation setSnapshotIndex(BigInteger snapshotIndex) {
        this.snapshotIndex = snapshotIndex;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public Evaluation setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public Evaluation setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Evaluation fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Evaluation.class);
    }

    public static List<Evaluation> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Evaluation.class);
    }
}
