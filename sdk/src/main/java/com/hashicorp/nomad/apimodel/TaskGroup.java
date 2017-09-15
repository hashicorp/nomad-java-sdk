package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class TaskGroup extends ApiObject {
    private String name;
    private Integer count;
    private List<Constraint> constraints;
    private List<Task> tasks;
    private RestartPolicy restartPolicy;
    private EphemeralDisk ephemeralDisk;
    private UpdateStrategy update;
    private Map<String, String> meta;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public TaskGroup setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Count")
    public Integer getCount() {
        return count;
    }

    public TaskGroup setCount(Integer count) {
        this.count = count;
        return this;
    }

    @JsonProperty("Constraints")
    public List<Constraint> getConstraints() {
        return constraints;
    }

    public TaskGroup setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
        return this;
    }

    public TaskGroup addConstraints(Constraint... constraints) {
        if (this.constraints == null)
            this.constraints = new java.util.ArrayList<>();
        for (Constraint item : constraints)
            this.constraints.add(item);
        return this;
    }

    @JsonProperty("Tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    public TaskGroup setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskGroup addTasks(Task... tasks) {
        if (this.tasks == null)
            this.tasks = new java.util.ArrayList<>();
        for (Task item : tasks)
            this.tasks.add(item);
        return this;
    }

    @JsonProperty("RestartPolicy")
    public RestartPolicy getRestartPolicy() {
        return restartPolicy;
    }

    public TaskGroup setRestartPolicy(RestartPolicy restartPolicy) {
        this.restartPolicy = restartPolicy;
        return this;
    }

    @JsonProperty("EphemeralDisk")
    public EphemeralDisk getEphemeralDisk() {
        return ephemeralDisk;
    }

    public TaskGroup setEphemeralDisk(EphemeralDisk ephemeralDisk) {
        this.ephemeralDisk = ephemeralDisk;
        return this;
    }

    @JsonProperty("Update")
    public UpdateStrategy getUpdate() {
        return update;
    }

    public TaskGroup setUpdate(UpdateStrategy update) {
        this.update = update;
        return this;
    }

    @JsonProperty("Meta")
    public Map<String, String> getMeta() {
        return meta;
    }

    public TaskGroup setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public TaskGroup addMeta(String key, String value) {
        if (this.meta == null)
            this.meta = new java.util.HashMap<>();
        this.meta.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskGroup fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskGroup.class);
    }

    public static List<TaskGroup> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskGroup.class);
    }
}
