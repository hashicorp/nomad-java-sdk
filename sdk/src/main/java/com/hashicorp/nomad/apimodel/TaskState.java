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
public final class TaskState {
    private String state;
    private boolean failed;
    private List<TaskEvent> events;

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    public TaskState setState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("Failed")
    public boolean getFailed() {
        return failed;
    }

    public TaskState setFailed(boolean failed) {
        this.failed = failed;
        return this;
    }

    @JsonProperty("Events")
    public List<TaskEvent> getEvents() {
        return events;
    }

    public TaskState setEvents(List<TaskEvent> events) {
        this.events = events;
        return this;
    }

    public TaskState addEvents(TaskEvent... events) {
        if (this.events == null)
            this.events = new java.util.ArrayList<>();
        for (TaskEvent item : events)
            this.events.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static TaskState fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, TaskState.class);
    }

    public static List<TaskState> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, TaskState.class);
    }
}
