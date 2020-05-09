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
public final class ConsulExposeConfig extends ApiObject {
    private List<ConsulExposePath> path;

    @JsonProperty("Path")
    public List<ConsulExposePath> getPath() {
        return path;
    }

    public ConsulExposeConfig setPath(List<ConsulExposePath> path) {
        this.path = path;
        return this;
    }

    public ConsulExposeConfig addPath(ConsulExposePath... path) {
        if (this.path == null)
            this.path = new java.util.ArrayList<>();
        for (ConsulExposePath item : path)
            this.path.add(item);
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulExposeConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulExposeConfig.class);
    }

    public static List<ConsulExposeConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulExposeConfig.class);
    }
}
