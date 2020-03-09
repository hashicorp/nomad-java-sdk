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
public final class DrainStrategy extends ApiObject {
    private DrainSpec drainSpec;
    private Date forceDeadline;
    private Date startedAt;

    @JsonProperty("DrainSpec")
    public DrainSpec getDrainSpec() {
        return drainSpec;
    }

    public DrainStrategy setDrainSpec(DrainSpec drainSpec) {
        this.drainSpec = drainSpec;
        return this;
    }

    @JsonProperty("ForceDeadline")
    public Date getForceDeadline() {
        return forceDeadline;
    }

    public DrainStrategy setForceDeadline(Date forceDeadline) {
        this.forceDeadline = forceDeadline;
        return this;
    }

    @JsonProperty("StartedAt")
    public Date getStartedAt() {
        return startedAt;
    }

    public DrainStrategy setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DrainStrategy fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DrainStrategy.class);
    }

    public static List<DrainStrategy> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DrainStrategy.class);
    }
}
