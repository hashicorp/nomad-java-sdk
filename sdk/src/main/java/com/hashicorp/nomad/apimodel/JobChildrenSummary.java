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
public final class JobChildrenSummary {
    private long pending;
    private long running;
    private long dead;

    @JsonProperty("Pending")
    public long getPending() {
        return pending;
    }

    public JobChildrenSummary setPending(long pending) {
        this.pending = pending;
        return this;
    }

    @JsonProperty("Running")
    public long getRunning() {
        return running;
    }

    public JobChildrenSummary setRunning(long running) {
        this.running = running;
        return this;
    }

    @JsonProperty("Dead")
    public long getDead() {
        return dead;
    }

    public JobChildrenSummary setDead(long dead) {
        this.dead = dead;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static JobChildrenSummary fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, JobChildrenSummary.class);
    }

    public static List<JobChildrenSummary> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, JobChildrenSummary.class);
    }
}
