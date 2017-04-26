package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Evaluation;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.JobDispatchResponse;
import com.hashicorp.nomad.apimodel.JobListStub;
import com.hashicorp.nomad.apimodel.JobPlanResponse;
import com.hashicorp.nomad.apimodel.JobSummary;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * API for managing and querying jobs,
 * exposing the functionality of the {@code /v1/jobs} and {@code /v1/job} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/json-jobs.html">Job Specification</a>
 * for documentation about the {@link Job} structure.
 */
public class JobsApi extends ApiBase {

    JobsApi(final NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Lists the allocations belonging to a job in the active region.
     *
     * @param jobId ID of the job to list allocations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/<ID>/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(final String jobId)
            throws IOException, NomadException {

        return allocations(jobId, null);
    }

    /**
     * Lists the allocations belonging to a job in the active region.
     *
     * @param jobId   the ID of the job to list allocations for
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/<ID>/allocations}</a>
     */
    public ServerQueryResponse<List<AllocationListStub>> allocations(
            final String jobId,
            @Nullable final QueryOptions<List<AllocationListStub>> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/job/" + jobId + "/allocations",
                options,
                NomadJson.parserForListOf(AllocationListStub.class));
    }

    /**
     * Deregisters a job in the active region, and stops all allocations that are part of it.
     *
     * @param jobId ID of the job to deregister
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html#delete">{@code DELETE /v1/job/<ID>}</a>
     */
    public EvaluationResponse deregister(final String jobId) throws IOException, NomadException {
        return deregister(jobId, null);
    }

    /**
     * Deregisters a job in the active region,
     * and stops all allocations that are part of it.
     *
     * @param jobId   the ID of the job to deregister
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html#delete">{@code DELETE /v1/job/<ID>}</a>
     */
    public EvaluationResponse deregister(final String jobId, @Nullable final WriteOptions options)
            throws IOException, NomadException {

        return executeEvaluationCreatingRequest(delete("/v1/job/" + jobId, options));
    }

