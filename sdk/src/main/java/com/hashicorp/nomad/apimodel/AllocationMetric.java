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
public final class AllocationMetric extends ApiObject {
    private int nodesEvaluated;
    private int nodesFiltered;
    private Map<String, Integer> nodesAvailable;
    private Map<String, Integer> classFiltered;
    private Map<String, Integer> constraintFiltered;
    private int nodesExhausted;
    private Map<String, Integer> classExhausted;
    private Map<String, Integer> dimensionExhausted;
    private List<String> quotaExhausted;
    private Map<String, Double> scores;
    private long allocationTime;
    private int coalescedFailures;

    @JsonProperty("NodesEvaluated")
    public int getNodesEvaluated() {
        return nodesEvaluated;
    }

    public AllocationMetric setNodesEvaluated(int nodesEvaluated) {
        this.nodesEvaluated = nodesEvaluated;
        return this;
    }

    @JsonProperty("NodesFiltered")
    public int getNodesFiltered() {
        return nodesFiltered;
    }

    public AllocationMetric setNodesFiltered(int nodesFiltered) {
        this.nodesFiltered = nodesFiltered;
        return this;
    }

    @JsonProperty("NodesAvailable")
    public Map<String, Integer> getNodesAvailable() {
        return nodesAvailable;
    }

    public AllocationMetric setNodesAvailable(Map<String, Integer> nodesAvailable) {
        this.nodesAvailable = nodesAvailable;
        return this;
    }

    public AllocationMetric addNodesAvailable(String key, int value) {
        if (this.nodesAvailable == null)
            this.nodesAvailable = new java.util.HashMap<>();
        this.nodesAvailable.put(key, value);
        return this;
    }

    @JsonProperty("ClassFiltered")
    public Map<String, Integer> getClassFiltered() {
        return classFiltered;
    }

    public AllocationMetric setClassFiltered(Map<String, Integer> classFiltered) {
        this.classFiltered = classFiltered;
        return this;
    }

    public AllocationMetric addClassFiltered(String key, int value) {
        if (this.classFiltered == null)
            this.classFiltered = new java.util.HashMap<>();
        this.classFiltered.put(key, value);
        return this;
    }

    @JsonProperty("ConstraintFiltered")
    public Map<String, Integer> getConstraintFiltered() {
        return constraintFiltered;
    }

    public AllocationMetric setConstraintFiltered(Map<String, Integer> constraintFiltered) {
        this.constraintFiltered = constraintFiltered;
        return this;
    }

    public AllocationMetric addConstraintFiltered(String key, int value) {
        if (this.constraintFiltered == null)
            this.constraintFiltered = new java.util.HashMap<>();
        this.constraintFiltered.put(key, value);
        return this;
    }

    @JsonProperty("NodesExhausted")
    public int getNodesExhausted() {
        return nodesExhausted;
    }

    public AllocationMetric setNodesExhausted(int nodesExhausted) {
        this.nodesExhausted = nodesExhausted;
        return this;
    }

    @JsonProperty("ClassExhausted")
    public Map<String, Integer> getClassExhausted() {
        return classExhausted;
    }

    public AllocationMetric setClassExhausted(Map<String, Integer> classExhausted) {
        this.classExhausted = classExhausted;
        return this;
    }

    public AllocationMetric addClassExhausted(String key, int value) {
        if (this.classExhausted == null)
            this.classExhausted = new java.util.HashMap<>();
        this.classExhausted.put(key, value);
        return this;
    }

    @JsonProperty("DimensionExhausted")
    public Map<String, Integer> getDimensionExhausted() {
        return dimensionExhausted;
    }

    public AllocationMetric setDimensionExhausted(Map<String, Integer> dimensionExhausted) {
        this.dimensionExhausted = dimensionExhausted;
        return this;
    }

    public AllocationMetric addDimensionExhausted(String key, int value) {
        if (this.dimensionExhausted == null)
            this.dimensionExhausted = new java.util.HashMap<>();
        this.dimensionExhausted.put(key, value);
        return this;
    }

    @JsonProperty("QuotaExhausted")
    public List<String> getQuotaExhausted() {
        return quotaExhausted;
    }

    public AllocationMetric setQuotaExhausted(List<String> quotaExhausted) {
        this.quotaExhausted = quotaExhausted;
        return this;
    }

    public AllocationMetric addQuotaExhausted(String... quotaExhausted) {
        if (this.quotaExhausted == null)
            this.quotaExhausted = new java.util.ArrayList<>();
        for (String item : quotaExhausted)
            this.quotaExhausted.add(item);
        return this;
    }

    @JsonProperty("Scores")
    public Map<String, Double> getScores() {
        return scores;
    }

    public AllocationMetric setScores(Map<String, Double> scores) {
        this.scores = scores;
        return this;
    }

    public AllocationMetric addScores(String key, double value) {
        if (this.scores == null)
            this.scores = new java.util.HashMap<>();
        this.scores.put(key, value);
        return this;
    }

    @JsonProperty("AllocationTime")
    public long getAllocationTime() {
        return allocationTime;
    }

    public AllocationMetric setAllocationTime(long allocationTime) {
        this.allocationTime = allocationTime;
        return this;
    }

    @JsonProperty("CoalescedFailures")
    public int getCoalescedFailures() {
        return coalescedFailures;
    }

    public AllocationMetric setCoalescedFailures(int coalescedFailures) {
        this.coalescedFailures = coalescedFailures;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static AllocationMetric fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, AllocationMetric.class);
    }

    public static List<AllocationMetric> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, AllocationMetric.class);
    }
}
