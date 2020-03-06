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
public final class AllocFileInfo extends ApiObject {
    private String name;
    private boolean isDir;
    private long size;
    private String fileMode;
    private Date modTime;
    private String contentType;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public AllocFileInfo setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("IsDir")
    public boolean getIsDir() {
        return isDir;
    }

    public AllocFileInfo setIsDir(boolean isDir) {
        this.isDir = isDir;
        return this;
    }

    @JsonProperty("Size")
    public long getSize() {
        return size;
    }

    public AllocFileInfo setSize(long size) {
        this.size = size;
        return this;
    }

    @JsonProperty("FileMode")
    public String getFileMode() {
        return fileMode;
    }

    public AllocFileInfo setFileMode(String fileMode) {
        this.fileMode = fileMode;
        return this;
    }

    @JsonProperty("ModTime")
    public Date getModTime() {
        return modTime;
    }

    public AllocFileInfo setModTime(Date modTime) {
        this.modTime = modTime;
        return this;
    }

    @JsonProperty("ContentType")
    public String getContentType() {
        return contentType;
    }

    public AllocFileInfo setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocFileInfo fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocFileInfo.class);
    }

    public static List<AllocFileInfo> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocFileInfo.class);
    }
}
