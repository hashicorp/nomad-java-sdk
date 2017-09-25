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
public final class Job extends ApiObject {
    private Boolean stop;
    private String region;
    private String namespace;
    private String id;
    private String parentId;
    private String name;
    private String type;
    private Integer priority;
    private Boolean allAtOnce;
    private List<String> datacenters;
    private List<Constraint> constraints;
    private List<TaskGroup> taskGroups;
    private UpdateStrategy update;
    private PeriodicConfig periodic;
    private ParameterizedJobConfig parameterizedJob;
    private byte[] payload;
    private Map<String, String> meta;
    private String vaultToken;
    private String status;
    private String statusDescription;
    private Boolean stable;
    private BigInteger version;
    private Long submitTime;
    private BigInteger createIndex;
    private BigInteger modifyIndex;
    private BigInteger jobModifyIndex;

    @JsonProperty("Stop")
    public Boolean getStop() {
        return stop;
    }

    public Job setStop(Boolean stop) {
        this.stop = stop;
        return this;
    }

    @JsonProperty("Region")
    public String getRegion() {
        return region;
    }

    public Job setRegion(String region) {
        this.region = region;
        return this;
    }

    @JsonProperty("Namespace")
    public String getNamespace() {
        return namespace;
    }

    public Job setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public Job setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("ParentID")
    public String getParentId() {
        return parentId;
    }

    public Job setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public Job setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public Job setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Priority")
    public Integer getPriority() {
        return priority;
    }

    public Job setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    @JsonProperty("AllAtOnce")
    public Boolean getAllAtOnce() {
        return allAtOnce;
    }

    public Job setAllAtOnce(Boolean allAtOnce) {
        this.allAtOnce = allAtOnce;
        return this;
    }

    @JsonProperty("Datacenters")
    public List<String> getDatacenters() {
        return datacenters;
    }

    public Job setDatacenters(List<String> datacenters) {
        this.datacenters = datacenters;
        return this;
    }

    public Job addDatacenters(String... datacenters) {
        if (this.datacenters == null)
            this.datacenters = new java.util.ArrayList<>();
        for (String item : datacenters)
            this.datacenters.add(item);
        return this;
    }

    @JsonProperty("Constraints")
    public List<Constraint> getConstraints() {
        return constraints;
    }

    public Job setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
        return this;
    }

    public Job addConstraints(Constraint... constraints) {
        if (this.constraints == null)
            this.constraints = new java.util.ArrayList<>();
        for (Constraint item : constraints)
            this.constraints.add(item);
        return this;
    }

    @JsonProperty("TaskGroups")
    public List<TaskGroup> getTaskGroups() {
        return taskGroups;
    }

    public Job setTaskGroups(List<TaskGroup> taskGroups) {
        this.taskGroups = taskGroups;
        return this;
    }

    public Job addTaskGroups(TaskGroup... taskGroups) {
        if (this.taskGroups == null)
            this.taskGroups = new java.util.ArrayList<>();
        for (TaskGroup item : taskGroups)
            this.taskGroups.add(item);
        return this;
    }

    @JsonProperty("Update")
    public UpdateStrategy getUpdate() {
        return update;
    }

    public Job setUpdate(UpdateStrategy update) {
        this.update = update;
        return this;
    }

    @JsonProperty("Periodic")
    public PeriodicConfig getPeriodic() {
        return periodic;
    }

    public Job setPeriodic(PeriodicConfig periodic) {
        this.periodic = periodic;
        return this;
    }

    @JsonProperty("ParameterizedJob")
    public ParameterizedJobConfig getParameterizedJob() {
        return parameterizedJob;
    }

    public Job setParameterizedJob(ParameterizedJobConfig parameterizedJob) {
        this.parameterizedJob = parameterizedJob;
        return this;
    }

    @JsonProperty("Payload")
    public byte[] getPayload() {
        return payload;
    }

    public Job setPayload(byte[] payload) {
        this.payload = payload;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public Job setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public Job addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @JsonProperty("VaultToken")
    public String getVaultToken() {
        return vaultToken;
    }

    public Job setVaultToken(String vaultToken) {
        this.vaultToken = vaultToken;
        return this;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public Job setStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("StatusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    public Job setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    @JsonProperty("Stable")
    public Boolean getStable() {
        return stable;
    }

    public Job setStable(Boolean stable) {
        this.stable = stable;
        return this;
    }

    @JsonProperty("Version")
    public BigInteger getVersion() {
        return version;
    }

    public Job setVersion(BigInteger version) {
        this.version = version;
        return this;
    }

    @JsonProperty("SubmitTime")
    public Long getSubmitTime() {
        return submitTime;
    }

    public Job setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
        return this;
    }

    @JsonProperty("CreateIndex")
    public BigInteger getCreateIndex() {
        return createIndex;
    }

    public Job setCreateIndex(BigInteger createIndex) {
        this.createIndex = createIndex;
        return this;
    }

    @JsonProperty("ModifyIndex")
    public BigInteger getModifyIndex() {
        return modifyIndex;
    }

    public Job setModifyIndex(BigInteger modifyIndex) {
        this.modifyIndex = modifyIndex;
        return this;
    }

    @JsonProperty("JobModifyIndex")
    public BigInteger getJobModifyIndex() {
        return jobModifyIndex;
    }

    public Job setJobModifyIndex(BigInteger jobModifyIndex) {
        this.jobModifyIndex = jobModifyIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Job fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Job.class);
    }

    public static List<Job> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Job.class);
    }
}
