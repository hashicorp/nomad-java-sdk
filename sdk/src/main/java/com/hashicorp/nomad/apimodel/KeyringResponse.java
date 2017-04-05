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
public final class KeyringResponse extends ApiObject {
    private Map<String, String> messages;
    private Map<String, Integer> keys;
    private int numNodes;

    @JsonProperty("Messages")
    public Map<String, String> getMessages() {
        return messages;
    }

    public KeyringResponse setMessages(Map<String, String> messages) {
        this.messages = messages;
        return this;
    }

    public KeyringResponse addMessages(String key, String value) {
        if (this.messages == null)
            this.messages = new java.util.HashMap<>();
        this.messages.put(key, value);
        return this;
    }

    @JsonProperty("Keys")
    public Map<String, Integer> getKeys() {
        return keys;
    }

    public KeyringResponse setKeys(Map<String, Integer> keys) {
        this.keys = keys;
        return this;
    }

    public KeyringResponse addKeys(String key, int value) {
        if (this.keys == null)
            this.keys = new java.util.HashMap<>();
        this.keys.put(key, value);
        return this;
    }

    @JsonProperty("NumNodes")
    public int getNumNodes() {
        return numNodes;
    }

    public KeyringResponse setNumNodes(int numNodes) {
        this.numNodes = numNodes;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static KeyringResponse fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, KeyringResponse.class);
    }

    public static List<KeyringResponse> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, KeyringResponse.class);
    }
}
