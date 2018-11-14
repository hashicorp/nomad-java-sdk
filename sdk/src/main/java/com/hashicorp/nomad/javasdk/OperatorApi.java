package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AutopilotConfiguration;
import com.hashicorp.nomad.apimodel.OperatorHealthReply;
import com.hashicorp.nomad.apimodel.RaftConfiguration;
import com.hashicorp.nomad.apimodel.SchedulerConfiguration;
import org.apache.http.client.methods.RequestBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigInteger;

/**
 * API for operating a Nomad cluster,
 * exposing the <a href="https://www.nomadproject.io/api/operator.html">operator</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class OperatorApi extends ApiBase {

    OperatorApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Gets the cluster's Raft configuration.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-raft-configuration">{@code GET /v1/operator/raft/configuration}</a>
     */
    public NomadResponse<RaftConfiguration> raftGetConfiguration() throws IOException, NomadException {
        return raftGetConfiguration(null);
    }

    /**
     * Gets the health of the autopilot status.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-health">{@code GET /v1/operator/autopilot/health}</a>
     */
    public NomadResponse<OperatorHealthReply> getHealth() throws IOException, NomadException {
        return getHealth(null);
    }


    /**
     * Gets the cluster's Raft configuration.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-raft-configuration">{@code GET /v1/operator/raft/configuration}</a>
     */
    public NomadResponse<RaftConfiguration> raftGetConfiguration(
            @Nullable QueryOptions<RaftConfiguration> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/operator/raft/configuration",
                options,
                NomadJson.parserFor(RaftConfiguration.class));
    }

    /**
     * Gets the health of the autopilot status.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-health">{@code GET /v1/operator/autopilot/health}</a>
     */
    public NomadResponse<OperatorHealthReply> getHealth(
            @Nullable QueryOptions<OperatorHealthReply> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/operator/autopilot/health",
                options,
                NomadJson.parserFor(OperatorHealthReply.class));
    }

    /**
     * Removes a raft peer from the cluster.
     *
     * @param address ip:port address of the peer to remove
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#remove-raft-peer">{@code DELETE /v1/operator/raft/peer}</a>
     */
    public NomadResponse<Void> raftRemovePeerByAddress(String address)
            throws IOException, NomadException {

        return raftRemovePeerByAddress(address, null);
    }

    /**
     * Removes a raft peer from the cluster.
     *
     * @param address ip:port address of the peer to remove
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#remove-raft-peer">{@code DELETE /v1/operator/raft/peer}</a>
     */
    public NomadResponse<Void> raftRemovePeerByAddress(String address, @Nullable WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(
                delete(
                        uri("/v1/operator/raft/peer").addParameter("address", address),
                        options
                ),
                null
        );
    }

    /**
     * Gets the autopilot configuration.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-autopilot-configuration">{@code GET /v1/operator/autopilot/configuration}</a>
     */
    public NomadResponse<AutopilotConfiguration> getAutopilotConfiguration() throws IOException, NomadException {
        return getAutopilotConfiguration(null);
    }

    /**
     * Gets the autopilot configuration.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-autopilot-configuration">{@code GET /v1/operator/autopilot/configuration}</a>
     */
    public NomadResponse<AutopilotConfiguration> getAutopilotConfiguration(
            @Nullable QueryOptions<AutopilotConfiguration> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/operator/autopilot/configuration",
                options,
                NomadJson.parserFor(AutopilotConfiguration.class));
    }

    /**
     * Updates the autopilot configuration.
     *
     * @param autopilotConfiguration the desired autopilot configuration
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#update-autopilot-configuration">{@code PUT /v1/operator/autopilot/configuration}</a>
     */
    public NomadResponse<Boolean> updateAutopilotConfiguration(
            AutopilotConfiguration autopilotConfiguration
    ) throws IOException, NomadException {
        return updateAutopilotConfiguration(autopilotConfiguration, null);
    }

    /**
     * Updates the autopilot configuration.
     *
     * @param autopilotConfiguration the desired autopilot configuration
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#update-autopilot-configuration">{@code PUT /v1/operator/autopilot/configuration}</a>
     */
    public NomadResponse<Boolean> updateAutopilotConfiguration(
            AutopilotConfiguration autopilotConfiguration,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {
        return updateAutopilotConfiguration(autopilotConfiguration, options, null);
    }

    /**
     * Updates the autopilot configuration.
     *
     * @param autopilotConfiguration the desired autopilot configuration
     * @param options options controlling how the request is performed
     * @param cas if not null, use check-and-set semantics on the update
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#update-autopilot-configuration">{@code PUT /v1/operator/autopilot/configuration}</a>
     */
    public NomadResponse<Boolean> updateAutopilotConfiguration(
            AutopilotConfiguration autopilotConfiguration,
            @Nullable WriteOptions options,
            @Nullable BigInteger cas
        ) throws IOException, NomadException {
        RequestBuilder builder = put(
                uri("/v1/operator/autopilot/configuration"),
                autopilotConfiguration,
                options
        );
        if (cas != null) {
            builder.addParameter("cas", cas.toString());
        }
        return executeServerAction(
                builder,
                NomadJson.parserFor(Boolean.class)
        );
    }

    /**
     * Gets the scheduler configuration.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-scheduler-configuration">{@code GET /v1/operator/scheduler/configuration}</a>
     */
    public NomadResponse<SchedulerConfiguration> getSchedulerConfiguration(
            @Nullable QueryOptions<SchedulerConfiguration> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/operator/scheduler/configuration",
                options,
                NomadJson.parserFor(SchedulerConfiguration.class));
    }

    /**
     * Updates the scheduler configuration.
     *
     * @param schedulerConfiguration the desired scheduler configuration
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#update-scheduler-configuration">{@code PUT /v1/operator/scheduler/configuration}</a>
     */
    public NomadResponse<Boolean> updateSchedulerConfiguration(
            SchedulerConfiguration schedulerConfiguration
    ) throws IOException, NomadException {
        return updateSchedulerConfiguration(schedulerConfiguration, null, null);
    }

    /**
     * Updates the scheduler configuration.
     *
     * @param schedulerConfiguration the desired scheduler configuration
     * @param options options controlling how the request is performed
     * @param cas if not null, use check-and-set semantics on the update
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#update-scheduler-configuration">{@code PUT /v1/operator/scheduler/configuration}</a>
     */
    public NomadResponse<Boolean> updateSchedulerConfiguration(
            SchedulerConfiguration schedulerConfiguration,
            @Nullable WriteOptions options,
            @Nullable BigInteger cas
    ) throws IOException, NomadException {
        RequestBuilder builder = put(
                uri("/v1/operator/scheduler/configuration"),
                schedulerConfiguration,
                options
        );
        if (cas != null) {
            builder.addParameter("cas", cas.toString());
        }
        return executeServerAction(
                builder,
                NomadJson.parserFor(Boolean.class)
        );
    }
}
