package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.*;
import com.hashicorp.nomad.testutils.TestAgent;
import org.hamcrest.*;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AllocationsApiTest extends ApiTestBase {

    @Test
    public void shouldListAllocations() throws Exception {
        try (TestAgent agent = newClientServer()) {
            AllocationsApi allocationsApi = agent.getApiClient().getAllocationsApi();

            ServerQueryResponse<List<AllocationListStub>> response = allocationsApi.list();
            assertPristineServerQueryResponse(response);
            assertThat("allocations", response.getValue(), empty());

            Evaluation eval = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent);

            ServerQueryResponse<List<AllocationListStub>> updatedResponse = allocationsApi.list();
            assertUpdatedServerQueryResponse(updatedResponse);
            List<AllocationListStub> list = updatedResponse.getValue();
            assertThat(list, not(empty()));
            assertThat(list, everyItem(Matchers.<AllocationListStub>hasProperty("evalId", is(eval.getId()))));
            assertThat(list, everyItem(Matchers.<AllocationListStub>hasProperty("namespace", is("default"))));
            assertThat(list, everyItem(Matchers.<AllocationListStub>hasProperty("nodeName", nonEmptyString())));
            assertThat(list, everyItem(Matchers.<AllocationListStub>hasProperty("jobType", is(eval.getType()))));
        }
    }

    @Test
    public void shouldListAllocationsHavingPrefix() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final AllocationsApi allocationsApi = agent.getApiClient().getAllocationsApi();

            new ErrorResponseAssertion("must be even length") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return allocationsApi.list("f00");
                }
            };
            new ErrorResponseAssertion("Invalid UUID") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return allocationsApi.list("ok");
                }
            };

            ServerQueryResponse<List<AllocationListStub>> response = allocationsApi.list("f00d");
            assertPristineServerQueryResponse(response);
            assertThat("allocations", response.getValue(), empty());

            String evalID = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent).getId();

            ServerQueryResponse<List<AllocationListStub>> allResponse = allocationsApi.list("");
            assertUpdatedServerQueryResponse(allResponse);
            assertThat(allResponse.getValue(), hasSize(1));
            AllocationListStub allocationSummary = allResponse.getValue().get(0);
            assertThat(allocationSummary.getEvalId(), is(evalID));

            String existingPrefix = allocationSummary.getId().substring(0, 4);
            ServerQueryResponse<List<AllocationListStub>> prefixedResponse = allocationsApi.list(existingPrefix);
            assertUpdatedServerQueryResponse(prefixedResponse);
            assertThat(prefixedResponse.getValue(), hasSize(1));

            String nonExistingPrefix = "0000".equals(existingPrefix) ? "0001" : "0000";
            ServerQueryResponse<List<AllocationListStub>> nonExistingResponse = allocationsApi.list(nonExistingPrefix);
            assertUpdatedServerQueryResponse(nonExistingResponse);
            assertThat(nonExistingResponse.getValue(), empty());
        }
    }

    @Test
    public void shouldRetrieveAllocationInfo() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final AllocationsApi allocationsApi = agent.getApiClient().getAllocationsApi();

            new ErrorResponseAssertion("alloc not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return allocationsApi.info(UUID.randomUUID().toString());
                }
            };

            String evalID = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent).getId();

            ServerQueryResponse<List<AllocationListStub>> response = allocationsApi.list();
            assertThat("allocations", response.getValue(), not(empty()));
            String allocationId = response.getValue().get(0).getId();

            ServerQueryResponse<Allocation> infoResponse = allocationsApi.info(allocationId);
            assertUpdatedServerQueryResponse(infoResponse);
            Allocation alloc = infoResponse.getValue();
            assertThat(alloc.getId(), is(allocationId));
            assertThat(alloc.getEvalId(), is(evalID));
            assertThat(alloc.getMetrics(), notNullValue());
            assertThat(alloc.getMetrics().getScoreMetaData(), not(empty()));
            assertThat(alloc.getNodeName(), nonEmptyString());

            assertThat(alloc.getAllocatedResources().getShared().getDiskMb(), greaterThan(0l));
            assertThat(alloc.getAllocatedResources().getTasks().get("task1").getCpu().getCpuShares(), is(100l));
            assertThat(alloc.getAllocatedResources().getTasks().get("task1").getMemory().getMemoryMb(), is(256l));
        }
    }

    @Test
    public void shouldStopAllocation() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final AllocationsApi allocationsApi = agent.getApiClient().getAllocationsApi();

            String evalID = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent).getId();

            new ErrorResponseAssertion("allocation not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return allocationsApi.stop(UUID.randomUUID().toString());
                }
            };

            ServerQueryResponse<List<AllocationListStub>> response = allocationsApi.list();
            assertThat("allocations", response.getValue(), not(empty()));
            String allocationId = response.getValue().get(0).getId();

            ServerResponse<AllocStopResponse> stopResponse = allocationsApi.stop(allocationId);
            assertUpdatedServerResponse(stopResponse);
            assertThat(stopResponse.getValue().getEvalId(), not(evalID));
        }
    }

    @Test
    public void shouldSignalAllocation() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final AllocationsApi allocationsApi = agent.getApiClient().getAllocationsApi();

            Job testJob = createTestJob();
            testJob.getTaskGroups().get(0).getTasks().get(0).addConfig("run_for", "5s");
            registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent, testJob).getId();

            new ErrorResponseAssertion("Unknown allocation") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    allocationsApi.signal(UUID.randomUUID().toString(), "SIGUSR1", null);
                    return null;
                }
            };

            ServerQueryResponse<List<AllocationListStub>> response = allocationsApi.list();
            assertThat("allocations", response.getValue(), not(empty()));
            final String allocationId = response.getValue().get(0).getId();

            new ErrorResponseAssertion("Task not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    allocationsApi.signal(allocationId, "SIGUSR1", "non-existent-task");
                    return null;
                }
            };

            allocationsApi.info(allocationId, QueryOptions.pollRepeatedlyUntil(
                    NomadPredicates.responseValue(new Predicate<Allocation>() {
                        @Override
                        public boolean apply(@Nonnull Allocation allocation) {
                            return allocation.getTaskStates() != null &&
                                    allocation.getTaskStates().get("task1") != null &&
                                    allocation.getTaskStates().get("task1").getState().equals("running");
                        }
                    }),
                    waitStrategyForTest()
            ));

            allocationsApi.signal(allocationId, "SIGUSR1", null);

            final Matcher<Iterable<? super TaskEvent>> hasSignalEvent = hasItem(new TypeSafeMatcher<TaskEvent>() {
                @Override
                public void describeTo(Description description) {
                    description.appendText("signaling event");
                }

                @Override
                protected boolean matchesSafely(TaskEvent taskEvent) {
                    return taskEvent.getType().equals("Signaling") &&
                            taskEvent.getDetails().get("signal").equals("SIGUSR1");
                }
            });

            // this throws an exception if we time out waiting for the signal task event that we are expecting
            ServerQueryResponse<Allocation> info = allocationsApi.info(allocationId, QueryOptions.pollRepeatedlyUntil(
                    NomadPredicates.responseValue(new Predicate<Allocation>() {
                        @Override
                        public boolean apply(@Nonnull Allocation allocation) {
                            return hasSignalEvent.matches(allocation.getTaskStates().get("task1").getEvents());
                        }
                    }),
                    waitStrategyForTest()
            ));
        }
    }

}
