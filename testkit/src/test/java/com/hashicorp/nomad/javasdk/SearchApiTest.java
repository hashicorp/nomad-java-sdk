package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.JobListStub;
import com.hashicorp.nomad.apimodel.SearchResponse;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchApiTest extends ApiTestBase {

    @Test
    public void shouldListAssets() throws Exception {
        try (TestAgent agent = newServer()) {
            SearchApi searchApi = agent.getApiClient().getSearchApi();

            Job job = createTestJob();
            agent.getApiClient().getJobsApi().register(job);

            String jobId = job.getId();
            ServerQueryResponse<SearchResponse> response =
                    searchApi.prefixSearch(jobId.substring(0, jobId.length() - 2), "jobs", null);
            assertUpdatedServerQueryResponse(response);

            final List<String> jobMatches = response.getValue().getMatches().get("jobs");
            assertThat(jobMatches, hasSize(1));
            assertThat(jobMatches.get(0), is(jobId));
        }
    }
}
