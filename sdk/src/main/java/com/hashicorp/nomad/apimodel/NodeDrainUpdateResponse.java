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
public final class NodeDrainUpdateResponse extends ApiObject {
    private BigInteger nodeModifyIndex;
    private List<String> evalIDs;
    private BigInteger evalCreateIndex;

    @JsonProperty("NodeModifyIndex")
    public BigInteger getNodeModifyIndex() {
        return nodeModifyIndex;
    }

    public NodeDrainUpdateResponse setNodeModifyIndex(BigInteger nodeModifyIndex) {
        this.nodeModifyIndex = nodeModifyIndex;
        return this;
    }

    @JsonProperty("EvalIDs")
    public List<String> getEvalIDs() {
        return evalIDs;
    }

    public NodeDrainUpdateResponse setEvalIDs(List<String> evalIDs) {
        this.evalIDs = evalIDs;
        return this;
    }

    public NodeDrainUpdateResponse addEvalIDs(String... evalIDs) {
        if (this.evalIDs == null)
            this.evalIDs = new java.util.ArrayList<>();
        for (String item : evalIDs)
            this.evalIDs.add(item);
        return this;
    }

    @JsonProperty("EvalCreateIndex")
    public BigInteger getEvalCreateIndex() {
        return evalCreateIndex;
    }

    public NodeDrainUpdateResponse setEvalCreateIndex(BigInteger evalCreateIndex) {
        this.evalCreateIndex = evalCreateIndex;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeDrainUpdateResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeDrainUpdateResponse.class);
    }

    public static List<NodeDrainUpdateResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeDrainUpdateResponse.class);
    }
}
