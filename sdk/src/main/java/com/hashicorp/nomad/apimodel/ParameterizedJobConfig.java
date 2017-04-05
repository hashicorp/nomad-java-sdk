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
public final class ParameterizedJobConfig extends ApiObject {
    private String payload;
    private List<String> metaRequired;
    private List<String> metaOptional;

    @JsonProperty("Payload")
    public String getPayload() {
        return payload;
    }

    public ParameterizedJobConfig setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    @JsonProperty("MetaRequired")
    public List<String> getMetaRequired() {
        return metaRequired;
    }

    public ParameterizedJobConfig setMetaRequired(List<String> metaRequired) {
        this.metaRequired = metaRequired;
        return this;
    }

    public ParameterizedJobConfig addMetaRequired(String... metaRequired) {
        if (this.metaRequired == null)
            this.metaRequired = new java.util.ArrayList<>();
        for (String item : metaRequired)
            this.metaRequired.add(item);
        return this;
    }

    @JsonProperty("MetaOptional")
    public List<String> getMetaOptional() {
        return metaOptional;
    }

    public ParameterizedJobConfig setMetaOptional(List<String> metaOptional) {
        this.metaOptional = metaOptional;
        return this;
    }

    public ParameterizedJobConfig addMetaOptional(String... metaOptional) {
        if (this.metaOptional == null)
            this.metaOptional = new java.util.ArrayList<>();
        for (String item : metaOptional)
            this.metaOptional.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ParameterizedJobConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ParameterizedJobConfig.class);
    }

    public static List<ParameterizedJobConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ParameterizedJobConfig.class);
    }
}
