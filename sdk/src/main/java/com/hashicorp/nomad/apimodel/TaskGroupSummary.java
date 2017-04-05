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
public final class TaskGroupSummary extends ApiObject {
    private int queued;
    private int complete;
    private int failed;
    private int running;
    private int starting;
    private int lost;

    @JsonProperty("Queued")
    public int getQueued() {
        return queued;
    }

    public TaskGroupSummary setQueued(int queued) {
        this.queued = queued;
        return this;
    }

    @JsonProperty("Complete")
    public int getComplete() {
        return complete;
    }

    public TaskGroupSummary setComplete(int complete) {
        this.complete = complete;
        return this;
    }

    @JsonProperty("Failed")
    public int getFailed() {
        return failed;
    }

    public TaskGroupSummary setFailed(int failed) {
        this.failed = failed;
        return this;
    }

    @JsonProperty("Running")
    public int getRunning() {
        return running;
    }

    public TaskGroupSummary setRunning(int running) {
        this.running = running;
        return this;
    }

    @JsonProperty("Starting")
    public int getStarting() {
        return starting;
    }

    public TaskGroupSummary setStarting(int starting) {
        this.starting = starting;
        return this;
    }

    @JsonProperty("Lost")
    public int getLost() {
        return lost;
    }

    public TaskGroupSummary setLost(int lost) {
        this.lost = lost;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskGroupSummary fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskGroupSummary.class);
    }

    public static List<TaskGroupSummary> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskGroupSummary.class);
    }
}
