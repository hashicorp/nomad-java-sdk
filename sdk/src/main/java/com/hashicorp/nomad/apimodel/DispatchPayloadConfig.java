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
public final class DispatchPayloadConfig {
    private String file;

    @JsonProperty("File")
    public String getFile() {
        return file;
    }

    public DispatchPayloadConfig setFile(String file) {
        this.file = file;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static DispatchPayloadConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, DispatchPayloadConfig.class);
    }

    public static List<DispatchPayloadConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, DispatchPayloadConfig.class);
    }
}
