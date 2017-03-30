package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Evaluation;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.hashicorp.nomad.javasdk.NomadPredicates.responseValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class EvaluationsApiTest extends ApiTestBase {

    @Test
    public void shouldListEvaluationsFromNewestToOldest() throws Exception {
        try (TestAgent agent = newServer()) {
            EvaluationsApi evaluationsApi = agent.getApiClient().getEvaluationsApi();

            ServerQueryResponse<List<Evaluation>> initialResponse = evaluationsApi.list();
            assertPristineServerQueryResponse(initialResponse);
            assertThat(initialResponse.getValue(), is(empty()));

            String evalID = agent.getApiClient().getJobsApi().register(createTestJob()).getValue();

            ServerQueryResponse<List<Evaluation>> updatedResponse = evaluationsApi.list();
            assertUpdatedServerQueryResponse(updatedResponse);
            List<Evaluation> evaluations = updatedResponse.getValue();
            assertThat(evaluations, not(empty()));
            Evaluation originalEvaluation = evaluations.get(evaluations.size() - 1);
            assertThat(originalEvaluation.getId(), is(evalID));
        }
    }

    @Test
    public void shouldListEvaluationsWithPrefix() throws Exception {
        try (TestAgent agent = newServer()) {
            EvaluationsApi evaluationsApi = agent.getApiClient().getEvaluationsApi();

            ServerQueryResponse<List<Evaluation>> initialResponse = evaluationsApi.list("abcdef");
            assertPristineServerQueryResponse(initialResponse);
            assertThat(initialResponse.getValue(), is(empty()));

            String evalID = agent.getApiClient().getJobsApi().register(createTestJob()).getValue();

            ServerQueryResponse<List<Evaluation>> updatedResponse = evaluationsApi.list(evalID.substring(0, 4));
            assertUpdatedServerQueryResponse(updatedResponse);
            List<Evaluation> evaluations = updatedResponse.getValue();

            // if the eval fails fast there can be more than 1
            // but they are in order of most recent first, so look at the last one
            Evaluation evaluation = evaluations.get(evaluations.size() - 1);
            assertThat(evaluation.getId(), is(evalID));
        }
    }

    @Test
    public void shouldGetEvaluationInfo() throws Exception {
        try (TestAgent agent = newServer()) {
            final EvaluationsApi evaluationsApi = agent.getApiClient().getEvaluationsApi();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return evaluationsApi.info("8E231CF4-CA48-43FF-B694-5801E69E22FA");
                }
            };

            String evalID = agent.getApiClient().getJobsApi().register(createTestJob()).getValue();

            ServerQueryResponse<Evaluation> response = evaluationsApi.info(evalID);
            assertUpdatedServerQueryResponse(response);
            assertThat(response.getValue().getId(), is(evalID));
        }
    }

    @Test
    public void shouldListAllocationsForAnEvaluation() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final EvaluationsApi evaluationsApi = agent.getApiClient().getEvaluationsApi();

            ServerQueryResponse<List<AllocationListStub>> initialResponse = evaluationsApi.allocations("8E231CF4-CA48-43FF-B694-5801E69E22FA");
            assertPristineServerQueryResponse(initialResponse);
            assertThat(initialResponse.getValue(), is(empty()));

            final String evalID = agent.getApiClient().getJobsApi().register(createTestJob()).getValue();

            ServerQueryResponse<List<AllocationListStub>> response = evaluationsApi.allocations(evalID,
                    QueryOptions.pollRepeatedlyUntil(
                            responseValue(NomadPredicates.<AllocationListStub>nonEmpty()),
                            waitStrategyForTest()
                    )
            );
            assertUpdatedServerQueryResponse(response);
            assertThat(response.getValue(), not(empty()));
            assertThat(response.getValue().get(0).getEvalId(), is(evalID));
        }
    }

}
