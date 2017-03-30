package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class JobDiff {
    private String type;
    private String id;
    private List<FieldDiff> fields;
    private List<ObjectDiff> objects;
    private List<TaskGroupDiff> taskGroups;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public JobDiff setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public JobDiff setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Fields")
    public List<FieldDiff> getFields() {
        return fields;
    }

    public JobDiff setFields(List<FieldDiff> fields) {
        this.fields = fields;
        return this;
    }

    public JobDiff addFields(FieldDiff... fields) {
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

    public JobDiff setObjects(List<ObjectDiff> objects) {
        this.objects = objects;
        return this;
    }

    public JobDiff addObjects(ObjectDiff... objects) {
        if (this.objects == null)
            this.objects = new java.util.ArrayList<>();
        for (ObjectDiff item : objects)
            this.objects.add(item);
        return this;
    }

    @JsonProperty("TaskGroups")
    public List<TaskGroupDiff> getTaskGroups() {
        return taskGroups;
    }

    public JobDiff setTaskGroups(List<TaskGroupDiff> taskGroups) {
        this.taskGroups = taskGroups;
        return this;
    }

    public JobDiff addTaskGroups(TaskGroupDiff... taskGroups) {
        if (this.taskGroups == null)
            this.taskGroups = new java.util.ArrayList<>();
        for (TaskGroupDiff item : taskGroups)
            this.taskGroups.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobDiff fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobDiff.class);
    }

    public static List<JobDiff> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobDiff.class);
    }
}
