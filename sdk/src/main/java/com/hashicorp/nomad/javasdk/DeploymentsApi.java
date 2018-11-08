package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Deployment;
import com.hashicorp.nomad.apimodel.DeploymentUpdateResponse;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * API for querying for information about deployments,
 * exposing the <a href="https://www.nomadproject.io/api/deployments.html">deployments</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class DeploymentsApi extends ApiBase {

    static final Comparator<Deployment> NEWEST_TO_OLDEST_DEPLOYMENTS = new Comparator<Deployment>() {
        @Override
        public int compare(Deployment o1, Deployment o2) {
            return o2.getCreateIndex().compareTo(o1.getCreateIndex());
        }
    };

    DeploymentsApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Lists deployments in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-deployments">{@code GET /v1/deployments}</a>
     */
    public ServerQueryResponse<List<Deployment>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists deployments in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-deployments">{@code GET /v1/deployments}</a>
     */
    public ServerQueryResponse<List<Deployment>> list(
            @Nullable QueryOptions<List<Deployment>> options
    ) throws IOException, NomadException {
        return list(null, options);
    }

    /**
     * Lists deployments in the active region.
     *
     * @param deploymentIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only deployments having an ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-deployments">{@code GET /v1/deployments}</a>
     */
    public ServerQueryResponse<List<Deployment>> list(
            @Nullable String deploymentIdPrefix
    ) throws IOException, NomadException {
        return list(deploymentIdPrefix, null);
    }

    /**
     * Lists deployments in the active region.
     *
     * @param deploymentIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only deployments having an ID with this prefix
     * @param options            options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-deployments">{@code GET /v1/deployments}</a>
     */
    public ServerQueryResponse<List<Deployment>> list(
            @Nullable String deploymentIdPrefix,
            @Nullable QueryOptions<List<Deployment>> options
    ) throws IOException, NomadException {
        return executeServerQueryForPrefixFilteredList(
                "/v1/deployments",
                deploymentIdPrefix,
                options,
                NomadJson.parserForSortedListOf(Deployment.class, NEWEST_TO_OLDEST_DEPLOYMENTS));
    }


    /**
     * Queries a deployment in the active region.
     *
     * @param deploymentId ID of the deployment to lookup
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#read-deployment">{@code GET /v1/deployment/{ID}}</a>
     */
    public ServerQueryResponse<Deployment> info(String deploymentId) throws IOException, NomadException {
        return info(deploymentId, null);
    }

    /**
     * Queries a deployment in the active region.
     *
     * @param deploymentId ID of the deployment to lookup
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#read-deployment">{@code GET /v1/deployment/{ID}}</a>
     */
    public ServerQueryResponse<Deployment> info(
            String deploymentId,
            @Nullable QueryOptions<Deployment> options) throws IOException, NomadException {
        return executeServerQuery("/v1/deployment/" + deploymentId, options, NomadJson.parserFor(Deployment.class));
    }

    /**
     * Lists the allocations belonging to a deployment in the active region.
     *
     * @param deploymentId ID of the deployment to list allocations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-allocations-for-deployment">{@code GET /v1/deployment/allocations/<ID>}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(final String deploymentId)
            throws IOException, NomadException {

        return allocations(deploymentId, null);
    }

    /**
     * Lists the allocations belonging to a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#list-allocations-for-deployment">{@code GET /v1/deployment/allocations/<ID>}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(
            final String deploymentId,
            @Nullable final QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/deployment/allocations/" + deploymentId,
                options,
                NomadJson.parserForListOf(AllocationListStub.class));
    }

    /**
     * Fails a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#fail-deployment">{@code PUT /v1/deployment/fail/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> fail(String deploymentId) throws IOException, NomadException {
        return fail(deploymentId, null);
    }

    /**
     * Fails a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#fail-deployment">{@code PUT /v1/deployment/fail/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> fail(String deploymentId, @Nullable WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(
                put("/v1/deployment/fail/" + deploymentId, new DeploymentSpecificRequest(deploymentId), options),
                NomadJson.parserFor(DeploymentUpdateResponse.class)
        );
    }

    /**
     * Pauses or un-pauses a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param pause        true if the deployment should be paused, false if it should be un-paused
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#pause-deployment">{@code PUT /v1/deployment/pause/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> pause(String deploymentId, boolean pause)
            throws IOException, NomadException {

        return pause(deploymentId, pause, null);
    }

    /**
     * Pauses or un-pauses a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param pause        true if the deployment should be paused, false if it should be un-paused
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#pause-deployment">{@code PUT /v1/deployment/pause/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> pause(
            String deploymentId, boolean pause,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {

        return executeServerAction(
                put("/v1/deployment/pause/" + deploymentId,
                        new DeploymentPauseRequest(deploymentId, pause),
                        options
                ),
                NomadJson.parserFor(DeploymentUpdateResponse.class)
        );
    }

    /**
     * Promotes all the canaries in a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#promote-deployment">{@code PUT /v1/deployment/promote/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> promoteAll(String deploymentId) throws IOException, NomadException {
        return promoteAll(deploymentId, null);
    }

    /**
     * Promotes all the canaries in a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#promote-deployment">{@code PUT /v1/deployment/promote/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> promoteAll(String deploymentId, @Nullable WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(
                put("/v1/deployment/promote/" + deploymentId,
                        new DeploymentPromoteRequest(deploymentId, true, null),
                        options),
                NomadJson.parserFor(DeploymentUpdateResponse.class)
        );
    }

    /**
     * Promotes the canaries in the provided groups of a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param groups       the groups whose canaries should be promoted
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#promote-deployment">{@code PUT /v1/deployment/promote/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> promoteGroups(
            String deploymentId,
            List<String> groups
    ) throws IOException, NomadException {
        return promoteGroups(deploymentId, groups, null);
    }

    /**
     * Promotes the canaries in the provided groups of a deployment in the active region.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param groups       the groups whose canaries should be promoted
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#promote-deployment">{@code PUT /v1/deployment/promote/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> promoteGroups(
            String deploymentId,
            List<String> groups,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {

        return executeServerAction(
                put("/v1/deployment/promote/" + deploymentId,
                        new DeploymentPromoteRequest(deploymentId, false, groups),
                        options),
                NomadJson.parserFor(DeploymentUpdateResponse.class)
        );
    }

    /**
     * Sets the health of allocations that are part of a deployment.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param healthy      ids of allocations to be set healthy
     * @param unhealthy    ids of allocations to be set unhealthy
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#set-allocation-health-in-deployment">{@code PUT /v1/deployment/allocation-health/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> setAllocHealth(
            String deploymentId,
            List<String> healthy,
            List<String> unhealthy
    ) throws IOException, NomadException {
        return setAllocHealth(deploymentId, healthy, unhealthy, null);
    }

    /**
     * Sets the health of allocations that are part of a deployment.
     *
     * @param deploymentId the ID of the deployment to list allocations for
     * @param healthy      ids of allocations to be set healthy
     * @param unhealthy    ids of allocations to be set unhealthy
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/deployments.html#set-allocation-health-in-deployment">{@code PUT /v1/deployment/allocation-health/<ID>}</a>
     */
    public ServerResponse<DeploymentUpdateResponse> setAllocHealth(
            String deploymentId,
            List<String> healthy,
            List<String> unhealthy,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {

        return executeServerAction(
                put("/v1/deployment/allocation-health/" + deploymentId,
                        new DeploymentAllocHealthRequest(deploymentId, healthy, unhealthy),
                        options),
                NomadJson.parserFor(DeploymentUpdateResponse.class)
        );
    }

    /**
     * Class matching the JSON request entity for allocation health setting requests.
     */
    private static final class DeploymentAllocHealthRequest {
        @JsonProperty("DeploymentID")
        public final String deploymentId;  // Checkstyle suppress VisibilityModifier
        @JsonProperty("HealthyAllocationIDs")
        public final List<String> healthyAllocationIds;  // Checkstyle suppress VisibilityModifier
        @JsonProperty("UnHealthyAllocationIDs")
        public final List<String> unHealthyAllocationIds;  // Checkstyle suppress VisibilityModifier

        private DeploymentAllocHealthRequest(
                String deploymentId, List<String> healthyAllocationIds, List<String> unHealthyAllocationIds) {
            this.deploymentId = deploymentId;
            this.healthyAllocationIds = healthyAllocationIds;
            this.unHealthyAllocationIds = unHealthyAllocationIds;
        }
    }

    /**
     * Class matching the JSON request entity for promotion requests.
     */
    private static final class DeploymentPromoteRequest {
        @JsonProperty("DeploymentID")
        public final String deploymentId;  // Checkstyle suppress VisibilityModifier
        public final boolean all;  // Checkstyle suppress VisibilityModifier
        public final List<String> groups;  // Checkstyle suppress VisibilityModifier

        private DeploymentPromoteRequest(String deploymentId, boolean all, List<String> groups) {
            this.deploymentId = deploymentId;
            this.all = all;
            this.groups = groups;
        }
    }

    /**
     * Class matching the JSON request entity for pause requests.
     */
    private static final class DeploymentPauseRequest {
        @JsonProperty("DeploymentID")
        public final String deploymentId;  // Checkstyle suppress VisibilityModifier
        public final boolean pause;  // Checkstyle suppress VisibilityModifier

        private DeploymentPauseRequest(String deploymentId, boolean pause) {
            this.deploymentId = deploymentId;
            this.pause = pause;
        }
    }


    /**
     * Class matching the JSON request entity for requests expecting just a deployment ID to be posted.
     */
    private static final class DeploymentSpecificRequest {
        @JsonProperty("DeploymentID")
        public final String deploymentId;  // Checkstyle suppress VisibilityModifier

        private DeploymentSpecificRequest(String deploymentId) {
            this.deploymentId = deploymentId;
        }
    }

}

