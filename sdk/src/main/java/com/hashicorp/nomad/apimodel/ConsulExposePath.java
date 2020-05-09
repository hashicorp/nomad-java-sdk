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
public final class ConsulExposePath extends ApiObject {
    private String path;
    private String protocol;
    private int localPathPort;
    private String listenerPort;

    @JsonProperty("Path")
    public String getPath() {
        return path;
    }

    public ConsulExposePath setPath(String path) {
        this.path = path;
        return this;
    }

    @JsonProperty("Protocol")
    public String getProtocol() {
        return protocol;
    }

    public ConsulExposePath setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    @JsonProperty("LocalPathPort")
    public int getLocalPathPort() {
        return localPathPort;
    }

    public ConsulExposePath setLocalPathPort(int localPathPort) {
        this.localPathPort = localPathPort;
        return this;
    }

    @JsonProperty("ListenerPort")
    public String getListenerPort() {
        return listenerPort;
    }

    public ConsulExposePath setListenerPort(String listenerPort) {
        this.listenerPort = listenerPort;
        return this;
    }

    @Override
    public String toString() {
        return NomadJson.serialize(this);
    }

    public static ConsulExposePath fromJson(String json) throws IOException {
        return NomadJson.deserialize(json, ConsulExposePath.class);
    }

    public static List<ConsulExposePath> fromJsonArray(String json) throws IOException {
        return NomadJson.deserializeList(json, ConsulExposePath.class);
    }
}
