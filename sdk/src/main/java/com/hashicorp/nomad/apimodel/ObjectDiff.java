package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class ObjectDiff extends ApiObject {
    private String type;
    private String name;
    private List<FieldDiff> fields;
    private List<ObjectDiff> objects;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public ObjectDiff setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public ObjectDiff setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Fields")
    public List<FieldDiff> getFields() {
        return fields;
    }

    public ObjectDiff setFields(List<FieldDiff> fields) {
        this.fields = fields;
        return this;
    }

    public ObjectDiff addFields(FieldDiff... fields) {
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

    public ObjectDiff setObjects(List<ObjectDiff> objects) {
        this.objects = objects;
        return this;
    }

    public ObjectDiff addObjects(ObjectDiff... objects) {
        if (this.objects == null)
            this.objects = new java.util.ArrayList<>();
        for (ObjectDiff item : objects)
            this.objects.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ObjectDiff fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ObjectDiff.class);
    }

    public static List<ObjectDiff> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ObjectDiff.class);
    }
}
