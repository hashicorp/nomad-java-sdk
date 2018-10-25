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
public final class DesiredTransition extends ApiObject {
    private Boolean migrate;

    @JsonProperty("Migrate")
    public Boolean getMigrate() {
        return migrate;
    }

    public DesiredTransition setMigrate(Boolean migrate) {
        this.migrate = migrate;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DesiredTransition fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DesiredTransition.class);
    }

    public static List<DesiredTransition> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DesiredTransition.class);
    }
}
