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
public final class TaskDiff {
    private String type;
    private String name;
    private List<FieldDiff> fields;
    private List<ObjectDiff> objects;
    private List<String> annotations;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public TaskDiff setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public TaskDiff setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Fields")
    public List<FieldDiff> getFields() {
        return fields;
    }

    public TaskDiff setFields(List<FieldDiff> fields) {
        this.fields = fields;
        return this;
    }

    public TaskDiff addFields(FieldDiff... fields) {
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

    public TaskDiff setObjects(List<ObjectDiff> objects) {
        this.objects = objects;
        return this;
    }

    public TaskDiff addObjects(ObjectDiff... objects) {
        if (this.objects == null)
            this.objects = new java.util.ArrayList<>();
        for (ObjectDiff item : objects)
            this.objects.add(item);
        return this;
    }

    @JsonProperty("Annotations")
    public List<String> getAnnotations() {
        return annotations;
    }

    public TaskDiff setAnnotations(List<String> annotations) {
        this.annotations = annotations;
        return this;
    }

    public TaskDiff addAnnotations(String... annotations) {
        if (this.annotations == null)
            this.annotations = new java.util.ArrayList<>();
        for (String item : annotations)
            this.annotations.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskDiff fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskDiff.class);
    }

    public static List<TaskDiff> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskDiff.class);
    }
}
