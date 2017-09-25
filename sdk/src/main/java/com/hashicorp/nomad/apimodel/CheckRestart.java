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
public final class CheckRestart extends ApiObject {
    private int limit;
    private Long grace;
    private boolean ignoreWarnings;

    @JsonProperty("Limit")
    public int getLimit() {
        return limit;
    }

    public CheckRestart setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @JsonProperty("Grace")
    public Long getGrace() {
        return grace;
    }

    public CheckRestart setGrace(Long grace) {
        this.grace = grace;
        return this;
    }

    @JsonProperty("IgnoreWarnings")
    public boolean getIgnoreWarnings() {
        return ignoreWarnings;
    }

    public CheckRestart setIgnoreWarnings(boolean ignoreWarnings) {
        this.ignoreWarnings = ignoreWarnings;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static CheckRestart fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, CheckRestart.class);
    }

    public static List<CheckRestart> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, CheckRestart.class);
    }
}
