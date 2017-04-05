package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class DesiredUpdates extends ApiObject {
    private BigInteger ignore;
    private BigInteger place;
    private BigInteger migrate;
    private BigInteger stop;
    private BigInteger inPlaceUpdate;
    private BigInteger destructiveUpdate;

    @JsonProperty("Ignore")
    public BigInteger getIgnore() {
        return ignore;
    }

    public DesiredUpdates setIgnore(BigInteger ignore) {
        this.ignore = ignore;
        return this;
    }

    @JsonProperty("Place")
    public BigInteger getPlace() {
        return place;
    }

    public DesiredUpdates setPlace(BigInteger place) {
        this.place = place;
        return this;
    }

    @JsonProperty("Migrate")
    public BigInteger getMigrate() {
        return migrate;
    }

    public DesiredUpdates setMigrate(BigInteger migrate) {
        this.migrate = migrate;
        return this;
    }

    @JsonProperty("Stop")
    public BigInteger getStop() {
        return stop;
    }

    public DesiredUpdates setStop(BigInteger stop) {
        this.stop = stop;
        return this;
    }

    @JsonProperty("InPlaceUpdate")
    public BigInteger getInPlaceUpdate() {
        return inPlaceUpdate;
    }

    public DesiredUpdates setInPlaceUpdate(BigInteger inPlaceUpdate) {
        this.inPlaceUpdate = inPlaceUpdate;
        return this;
    }

    @JsonProperty("DestructiveUpdate")
    public BigInteger getDestructiveUpdate() {
        return destructiveUpdate;
    }

    public DesiredUpdates setDestructiveUpdate(BigInteger destructiveUpdate) {
        this.destructiveUpdate = destructiveUpdate;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DesiredUpdates fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DesiredUpdates.class);
    }

    public static List<DesiredUpdates> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DesiredUpdates.class);
    }
}
