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
public final class TaskGroupDiff {
    private String type;
    private String name;
    private List<FieldDiff> fields;
    private List<ObjectDiff> objects;
    private List<TaskDiff> tasks;
    private Map<String, BigInteger> updates;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public TaskGroupDiff setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public TaskGroupDiff setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Fields")
    public List<FieldDiff> getFields() {
        return fields;
    }

    public TaskGroupDiff setFields(List<FieldDiff> fields) {
        this.fields = fields;
        return this;
    }

    public TaskGroupDiff addFields(FieldDiff... fields) {
        if (this.fields == null)
            this.fields = new java.util.ArrayList<>();
        for (FieldDiff item : fields)
            this.fields.add(item);
        return this;
    }

    @JsonProperty("Objects")
    public List<ObjectDiff> getObjects() {
        return objects;
    }

    public TaskGroupDiff setObjects(List<ObjectDiff> objects) {
        this.objects = objects;
        return this;
    }

    public TaskGroupDiff addObjects(ObjectDiff... objects) {
        if (this.objects == null)
            this.objects = new java.util.ArrayList<>();
        for (ObjectDiff item : objects)
            this.objects.add(item);
        return this;
    }

    @JsonProperty("Tasks")
    public List<TaskDiff> getTasks() {
        return tasks;
    }

    public TaskGroupDiff setTasks(List<TaskDiff> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskGroupDiff addTasks(TaskDiff... tasks) {
        if (this.tasks == null)
            this.tasks = new java.util.ArrayList<>();
        for (TaskDiff item : tasks)
            this.tasks.add(item);
        return this;
    }

    @JsonProperty("Updates")
    public Map<String, BigInteger> getUpdates() {
        return updates;
    }

    public TaskGroupDiff setUpdates(Map<String, BigInteger> updates) {
        this.updates = updates;
        return this;
    }

    public TaskGroupDiff addUpdates(String key, BigInteger value) {
        if (this.updates == null)
            this.updates = new java.util.HashMap<>();
        this.updates.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskGroupDiff fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskGroupDiff.class);
    }

    public static List<TaskGroupDiff> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskGroupDiff.class);
    }
}
