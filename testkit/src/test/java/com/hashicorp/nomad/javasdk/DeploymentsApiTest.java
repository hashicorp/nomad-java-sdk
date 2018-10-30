package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Deployment;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.UpdateStrategy;
import com.hashicorp.nomad.testutils.TestAgent;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class DeploymentsApiTest extends ApiTestBase {

    @Test
    public void shouldDiscoverDeployment() throws Exception {
        try (TestAgent agent = newServer()) {
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();
            final DeploymentsApi deploymentsApi = agent.getApiClient().getDeploymentsApi();

            Job job = createTestJob().setType("service").setUpdate(new UpdateStrategy().setCanary(1));
            jobsApi.register(job);

            List<Deployment> all = deploymentsApi.list("").getValue();
            assertThat( all, Matchers.<Deployment>hasSize(1));
            List<Deployment> fromJob = jobsApi.deployments(job.getId()).getValue();
            assertThat( fromJob, Matchers.<Deployment>hasSize(1));
            assertThat( fromJob.get(0).getId(), is(all.get(0).getId()) );
        }
    }

    @Test
    public void handleDeficientUseCases() throws Exception {
        try (TestAgent agent = newServer()) {
            final DeploymentsApi deploymentsApi = agent.getApiClient().getDeploymentsApi();
            final JobsApi jobsApi = agent.getApiClient().getJobsApi();

            ServerQueryResponse<List<Deployment>> list = deploymentsApi.list("");
            assertThat( list.getValue(), Matchers.<Deployment>hasSize(0));

            new ErrorResponseAssertion("deployment not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return deploymentsApi.info("f6004f6b-a58d-835f-5acf-3ad349000835");
                }
            };

            assertThat( deploymentsApi.allocations("f6004f6b-a58d-835f-5acf-3ad349000835").getValue(), Matchers.<AllocationListStub>hasSize(0));

            assertThat( jobsApi.latestDeployment("nonexistent-job").getValue(), is(nullValue()));
        }
    }

}
