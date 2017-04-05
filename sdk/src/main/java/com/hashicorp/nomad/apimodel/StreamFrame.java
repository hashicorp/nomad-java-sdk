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
public final class StreamFrame extends ApiObject {
    private long offset;
    private byte[] data;
    private String file;
    private String fileEvent;

    @JsonProperty("Offset")
    public long getOffset() {
        return offset;
    }

    public StreamFrame setOffset(long offset) {
        this.offset = offset;
        return this;
    }

    @JsonProperty("Data")
    public byte[] getData() {
        return data;
    }

    public StreamFrame setData(byte[] data) {
        this.data = data;
        return this;
    }

    @JsonProperty("File")
    public String getFile() {
        return file;
    }

    public StreamFrame setFile(String file) {
        this.file = file;
        return this;
    }

    @JsonProperty("FileEvent")
    public String getFileEvent() {
        return fileEvent;
    }

    public StreamFrame setFileEvent(String fileEvent) {
        this.fileEvent = fileEvent;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static StreamFrame fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, StreamFrame.class);
    }

    public static List<StreamFrame> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, StreamFrame.class);
    }
}
