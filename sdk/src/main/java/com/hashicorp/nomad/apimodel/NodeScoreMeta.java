package com.hashicorp.nomad.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.javasdk.ApiObject;
import com.hashicorp.nomad.javasdk.NomadJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a generated JavaBean representing a request or response structure.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a> documentation associated with the endpoint you are using.
 */
public final class NodeScoreMeta extends ApiObject {
    private String nodeId;
    private Map<String, Double> scores;
    private double normScore;

    @JsonProperty("NodeID")
    public String getNodeId() {
        return nodeId;
    }

    public NodeScoreMeta setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("Scores")
    public Map<String, Double> getScores() {
        return scores;
    }

    public NodeScoreMeta setScores(Map<String, Double> scores) {
        this.scores = scores;
        return this;
    }

    public NodeScoreMeta addScores(String key, double value) {
        if (this.scores == null)
            this.scores = new java.util.HashMap<>();
        this.scores.put(key, value);
        return this;
    }

    @JsonProperty("NormScore")
    public double getNormScore() {
        return normScore;
    }

    public NodeScoreMeta setNormScore(double normScore) {
        this.normScore = normScore;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static NodeScoreMeta fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, NodeScoreMeta.class);
    }

    public static List<NodeScoreMeta> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, NodeScoreMeta.class);
    }
}
