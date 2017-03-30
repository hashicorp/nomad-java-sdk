package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Allocation;
import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.testutils.TestAgent;
import org.hamcrest.Matchers;
import org.junit.Test;

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

            String evalID = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent).getId();

            ServerQueryResponse<List<AllocationListStub>> updatedResponse = allocationsApi.list();
            assertUpdatedServerQueryResponse(updatedResponse);
            assertThat(updatedResponse.getValue(), not(empty()));
            assertThat(updatedResponse.getValue(), hasItem(Matchers.<AllocationListStub>hasProperty("evalId", is(evalID))));
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
            assertThat(infoResponse.getValue().getId(), is(allocationId));
            assertThat(infoResponse.getValue().getEvalId(), is(evalID));
        }
    }

}