    /**
     * Dispatches a new instance of a parameterized job in the active region.
     *
     * @param jobId   id of the parameterized job to instantiate
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public ServerResponse<JobDispatchResponse> dispatch(final String jobId) throws IOException, NomadException {
        return dispatch(jobId, null, null);
    }

    /**
     * Dispatches a new instance of a parameterized job in the active region.
     *
     * @param jobId   id of the parameterized job to instantiate
     * @param payload payload for the instantiated job
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public ServerResponse<JobDispatchResponse> dispatch(
            final String jobId,
            final byte[] payload
    ) throws IOException, NomadException {
        return dispatch(jobId, null, payload);
    }

    /**
     * Dispatches a new instance of a parameterized job in the active region.
     *
     * @param jobId   id of the parameterized job to instantiate
     * @param meta    metadata for the instantiated job
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public ServerResponse<JobDispatchResponse> dispatch(
            final String jobId,
            @Nullable final Map<String, String> meta
    ) throws IOException, NomadException {
        return dispatch(jobId, meta, null);
    }

    /**
     * Registers or updates a job in the active region.
     *
     * Dispatches a new instance of a parameterized job in the active region.
     *
     * @param jobId   id of the parameterized job to instantiate
     * @param meta    metadata for the instantiated job
     * @param payload payload for the instantiated job
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public ServerResponse<JobDispatchResponse> dispatch(
            final String jobId,
            @Nullable final Map<String, String> meta,
            @Nullable final byte[] payload
    ) throws IOException, NomadException {
        return dispatch(jobId, meta, payload, null);
    }

    /**
     * Dispatches a new instance of a parameterized job in the active region.
     *
     * @param jobId   id of the parameterized job to instantiate
     * @param meta    metadata for the instantiated job
     * @param payload payload for the instantiated job
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public ServerResponse<JobDispatchResponse> dispatch(
            final String jobId,
            @Nullable final Map<String, String> meta,
            @Nullable final byte[] payload,
            @Nullable WriteOptions options
    ) throws IOException, NomadException {
        return executeServerAction(
                put("/v1/job/" + jobId + "/evaluate", new JobDispatchRequest(jobId, meta, payload), options),
                NomadJson.parserFor(JobDispatchResponse.class));
    }

    /**
     * Lists the evaluations belonging to a job in the active region.
     *
     * @param jobId ID of the job to list evaluations for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/<ID>/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> evaluations(final String jobId)
            throws IOException, NomadException {

        return evaluations(jobId, null);
    }

    /**
     * Lists the evaluations belonging to a job in the active region.
     *
     * @param jobId   the ID of the job to list evaluations for
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/<ID>/evaluations}</a>
     */
    public ServerQueryResponse<List<Evaluation>> evaluations(
            final String jobId,
            @Nullable final QueryOptions<List<Evaluation>> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/job/" + jobId + "/evaluations",
                options,
                NomadJson.parserForSortedListOf(Evaluation.class, EvaluationsApi.NEWEST_TO_OLDEST_EVALUATIONS));
    }

    /**
     * Creates a new evaluation for a job in the active region.
     * <p>
     * This can be used to force run the scheduling logic if necessary.
     *
     * @param jobId ID of the job to evaluate
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/<ID>/evaluate}</a>
     */
    public EvaluationResponse forceEvaluate(final String jobId) throws IOException, NomadException {
        return forceEvaluate(jobId, null);
    }

    /**
     * Creates a new evaluation for a job in the active region.
     * <p>
     * This can be used to force run the scheduling logic if necessary.
     *
     * @param jobId   the ID of the job to evaluate
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/<ID>/evaluate}</a>
     */
    public EvaluationResponse forceEvaluate(
            final String jobId,
            @Nullable final WriteOptions options) throws IOException, NomadException {

        return executeEvaluationCreatingRequest(put("/v1/job/" + jobId + "/evaluate", options));
    }

    /**
     * Queries a job in the active region.
     *
     * @param jobId ID of the job to query
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/{ID}}</a>
     */
    public ServerQueryResponse<Job> info(final String jobId) throws IOException, NomadException {
        return info(jobId, null);
    }

    /**
     * Queries a job in the active region.
     *
     * @param jobId   the ID of the job to query
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/{ID}}</a>
     */
    public ServerQueryResponse<Job> info(
            final String jobId,
            @Nullable final QueryOptions<Job> options
    ) throws IOException, NomadException {
        return executeServerQuery("/v1/job/" + jobId, options, NomadJson.parserFor(Job.class));
    }

    /**
     * Lists jobs in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<JobListStub>> list() throws IOException, NomadException {
        return list(null, null);
    }

    /**
     * Lists jobs in the active region.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<JobListStub>> list(
            @Nullable final QueryOptions<List<JobListStub>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists jobs in the active region.
     *
     * @param jobIdPrefix an even-length prefix that, if given,
     *                    restricts the results to only jobs having an ID with this prefix
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<JobListStub>> list(@Nullable final String jobIdPrefix)
            throws IOException, NomadException {

        return list(jobIdPrefix, null);
    }

    /**
     * Lists jobs in the active region.
     *
     * @param jobIdPrefix an even-length prefix that, if given,
     *                    restricts the results to only jobs having an ID with this prefix
     * @param options     options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<JobListStub>> list(
            @Nullable final String jobIdPrefix,
            @Nullable final QueryOptions<List<JobListStub>> options
    ) throws IOException, NomadException {

        return executeServerQueryForPrefixFilteredList(
                "/v1/jobs",
                jobIdPrefix,
                options,
                NomadJson.parserForListOf(JobListStub.class));
    }

    /**
     * Forces a new instance of a periodic job in the active region.
     * <p>
     * A new instance will be created even if it violates the job's prohibit_overlap settings.
     * As such, this should be only used to immediately run a periodic job.
     *
     * @param jobId ID of the job to force a run of
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/{ID}/periodic/force}</a>
     */
    public EvaluationResponse periodicForce(String jobId) throws IOException, NomadException {
        return periodicForce(jobId, null);
    }

    /**
     * Forces a new instance of a periodic job in the active region.
     * <p>
     * A new instance will be created even if it violates the job's prohibit_overlap settings.
     * As such, this should be only used to immediately run a periodic job.
     *
     * @param jobId   the ID of the job to force a run of
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/{ID}/periodic/force}</a>
     */
    public EvaluationResponse periodicForce(String jobId, @Nullable WriteOptions options)
            throws IOException, NomadException {
        return executeEvaluationCreatingRequest(
                put("/v1/job/" + jobId + "/periodic/force", options));
    }

    /**
     * Invokes a dry-run of the scheduler for a job in the active region.
     * <p>
     * Can be used together with the modifyIndex parameter of {@link #register(Job, BigInteger) register}
     * to inspect what will happen before registering a job.
     *
     * @param job  detailed specification of the job to plan for
     * @param diff indicates whether a diff between the current and submitted versions of the job
     *             should be included in the response.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/intro/getting-started/jobs.html#modifying-a-job">Modifying a Job</a>
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/{ID}/periodic/force}</a>
     */
    public ServerResponse<JobPlanResponse> plan(Job job, boolean diff) throws IOException, NomadException {
        return plan(job, diff, null);
    }

    /**
     * Invokes a dry-run of the scheduler for a job in the active region.
     * <p>
     * Can be used together with the modifyIndex parameter of {@link #register(Job, BigInteger) register}
     * to inspect what will happen before registering a job.
     *
     * @param job     detailed specification of the job to plan for
     * @param diff    indicates whether a diff between the current and submitted versions of the job
     *                should be included in the response.
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/intro/getting-started/jobs.html#modifying-a-job">Modifying a Job</a>
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code PUT /v1/job/{ID}/periodic/force}</a>
     */
    public ServerResponse<JobPlanResponse> plan(
            Job job,
            boolean diff,
            @Nullable WriteOptions options) throws IOException, NomadException {
        return executeServerAction(
                put(uri("/v1/job/" + job.getId() + "/plan"), new JobPlanRequest(job, diff), options),
                NomadJson.parserFor(JobPlanResponse.class));
    }

    /**
     * Registers or updates a job in the active region.
     *
     * @param job detailed specification of the job to register
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public EvaluationResponse register(Job job) throws IOException, NomadException {
        return register(job, null, null);
    }

    /**
     * Registers or updates a job in the active region.
     *
     * @param job     detailed specification of the job to register
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public EvaluationResponse register(Job job, @Nullable WriteOptions options) throws IOException, NomadException {
        return register(job, null, options);
    }

    /**
     * Registers or updates a job in the active region.
     *
     * @param job         detailed specification of the job to register
     * @param modifyIndex when specified, the registration is only performed if the job's modify index matches.
     *                    This can be used to make sure the job hasn't changed since getting a
     *                    {@link #plan(Job, boolean) plan}.
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public EvaluationResponse register(Job job, @Nullable BigInteger modifyIndex) throws IOException, NomadException {
        return register(job, modifyIndex, null);
    }

    /**
     * Registers or updates a job in the active region.
     *
     * @param job         detailed specification of the job to register
     * @param modifyIndex when specified, the registration is only performed if the job's modify index matches.
     *                    This can be used to make sure the job hasn't changed since getting a
     *                    {@link #plan(Job, boolean) plan}.
     * @param options     options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/jobs.html#put-post">{@code PUT /v1/jobs}</a>
     */
    public EvaluationResponse register(Job job,
                                       @Nullable BigInteger modifyIndex,
                                       @Nullable WriteOptions options) throws IOException, NomadException {
        return executeEvaluationCreatingRequest(
                put("/v1/jobs", new JobRegistrationRequest(job, modifyIndex), options));
    }

    /**
     * Queries the summary of a job in the active region.
     *
     * @param jobId ID of the job to get a summary for
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/{ID}/summary}</a>
     */
    public ServerQueryResponse<JobSummary> summary(String jobId) throws IOException, NomadException {
        return summary(jobId, null);
    }

    /**
     * Queries the summary of a job in the active region.
     *
     * @param jobId   ID of the job to get a summary for
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/job.html">{@code GET /v1/job/{ID}/summary}</a>
     */
    public ServerQueryResponse<JobSummary> summary(String jobId, @Nullable QueryOptions<JobSummary> options)
            throws IOException, NomadException {
        return executeServerQuery(
                "/v1/job/" + jobId + "/summary",
                options,
                NomadJson.parserFor(JobSummary.class));
    }

    private EvaluationResponse executeEvaluationCreatingRequest(RequestBuilder request)
            throws IOException, NomadException {
        return apiClient.execute(request, new ResponseAdapter<String, EvaluationResponse>(new ValueExtractor<String>() {
            private final JsonParser<EvalIdResponse> evalIdParser = NomadJson.parserFor(EvalIdResponse.class);

            @Override
            public String extractValue(String json) throws ResponseParsingException {
                return evalIdParser.extractValue(json).evalID;
            }
        }) {
            @Override
            protected EvaluationResponse buildResponse(HttpResponse httpResponse,
                                                       String rawEntity,
                                                       @Nonnull String value) {
                return new EvaluationResponse(httpResponse, rawEntity, value);
            }
        });
    }

    /**
     * Class matching the JSON request entity for job dispatch requests.
     */
    private static class JobDispatchRequest {
        @JsonProperty("JobID")
        public final String jobId; // Checkstyle suppress VisibilityModifier
        public final Map<String, String> meta; // Checkstyle suppress VisibilityModifier
        public final byte[] payload; // Checkstyle suppress VisibilityModifier

        JobDispatchRequest(String jobId, @Nullable Map<String, String> meta, @Nullable byte[] payload) {
            this.jobId = jobId;
            this.meta = meta;
            this.payload = payload;
        }
    }

    /**
     * Class matching the JSON request entity for job plan requests.
     */
    @SuppressFBWarnings(
            value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
            justification = "The fields are read via reflection during JSON serialization"
    )
    private static class JobPlanRequest {
        public Job job; // Checkstyle suppress VisibilityModifier
        public final boolean diff; // Checkstyle suppress VisibilityModifier

        JobPlanRequest(Job job, boolean diff) {
            this.job = job;
            this.diff = diff;
        }
    }

    /**
     * Class matching the JSON request entity for job registration requests.
     */
    private static class JobRegistrationRequest {
        public final Job job; // Checkstyle suppress VisibilityModifier
        public final Boolean enforceIndex; // Checkstyle suppress VisibilityModifier
        public final BigInteger jobModifyIndex; // Checkstyle suppress VisibilityModifier

        JobRegistrationRequest(Job job, @Nullable BigInteger jobModifyIndex) {
            this.job = job;
            this.enforceIndex = jobModifyIndex != null;
            this.jobModifyIndex = jobModifyIndex;
        }
    }

    /**
     * Class matching the JSON that wraps evaluation IDs in responses to evaluation-creating requests.
     */
    private static class EvalIdResponse {
        public String evalID; // Checkstyle suppress VisibilityModifier
    }

}
