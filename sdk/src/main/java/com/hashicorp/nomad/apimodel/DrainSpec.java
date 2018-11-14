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
public final class DrainSpec extends ApiObject {
    private long deadline;
    private boolean ignoreSystemJobs;

    @JsonProperty("Deadline")
    public long getDeadline() {
        return deadline;
    }

    public DrainSpec setDeadline(long deadline) {
        this.deadline = deadline;
        return this;
    }

    @JsonProperty("IgnoreSystemJobs")
    public boolean getIgnoreSystemJobs() {
        return ignoreSystemJobs;
    }

    public DrainSpec setIgnoreSystemJobs(boolean ignoreSystemJobs) {
        this.ignoreSystemJobs = ignoreSystemJobs;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DrainSpec fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DrainSpec.class);
    }

    public static List<DrainSpec> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DrainSpec.class);
    }
}
