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
public final class RescheduleTracker extends ApiObject {
    private List<RescheduleEvent> events;

    @JsonProperty("Events")
    public List<RescheduleEvent> getEvents() {
        return events;
    }

    public RescheduleTracker setEvents(List<RescheduleEvent> events) {
        this.events = events;
        return this;
    }

    public RescheduleTracker addEvents(RescheduleEvent... events) {
        if (this.events == null)
            this.events = new java.util.ArrayList<>();
        for (RescheduleEvent item : events)
            this.events.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static RescheduleTracker fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, RescheduleTracker.class);
    }

    public static List<RescheduleTracker> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, RescheduleTracker.class);
    }
}
