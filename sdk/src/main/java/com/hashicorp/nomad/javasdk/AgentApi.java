package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.apimodel.AgentSelf;
import com.hashicorp.nomad.apimodel.ServerMembers;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * API for Nomad agent and cluster management,
 * exposing the functionality of the {@code /v1/agent/…} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class AgentApi extends ApiBase {

    AgentApi(final NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Queries for information about the agent we are connected to.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-self.html">{@code GET /v1/agent/self}</a>
     */
    @Nonnull
    public final NomadResponse<AgentSelf> self() throws IOException, NomadException {
        return executePlain(get("/v1/agent/self"), NomadJson.parserFor(AgentSelf.class));
    }

    /**
     * Queries for the known peers in the gossip pool.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-members.html">{@code GET /v1/agent/members}</a>
     */
    @Nonnull
    public final NomadResponse<ServerMembers> members() throws IOException, NomadException {
        return executePlain(get("/v1/agent/members"), NomadJson.parserFor(ServerMembers.class));
    }

    /**
     * Forces a member of the gossip pool from the "failed" state into the "left" state.
     *
     * @param nodeName the name of the node to force out of the pool
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-force-leave.html">{@code PUT /v1/agent/force-leave}</a>
     */
    @Nonnull
    public final NomadResponse<Void> forceLeave(String nodeName) throws IOException, NomadException {
        return executePlain(put(uri("/v1/agent/force-leave").addParameter("node", nodeName), null), null);
    }

    /**
     * Queries an agent in client mode for its list of known servers.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-servers.html#get">{@code GET /v1/agent/servers}</a>
     */
    @Nonnull
    public final NomadResponse<List<String>> servers() throws IOException, NomadException {
        return executePlain(get("/v1/agent/servers"), NomadJson.parserForListOf(String.class));
    }

    /**
     * Updates the list of known servers to the given addresses, replacing all previous addresses.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-servers.html#put-post">{@code PUT /v1/agent/servers}</a>
     */
    @Nonnull
    public final NomadResponse<Void> setServers(String... addresses) throws IOException, NomadException {
        return setServers(Arrays.asList(addresses));
    }

    /**
     * Updates the list of known servers to the given addresses, replacing all previous addresses.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-servers.html#put-post">{@code PUT /v1/agent/servers}</a>
     */
    @Nonnull
    public final NomadResponse<Void> setServers(Iterable<String> addresses) throws IOException, NomadException {
        final URIBuilder uri = uri("/v1/agent/servers");
        for (String address : addresses)
            uri.addParameter("address", address);
        return executePlain(put(uri, null), null);
    }

    /**
     * Causes the agent to join a cluster by joining the gossip pool at one of the given addresses.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-join.html">{@code PUT /v1/agent/join}</a>
     */
    @Nonnull
    public final NomadResponse<Integer> join(String... addresses) throws IOException, NomadException {
        return join(Arrays.asList(addresses));
    }

    /**
     * Causes the agent to join a cluster by joining the gossip pool at one of the given addresses.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/agent-join.html">{@code PUT /v1/agent/join}</a>
     */
    @Nonnull
    public final NomadResponse<Integer> join(Iterable<String> addresses) throws IOException, NomadException {
        final URIBuilder uri = uri("/v1/agent/join");
        for (String address : addresses)
            uri.addParameter("address", address);
        return executePlain(put(uri, null), new JoinResponseValueExtractor());
    }

    /**
     * Private type representing raw responses from the /v1/agent/join endpoint.
     */
    private static final class JoinResponse {
        @JsonProperty("error")
        private String error;
        @JsonProperty("num_joined")
        private int numberJoined;
    }

    /**
     * A value extractor that converts a @{link JoinResponse} into an Integer,
     * or throwing an exception if it signals an error.
     */
    private static class JoinResponseValueExtractor implements ValueExtractor<Integer> {
        private final JsonParser<JoinResponse> parser = NomadJson.parserFor(JoinResponse.class);

        @Override
        public Integer extractValue(String json) throws ResponseParsingException, ErrorFoundInResponseEntityException {
            JoinResponse joinResponse = parser.extractValue(json);
            if (joinResponse.error != null && !joinResponse.error.isEmpty())
                throw new ErrorFoundInResponseEntityException(joinResponse.error);
            return joinResponse.numberJoined;
        }
    }
}
