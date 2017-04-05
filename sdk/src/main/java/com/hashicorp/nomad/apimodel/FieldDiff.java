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
public final class FieldDiff extends ApiObject {
    private String type;
    private String name;
    private String old;
    private String New;
    private List<String> annotations;

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public FieldDiff setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public FieldDiff setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("Old")
    public String getOld() {
        return old;
    }

    public FieldDiff setOld(String old) {
        this.old = old;
        return this;
    }

    @JsonProperty("New")
    public String getNew() {
        return New;
    }

    public FieldDiff setNew(String New) {
        this.New = New;
        return this;
    }

    @JsonProperty("Annotations")
    public List<String> getAnnotations() {
        return annotations;
    }

    public FieldDiff setAnnotations(List<String> annotations) {
        this.annotations = annotations;
        return this;
    }

    public FieldDiff addAnnotations(String... annotations) {
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

    public static FieldDiff fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, FieldDiff.class);
    }

    public static List<FieldDiff> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, FieldDiff.class);
    }
}
