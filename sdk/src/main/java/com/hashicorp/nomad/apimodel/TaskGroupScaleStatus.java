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
public final class TaskGroupScaleStatus extends ApiObject {
    private int desired;
    private int placed;
    private int running;
    private int healthy;
    private int unhealthy;
    private List<ScalingEvent> events;

    @JsonProperty("Desired")
    public int getDesired() {
        return desired;
    }

    public TaskGroupScaleStatus setDesired(int desired) {
        this.desired = desired;
        return this;
    }

    @JsonProperty("Placed")
    public int getPlaced() {
        return placed;
    }

    public TaskGroupScaleStatus setPlaced(int placed) {
        this.placed = placed;
        return this;
    }

    @JsonProperty("Running")
    public int getRunning() {
        return running;
    }

    public TaskGroupScaleStatus setRunning(int running) {
        this.running = running;
        return this;
    }

    @JsonProperty("Healthy")
    public int getHealthy() {
        return healthy;
    }

    public TaskGroupScaleStatus setHealthy(int healthy) {
        this.healthy = healthy;
        return this;
    }

    @JsonProperty("Unhealthy")
    public int getUnhealthy() {
        return unhealthy;
    }

    public TaskGroupScaleStatus setUnhealthy(int unhealthy) {
        this.unhealthy = unhealthy;
        return this;
    }

    @JsonProperty("Events")
    public List<ScalingEvent> getEvents() {
        return events;
    }

    public TaskGroupScaleStatus setEvents(List<ScalingEvent> events) {
        this.events = events;
        return this;
    }

    public TaskGroupScaleStatus addEvents(ScalingEvent... events) {
        if (this.events == null)
            this.events = new java.util.ArrayList<>();
        for (ScalingEvent item : events)
            this.events.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskGroupScaleStatus fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskGroupScaleStatus.class);
    }

    public static List<TaskGroupScaleStatus> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskGroupScaleStatus.class);
    }
}
