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
public final class LogConfig {
    private Integer maxFiles;
    private Integer maxFileSizeMb;

    @JsonProperty("MaxFiles")
    public Integer getMaxFiles() {
        return maxFiles;
    }

    public LogConfig setMaxFiles(Integer maxFiles) {
        this.maxFiles = maxFiles;
        return this;
    }

    @JsonProperty("MaxFileSizeMB")
    public Integer getMaxFileSizeMb() {
        return maxFileSizeMb;
    }

    public LogConfig setMaxFileSizeMb(Integer maxFileSizeMb) {
        this.maxFileSizeMb = maxFileSizeMb;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static LogConfig fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, LogConfig.class);
    }

    public static List<LogConfig> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, LogConfig.class);
    }
}
