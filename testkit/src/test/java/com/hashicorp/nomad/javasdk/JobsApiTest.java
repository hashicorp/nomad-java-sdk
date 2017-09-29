package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Evaluation;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.JobListStub;
import com.hashicorp.nomad.apimodel.JobPlanResponse;
import com.hashicorp.nomad.apimodel.JobSummary;
import com.hashicorp.nomad.apimodel.PeriodicConfig;
import com.hashicorp.nomad.testutils.TestAgent;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class JobsApiTest extends ApiTestBase {

    @Test
    public void shouldRegisterANewJob() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            assertThatNoJobsHaveBeenRun(jobsApi);

            Job job = createTestJob();
            EvaluationResponse registrationResponse = jobsApi.register(job);
            assertUpdatedServerResponse(registrationResponse);
            assertThat("evaluation ID", registrationResponse.getValue(), is(nonEmptyString()));

            ServerQueryResponse<List<JobListStub>> updatedListResponse = jobsApi.list();
            assertUpdatedServerQueryResponse(updatedListResponse);
            List<JobListStub> updatedJobs = updatedListResponse.getValue();
            assertThat("jobs", updatedJobs, hasSize(1));
            assertThat("id", updatedJobs.get(0).getId(), is(job.getId()));
        }
    }

    private ServerQueryResponse<List<JobListStub>> assertThatNoJobsHaveBeenRun(JobsApi jobsApi) throws Exception {
        ServerQueryResponse<List<JobListStub>> listResponse = jobsApi.list();
        assertPristineServerQueryResponse(listResponse);
        assertThat("jobs", listResponse.getValue(), is(empty()));
        return listResponse;
    }

    @Test
    public void shouldRegisterNewJobWithIndexEnforcement() throws Exception {
        try (TestAgent agent = newServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            final Job job = createTestJob();

            new ErrorResponseAssertion("Enforcing job modify index") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.register(job, BigInteger.valueOf(10));
                }
            };

            assertThatNoJobsHaveBeenRun(jobsApi);

            EvaluationResponse registrationResponse = jobsApi.register(job, BigInteger.ZERO);
            assertUpdatedServerResponse(registrationResponse);
            assertThat("evaluation ID", registrationResponse.getValue(), is(nonEmptyString()));

            ServerQueryResponse<List<JobListStub>> updatedListResponse = jobsApi.list();
            assertUpdatedServerQueryResponse(updatedListResponse);
            List<JobListStub> updatedJobs = updatedListResponse.getValue();
            assertThat("jobs", updatedJobs, hasSize(1));
            assertThat("id", updatedJobs.get(0).getId(), is(job.getId()));
            BigInteger currentIndex = updatedJobs.get(0).getJobModifyIndex();

            new ErrorResponseAssertion("Enforcing job modify index") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.register(job, BigInteger.valueOf(123456));
                }
            };

            EvaluationResponse enforcedUpdateResponse = jobsApi.register(job, currentIndex);
            assertUpdatedServerResponse(enforcedUpdateResponse);
        }
    }

    @Test
    public void shouldGetJobInfo() throws Exception {
        try (TestAgent agent = newServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            final Job job = createTestJob();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.info(job.getId());
                }
            };

            jobsApi.register(job);

            ServerQueryResponse<Job> infoResponse = jobsApi.info(job.getId());
            assertUpdatedServerQueryResponse(infoResponse);
            assertThat("id", infoResponse.getValue().getId(), is(job.getId()));
        }
    }

    @Test
    public void shouldHandleJobIdsContainingAnyNonSpaceCharacter() throws Exception {
        try (TestAgent agent = newServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            final String id = "Who/谁-would_use'such\\Ā\"scaryID?eh=%20&\0";
            Job job = createTestJob().setId(id);

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.info(id);
                }
            };

            registerTestJobAndPollUntilEvaluationCompletes(agent, job);

            ServerQueryResponse<Job> infoResponse = jobsApi.info(id);
            assertUpdatedServerQueryResponse(infoResponse);
            assertThat("id", infoResponse.getValue().getId(), is(id));
        }
    }

    @Test
    public void shouldListingJobsHavingIdPrefix() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            ServerQueryResponse<List<JobListStub>> listResponse = jobsApi.list("dummy");
            assertPristineServerQueryResponse(listResponse);
            List<JobListStub> jobs = listResponse.getValue();
            assertThat("jobs", jobs, is(empty()));

            Job job = createTestJob();
            EvaluationResponse registrationResponse = jobsApi.register(job);
            assertUpdatedServerResponse(registrationResponse);

            ServerQueryResponse<List<JobListStub>> updatedListResponse = jobsApi.list(job.getId().substring(0, 1));
            assertUpdatedServerQueryResponse(updatedListResponse);
            List<JobListStub> updatedJobs = updatedListResponse.getValue();
            assertThat("jobs", updatedJobs, hasSize(1));
            assertThat("id", updatedJobs.get(0).getId(), is(job.getId()));
        }
    }

    @Test
    public void shouldListAllocationsBelongingToAJob() throws Exception {
        try (TestAgent agent = newClientServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            Job job = createTestJob();
            String evalId = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent, job).getId();

            ServerQueryResponse<List<AllocationListStub>> response = jobsApi.allocations(job.getId());
            assertUpdatedServerQueryResponse(response);
            assertThat(response.getValue(), not(empty()));
            AllocationListStub allocation = response.getValue().get(0);
            assertThat(allocation.getId(), not(emptyOrNullString()));
            assertThat(allocation.getJobId(), is(job.getId()));
            assertThat(allocation.getEvalId(), is(evalId));
        }
    }

    @Test
    public void shouldListEvaluationsForAJob() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            Job job = createTestJob();

            ServerQueryResponse<List<Evaluation>> preRegistrationResponse = jobsApi.evaluations(job.getId());
            assertPristineServerQueryResponse(preRegistrationResponse);
            assertThat(preRegistrationResponse.getValue(), empty());

            String evalId = registerTestJobAndPollUntilEvaluationCompletes(agent, job).getId();

            ServerQueryResponse<List<Evaluation>> postRegistrationResponse = jobsApi.evaluations(job.getId());
            assertUpdatedServerQueryResponse(postRegistrationResponse);
            List<Evaluation> evaluations = postRegistrationResponse.getValue();
            assertThat(evaluations, not(empty()));
            Evaluation evaluation = evaluations.get(evaluations.size() - 1);
            assertThat(evaluation.getJobId(), is(job.getId()));
            assertThat(evaluation.getId(), is(evalId));
        }
    }

    @Test
    public void shouldDeregisterAnExistingJob() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            final Job job = createTestJob();
            registerTestJobAndPollUntilEvaluationCompletes(agent, job);

            Job retrievedJob = jobsApi.info(job.getId()).getValue();
            assertThat(retrievedJob.getStatus(), is("running"));

            EvaluationResponse dummyDeregisterResponse = jobsApi.deregister("nope");
            assertUpdatedServerResponse(dummyDeregisterResponse);
            Evaluation dummyEvaluation = agent.getApiClient().getEvaluationsApi().pollForCompletion(dummyDeregisterResponse, waitStrategyForTest()).getValue();
            assertThat(dummyEvaluation.getNextEval(), emptyOrNullString());
            assertThat(dummyEvaluation.getBlockedEval(), emptyOrNullString());

            retrievedJob = jobsApi.info(job.getId()).getValue();
            assertThat(retrievedJob.getStatus(), is("running"));

            EvaluationResponse deregisterResponse = jobsApi.deregister(job.getId());
            assertUpdatedServerResponse(deregisterResponse);
            assertThat("evaluation ID", deregisterResponse.getValue(), nonEmptyString());

            Evaluation evaluation = agent.getApiClient().getEvaluationsApi().pollForCompletion(deregisterResponse, waitStrategyForTest()).getValue();
            assertThat(evaluation.getNextEval(), emptyOrNullString());

            assertThat("job ID", jobsApi.info(job.getId()).getValue().getId(), nonEmptyString());

            EvaluationResponse deregisterAndPurgeResponse = jobsApi.deregister(job.getId(), true);
            assertUpdatedServerResponse(deregisterAndPurgeResponse);
            assertThat("evaluation ID", deregisterAndPurgeResponse.getValue(), nonEmptyString());

            new ErrorResponseAssertion("job not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.info(job.getId());
                }
            };
        }
    }

    @Test
    public void shouldForceEvaluationOfExistingJob() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            assertThatNoJobsHaveBeenRun(jobsApi);

            Job job = createTestJob();
            EvaluationResponse registrationResponse = jobsApi.register(job);
            assertUpdatedServerResponse(registrationResponse);

            EvaluationResponse evaluationResponse = jobsApi.forceEvaluate(job.getId());
            assertUpdatedServerResponse(evaluationResponse);
            String evalID = evaluationResponse.getValue();
            assertThat("evaluation ID", evalID, is(nonEmptyString()));

            ServerQueryResponse<List<Evaluation>> evaluationListResponse = jobsApi.evaluations(job.getId());
            assertUpdatedServerQueryResponse(evaluationListResponse);
            List<Evaluation> evaluations = evaluationListResponse.getValue();
            assertThat("evaluations", evaluations, hasItem(Matchers.<Evaluation>hasProperty("id", is(evalID))));
        }
    }

    @Test
    public void shouldForceRunOfPeriodJob() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            Job job = createTestJob()
                    .setPeriodic(new PeriodicConfig()
                            .setEnabled(true)
                            .setSpec("*/30 * * * *")
                            .setSpecType("cron")
                    );

            jobsApi.register(job);

            jobsApi.info(job.getId());

            EvaluationResponse forceResponse = jobsApi.periodicForce(job.getId());
            assertUpdatedServerResponse(forceResponse);
            String evalID = forceResponse.getValue();
            assertThat("evaluation ID", evalID, is(nonEmptyString()));

            ServerQueryResponse<Evaluation> evaluationInfoResponse = agent.getApiClient().getEvaluationsApi().info(evalID);
            assertUpdatedServerQueryResponse(evaluationInfoResponse);
            assertThat("id", evaluationInfoResponse.getValue().getId(), is(evalID));
        }
    }

    @Test
    public void shouldCreateJobPlan() throws Exception {
        try (TestAgent agent = newServer()) {
            JobsApi jobsApi = agent.getApiClient().getJobsApi();

            Job job = createTestJob();
            jobsApi.register(job);

            ServerResponse<JobPlanResponse> planResponse = jobsApi.plan(job, true);
            assertUpdatedServerResponse(planResponse);
            JobPlanResponse plan = planResponse.getValue();
            assertThat(plan.getJobModifyIndex(), greaterThan(BigInteger.ZERO));
            assertThat(plan.getDiff().getId(), nonEmptyString());
            assertThat(plan.getAnnotations(), is(notNullValue()));
            assertThat(plan.getCreatedEvals(), not(empty()));

            ServerResponse<JobPlanResponse> noDiffResponse = jobsApi.plan(job, false);
            assertUpdatedServerResponse(noDiffResponse);
            JobPlanResponse noDiffPlan = noDiffResponse.getValue();
            assertThat(noDiffPlan.getJobModifyIndex(), greaterThan(BigInteger.ZERO));
            assertThat(noDiffPlan.getDiff(), nullValue());
            assertThat(noDiffPlan.getAnnotations(), notNullValue());
            assertThat(noDiffPlan.getCreatedEvals(), not(empty()));
        }
    }

    @Test
    public void shouldGetJobSummary() throws Exception {
        try (TestAgent agent = newServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            final Job job = createTestJob();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return jobsApi.summary(job.getId());
                }
            };

            EvaluationResponse registerResponse = jobsApi.register(job);
            assertUpdatedServerResponse(registerResponse);

            ServerQueryResponse<JobSummary> jobSummaryResponse = jobsApi.summary(job.getId());
            assertUpdatedServerQueryResponse(jobSummaryResponse);
            JobSummary summary = jobSummaryResponse.getValue();
            assertThat(summary.getJobId(), is(job.getId()));
            assertThat(summary.getSummary(), hasKey(job.getTaskGroups().get(0).getName()));
        }
    }

}
