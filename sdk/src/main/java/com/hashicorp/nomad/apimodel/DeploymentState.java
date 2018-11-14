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
public final class DeploymentState extends ApiObject {
    private List<String> placedCanaries;
    private boolean autoRevert;
    private long progressDeadline;
    private Date requireProgressBy;
    private boolean promoted;
    private int desiredCanaries;
    private int desiredTotal;
    private int placedAllocs;
    private int healthyAllocs;
    private int unhealthyAllocs;

    @JsonProperty("PlacedCanaries")
    public List<String> getPlacedCanaries() {
        return placedCanaries;
    }

    public DeploymentState setPlacedCanaries(List<String> placedCanaries) {
        this.placedCanaries = placedCanaries;
        return this;
    }

    public DeploymentState addPlacedCanaries(String... placedCanaries) {
        if (this.placedCanaries == null)
            this.placedCanaries = new java.util.ArrayList<>();
        for (String item : placedCanaries)
            this.placedCanaries.add(item);
        return this;
    }

    @JsonProperty("AutoRevert")
    public boolean getAutoRevert() {
        return autoRevert;
    }

    public DeploymentState setAutoRevert(boolean autoRevert) {
        this.autoRevert = autoRevert;
        return this;
    }

    @JsonProperty("ProgressDeadline")
    public long getProgressDeadline() {
        return progressDeadline;
    }

    public DeploymentState setProgressDeadline(long progressDeadline) {
        this.progressDeadline = progressDeadline;
        return this;
    }

    @JsonProperty("RequireProgressBy")
    public Date getRequireProgressBy() {
        return requireProgressBy;
    }

    public DeploymentState setRequireProgressBy(Date requireProgressBy) {
        this.requireProgressBy = requireProgressBy;
        return this;
    }

    @JsonProperty("Promoted")
    public boolean getPromoted() {
        return promoted;
    }

    public DeploymentState setPromoted(boolean promoted) {
        this.promoted = promoted;
        return this;
    }

    @JsonProperty("DesiredCanaries")
    public int getDesiredCanaries() {
        return desiredCanaries;
    }

    public DeploymentState setDesiredCanaries(int desiredCanaries) {
        this.desiredCanaries = desiredCanaries;
        return this;
    }

    @JsonProperty("DesiredTotal")
    public int getDesiredTotal() {
        return desiredTotal;
    }

    public DeploymentState setDesiredTotal(int desiredTotal) {
        this.desiredTotal = desiredTotal;
        return this;
    }

    @JsonProperty("PlacedAllocs")
    public int getPlacedAllocs() {
        return placedAllocs;
    }

    public DeploymentState setPlacedAllocs(int placedAllocs) {
        this.placedAllocs = placedAllocs;
        return this;
    }

    @JsonProperty("HealthyAllocs")
    public int getHealthyAllocs() {
        return healthyAllocs;
    }

    public DeploymentState setHealthyAllocs(int healthyAllocs) {
        this.healthyAllocs = healthyAllocs;
        return this;
    }

    @JsonProperty("UnhealthyAllocs")
    public int getUnhealthyAllocs() {
        return unhealthyAllocs;
    }

    public DeploymentState setUnhealthyAllocs(int unhealthyAllocs) {
        this.unhealthyAllocs = unhealthyAllocs;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DeploymentState fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DeploymentState.class);
    }

    public static List<DeploymentState> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DeploymentState.class);
    }
}
