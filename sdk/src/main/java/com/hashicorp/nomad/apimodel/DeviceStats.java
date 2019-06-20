package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class DeviceStats extends ApiObject {
    private StatValue summary;
    private StatObject stats;
    private Date timestamp;

    @JsonProperty("Summary")
    public StatValue getSummary() {
        return summary;
    }

    public DeviceStats setSummary(StatValue summary) {
        this.summary = summary;
        return this;
    }

    @JsonProperty("Stats")
    public StatObject getStats() {
        return stats;
    }

    public DeviceStats setStats(StatObject stats) {
        this.stats = stats;
        return this;
    }

    @JsonProperty("Timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public DeviceStats setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DeviceStats fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DeviceStats.class);
    }

    public static List<DeviceStats> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DeviceStats.class);
    }
}
