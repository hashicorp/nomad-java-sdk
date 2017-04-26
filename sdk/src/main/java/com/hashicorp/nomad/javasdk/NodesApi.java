package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Node;
import com.hashicorp.nomad.apimodel.NodeListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about client nodes,
 * exposing the functionality of the {@code /v1/nodes} and {@code /v1/node} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class NodesApi extends ApiBase {

    NodesApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * List the allocations belonging to a nodes in the active region.
     *
     * @param nodeId ID of the node to list allocations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code GET /v1/node/{ID}/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(String nodeId) throws IOException, NomadException {
        return allocations(nodeId, null);
    }

    /**
     * List the allocations belonging to a nodes in the active region.
     *
     * @param nodeId  ID of the node to list allocations for
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code GET /v1/node/{ID}/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(
            String nodeId,
            @Nullable QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/node/" + nodeId + "/allocations",
                options,
                NomadJson.parserForListOf(AllocationListStub.class));
    }

    /**
     * Creates a new evaluation for a node.
     *
     * @param nodeId ID of the node to evaluate
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code PUT /v1/node/{ID}/evaluate}</a>
     */
    public ServerResponse<Void> forceEvaluate(String nodeId) throws IOException, NomadException {
        return forceEvaluate(nodeId, null);
    }

    /**
     * Creates a new evaluation for a node.
     *
     * @param nodeId  ID of the node to evaluate
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code PUT /v1/node/{ID}/evaluate}</a>
     */
    public ServerResponse<Void> forceEvaluate(String nodeId, @Nullable WriteOptions options)
            throws IOException, NomadException {
        return executeServerAction(put("/v1/node/" + nodeId + "/evaluate", options), null);
    }

    /**
     * Queries a node in the active region.
     *
     * @param nodeId ID of the node to query
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code GET /v1/node/{ID}}</a>
     */
    public ServerQueryResponse<Node> info(String nodeId) throws IOException, NomadException {
        return info(nodeId, null);
    }

    /**
     * Queries a node in the active region.
     *
     * @param nodeId  ID of the node to query
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html">{@code GET /v1/node/{ID}}</a>
     */
    public ServerQueryResponse<Node> info(String nodeId, @Nullable QueryOptions<Node> options)
            throws IOException, NomadException {
        return executeServerQuery("/v1/node/" + nodeId, options, NomadJson.parserFor(Node.class));
    }

    /**
     * Lists client nodes in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/nodes.html">{@code GET /v1/nodes}</a>
     */
    public ServerQueryResponse<List<NodeListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists client nodes in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/nodes.html">{@code GET /v1/nodes}</a>
     */
    public ServerQueryResponse<List<NodeListStub>> list(
            @Nullable QueryOptions<List<NodeListStub>> options
    ) throws IOException, NomadException {
        return list(null, options);
    }

    /**
     * Lists client nodes in the active region.
     *
     * @param nodeIdPrefix an even-length prefix that, if given,
     *                     restricts the results to only nodes having an ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/nodes.html">{@code GET /v1/nodes}</a>
     */
    public ServerQueryResponse<List<NodeListStub>> list(@Nullable String nodeIdPrefix)
            throws IOException, NomadException {
        return list(nodeIdPrefix, null);
    }

    /**
     * Lists client nodes in the active region.
     *
     * @param nodeIdPrefix an even-length prefix that, if given,
     *                     restricts the results to only nodes having an ID with this prefix
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/nodes.html">{@code GET /v1/nodes}</a>
     */
    public ServerQueryResponse<List<NodeListStub>> list(
            @Nullable String nodeIdPrefix,
            @Nullable QueryOptions<List<NodeListStub>> options
    ) throws IOException, NomadException {
        return executeServerQueryForPrefixFilteredList(
                "/v1/nodes",
                nodeIdPrefix,
                options,
                NomadJson.parserForListOf(NodeListStub.class));
    }

    /**
     * Toggles drain mode on or off on a node in the active region.
     * <p>
     * When drain mode is enabled, no further allocations will be assigned and existing allocations will be migrated.
     *
     * @param nodeId  ID of the node to control
     * @param enabled drain mode is turned on when this is true, and off when false.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html#put-post">{@code PUT /v1/node/{ID}/drain}</a>
     */
    public EvaluationResponse toggleDrain(String nodeId, boolean enabled) throws IOException, NomadException {
        return toggleDrain(nodeId, enabled, null);
    }

    /**
     * Toggles drain mode on or off on a node in the active region.
     * <p>
     * When drain mode is enabled, no further allocations will be assigned and existing allocations will be migrated.
     *
     * @param nodeId  ID of the node to control
     * @param enabled drain mode is turned on when this is true, and off when false.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/node.html#put-post">{@code PUT /v1/node/{ID}/drain}</a>
     */
    public EvaluationResponse toggleDrain(
            String nodeId,
            boolean enabled,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {
        return executeEvaluationCreatingRequest(put(
                uri("/v1/node/" + nodeId + "/drain")
                        .addParameter("enable", Boolean.toString(enabled)),
                options
        ));
    }
}
