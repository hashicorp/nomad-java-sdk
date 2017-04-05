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
public final class Template extends ApiObject {
    private String sourcePath;
    private String destPath;
    private String embeddedTmpl;
    private String changeMode;
    private String changeSignal;
    private Long splay;
    private String perms;
    private String leftDelim;
    private String rightDelim;

    @JsonProperty("SourcePath")
    public String getSourcePath() {
        return sourcePath;
    }

    public Template setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    @JsonProperty("DestPath")
    public String getDestPath() {
        return destPath;
    }

    public Template setDestPath(String destPath) {
        this.destPath = destPath;
        return this;
    }

    @JsonProperty("EmbeddedTmpl")
    public String getEmbeddedTmpl() {
        return embeddedTmpl;
    }

    public Template setEmbeddedTmpl(String embeddedTmpl) {
        this.embeddedTmpl = embeddedTmpl;
        return this;
    }

    @JsonProperty("ChangeMode")
    public String getChangeMode() {
        return changeMode;
    }

    public Template setChangeMode(String changeMode) {
        this.changeMode = changeMode;
        return this;
    }

    @JsonProperty("ChangeSignal")
    public String getChangeSignal() {
        return changeSignal;
    }

    public Template setChangeSignal(String changeSignal) {
        this.changeSignal = changeSignal;
        return this;
    }

    @JsonProperty("Splay")
    public Long getSplay() {
        return splay;
    }

    public Template setSplay(Long splay) {
        this.splay = splay;
        return this;
    }

    @JsonProperty("Perms")
    public String getPerms() {
        return perms;
    }

    public Template setPerms(String perms) {
        this.perms = perms;
        return this;
    }

    @JsonProperty("LeftDelim")
    public String getLeftDelim() {
        return leftDelim;
    }

    public Template setLeftDelim(String leftDelim) {
        this.leftDelim = leftDelim;
        return this;
    }

    @JsonProperty("RightDelim")
    public String getRightDelim() {
        return rightDelim;
    }

    public Template setRightDelim(String rightDelim) {
        this.rightDelim = rightDelim;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static Template fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, Template.class);
    }

    public static List<Template> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, Template.class);
    }
}
