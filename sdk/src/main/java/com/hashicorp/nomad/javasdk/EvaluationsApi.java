package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Evaluation;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static com.hashicorp.nomad.javasdk.NomadPredicates.evaluationHasCompleted;
import static com.hashicorp.nomad.javasdk.NomadPredicates.responseValue;

/**
 * API for querying for information about evaluations,
 * exposing the functionality of the {@code /v1/evaluations} and {@code /v1/evaluation} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class EvaluationsApi extends ApiBase {

    static final Comparator<Evaluation> NEWEST_TO_OLDEST_EVALUATIONS = new Comparator<Evaluation>() {
        @Override
        public int compare(Evaluation o1, Evaluation o2) {
            return o2.getCreateIndex().compareTo(o1.getCreateIndex());
        }
    };

    EvaluationsApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Queries an evaluation in the active region.
     *
     * @param evaluationId ID of the evaluation to lookup
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/eval.html">{@code GET /v1/evaluation/{ID}}</a>
     */
    public ServerQueryResponse<Evaluation> info(String evaluationId) throws IOException, NomadException {
        return info(evaluationId, null);
    }

    /**
     * Queries an evaluation in the active region.
     *
     * @param evaluationId ID of the evaluation to lookup
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/eval.html">{@code GET /v1/evaluation/{ID}}</a>
     */
    public ServerQueryResponse<Evaluation> info(
            String evaluationId,
            @Nullable QueryOptions<Evaluation> options) throws IOException, NomadException {
        return executeServerQuery("/v1/evaluation/" + evaluationId, options, NomadJson.parserFor(Evaluation.class));
    }

    /**
     * Lists evaluations in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists evaluations in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> list(
            @Nullable QueryOptions<List<Evaluation>> options
    ) throws IOException, NomadException {
        return list(null, options);
    }

    /**
     * Lists evaluations in the active region.
     *
     * @param evaluationIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only evaluations having an ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> list(@Nullable String evaluationIdPrefix)
            throws IOException, NomadException {
        return list(evaluationIdPrefix, null);
    }

    /**
     * Lists evaluations in the active region.
     *
     * @param evaluationIdPrefix an even-length prefix that, if given,
     *                           restricts the results to only evaluations having an ID with this prefix
     * @param options            options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> list(
            @Nullable String evaluationIdPrefix,
            @Nullable QueryOptions<List<Evaluation>> options
    ) throws IOException, NomadException {
        return executeServerQueryForPrefixFilteredList(
                "/v1/evaluations",
                evaluationIdPrefix,
                options,
                NomadJson.parserForSortedListOf(Evaluation.class, NEWEST_TO_OLDEST_EVALUATIONS));
    }

    /**
     * Lists allocations created or modified an evaluation in the active region.
     *
     * @param evaluationId ID of the evaluation that created or modified the allocations
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluation/<ID>/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(String evaluationId)
            throws IOException, NomadException {
        return allocations(evaluationId, null);
    }

    /**
     * Lists allocations created or modified an evaluation in the active region.
     *
     * @param evaluationId ID of the evaluation that created or modified the allocations
     * @param options      options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/evals.html">{@code GET /v1/evaluation/<ID>/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(
            String evaluationId,
            @Nullable QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/evaluation/" + evaluationId + "/allocations",
                options,
                NomadJson.parserForListOf(AllocationListStub.class));
    }

    /**
     * Poll the server until an evaluation has completed.
     *
     * @param evaluationId ID of the evaluation to poll for
     * @param waitStrategy the wait strategy to use during polling
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     */
    public ServerQueryResponse<Evaluation> pollForCompletion(
            String evaluationId,
            WaitStrategy waitStrategy
    ) throws IOException, NomadException {
        return info(evaluationId,
                QueryOptions.pollRepeatedlyUntil(
                        responseValue(evaluationHasCompleted()),
                        waitStrategy));
    }

    /**
     * Poll the server until an evaluation has completed.
     *
     * @param evaluation   an EvaluationResponse containing the ID of the evaluation to poll for
     * @param waitStrategy the wait strategy to use during polling
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     */
    public ServerQueryResponse<Evaluation> pollForCompletion(EvaluationResponse evaluation, WaitStrategy waitStrategy)
            throws IOException, NomadException {
        return pollForCompletion(evaluation.getValue(), waitStrategy);
    }

}
