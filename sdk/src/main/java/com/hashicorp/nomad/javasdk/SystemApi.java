package com.hashicorp.nomad.javasdk;


import javax.annotation.Nullable;
import java.io.IOException;

/**
 * API for performing system maintenance that shouldn't be necessary for most users,
 * exposing the functionality of the
 * <a href="https://www.nomadproject.io/docs/http/system.html">{@code /v1/system/…} endpoints</a>
 * of the <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class SystemApi extends ApiBase {

    SystemApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Initiates garbage collection of jobs, evals, allocations and nodes in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/gc}</a>
     */
    public NomadResponse<Void> garbageCollect() throws IOException, NomadException {
        return garbageCollect(null);
    }

    /**
     * Initiates garbage collection of jobs, evals, allocations and nodes
     * in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/gc}</a>
     */
    public NomadResponse<Void> garbageCollect(@Nullable WriteOptions options) throws IOException, NomadException {
        return executePlain(put(uri("/v1/system/gc"), options), null);
    }

    /**
     * Reconciles the summaries of all jobs in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/reconcile/summaries}</a>
     */
    public NomadResponse<Void> reconcileSummaries() throws IOException, NomadException {
        return reconcileSummaries(null);
    }

    /**
     * Reconciles the summaries of all jobs in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/system.html">{@code PUT /v1/system/reconcile/summaries}</a>
     */
    public NomadResponse<Void> reconcileSummaries(@Nullable WriteOptions options) throws IOException, NomadException {
        return executePlain(put(uri("/v1/system/reconcile/summaries"), options), null);
    }
}
