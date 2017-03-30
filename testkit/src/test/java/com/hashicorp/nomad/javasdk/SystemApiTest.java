package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;

public class SystemApiTest extends ApiTestBase {

    @Test
    public void shouldTriggerGarbageCollection() throws Exception {
        try (TestAgent agent = newServer()) {
            final SystemApi systemApi = agent.getApiClient().getSystemApi();

            NomadResponse<Void> response = systemApi.garbageCollect();
            assertThat(response.getRawEntity(), emptyOrNullString());

            NomadResponse<Void> regionSpecificResponse = systemApi.garbageCollect(new WriteOptions("global"));
            assertThat(regionSpecificResponse.getRawEntity(), emptyOrNullString());

            new ErrorResponseAssertion("No path to region") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    System.err.println("Performing non-existent-region request");
                    return systemApi.garbageCollect(new WriteOptions("non-existent-region"));
                }
            };
        }
    }

    @Test
    public void shouldTriggerReconcilingSummaries() throws Exception {
        try (TestAgent agent = newServer()) {
            final SystemApi systemApi = agent.getApiClient().getSystemApi();

            NomadResponse<Void> response = systemApi.reconcileSummaries();
            assertThat(response.getRawEntity(), emptyOrNullString());

            NomadResponse<Void> regionSpecificResponse = systemApi.reconcileSummaries(new WriteOptions("global"));
            assertThat(regionSpecificResponse.getRawEntity(), emptyOrNullString());

            new ErrorResponseAssertion("No path to region") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return systemApi.reconcileSummaries(new WriteOptions("non-existent-region"));
                }
            };
        }
    }

}
