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
public final class PlanAnnotations extends ApiObject {
    private Map<String, DesiredUpdates> desiredTgUpdates;

    @JsonProperty("DesiredTGUpdates")
    public Map<String, DesiredUpdates> getDesiredTgUpdates() {
        return desiredTgUpdates;
    }

    public PlanAnnotations setDesiredTgUpdates(Map<String, DesiredUpdates> desiredTgUpdates) {
        this.desiredTgUpdates = desiredTgUpdates;
        return this;
    }

    public PlanAnnotations addDesiredTgUpdates(String key, DesiredUpdates value) {
        if (this.desiredTgUpdates == null)
            this.desiredTgUpdates = new java.util.HashMap<>();
        this.desiredTgUpdates.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static PlanAnnotations fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, PlanAnnotations.class);
    }

    public static List<PlanAnnotations> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, PlanAnnotations.class);
    }
}
