package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocStopResponse;
import com.hashicorp.nomad.apimodel.Allocation;
import com.hashicorp.nomad.apimodel.AllocationListStub;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about allocations,
 * exposing the functionality of the {@code /v1/allocations} and {@code /v1/allocation} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class AllocationsApi extends ApiBase {

    AllocationsApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Queries an allocation in the active region.
     *
     * @param id the allocation ID to lookup
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/alloc.html">{@code GET /v1/allocation/{ID}}</a>
     */
    public ServerQueryResponse<Allocation> info(String id) throws IOException, NomadException {
        return info(id, null);
    }

    /**
     * Queries an allocation in the active region.
     *
     * @param id      the allocation ID to lookup
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/alloc.html">{@code GET /v1/allocation/{ID}}</a>
     */
    public ServerQueryResponse<Allocation> info(
            String id,
            @Nullable QueryOptions<Allocation> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/allocation/" + id, options, NomadJson.parserFor(Allocation.class));
    }

    /**
     * Lists allocations in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/allocs.html">{@code GET /v1/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists allocations in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/allocs.html">{@code GET /v1/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> list(
            @Nullable QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {
        return list(null, options);
    }

    /**
     * Lists allocations in the active region.
     *
     * @param allocationIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only allocations having an ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/allocs.html">{@code GET /v1/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> list(@Nullable String allocationIdPrefix)
            throws IOException, NomadException {
        return list(allocationIdPrefix, null);
    }

    /**
     * Lists allocations in the active region.
     *
     * @param allocationIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only allocations having an ID with this prefix
     * @param options            options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/allocs.html">{@code GET /v1/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> list(
            @Nullable String allocationIdPrefix,
            @Nullable QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {
        return executeServerQueryForPrefixFilteredList(
                "/v1/allocations",
                allocationIdPrefix,
                options,
                NomadJson.parserForListOf(AllocationListStub.class));
    }

    /**
     * Stop and reschedules an allocation.
     * @param id the allocation ID to stop
     * @return allocation response, including the id of the follow-up evaluation for any rescheduled alloc.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     */
    public ServerResponse<AllocStopResponse> stop(String id) throws IOException, NomadException {
        return executeServerAction(put("/v1/allocation/" + id + "/stop", null),
                NomadJson.parserFor(AllocStopResponse.class));
    }

    /**
     * Class matching the JSON request entity for allocation signaling.
     */
    private static class AllocSignalRequest {
        public final String signal; // Checkstyle suppress VisibilityModifier
        public final String task; // Checkstyle suppress VisibilityModifier

        AllocSignalRequest(String signal, @Nullable String task) {
            this.signal = signal;
            this.task = task;
        }
    }


    /**
     * Sends a signal to an allocation or task.
     * @param id the allocation ID to stop
     * @param signal the signal to send
     * @param task the name of the task, required if the task group has more than one task
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     */
    public void signal(String id, String signal, @Nullable String task) throws IOException, NomadException {
        executeServerAction(
                put("/v1/client/allocation/" + id + "/signal", new AllocSignalRequest(signal, task), null),
                null);
    }
}
