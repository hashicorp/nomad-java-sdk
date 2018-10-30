package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.Node;
import com.hashicorp.nomad.apimodel.NodeListStub;
import com.hashicorp.nomad.testutils.NomadAgentConfiguration;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NodesApiTest extends ApiTestBase {

    @Test
    public void shouldListNodes() throws Exception {
        try (TestAgent agent = newAgent(new NomadAgentConfiguration.Builder().setClientEnabled(true).setName("especially-named"))) {
            NodesApi nodesApi = agent.getApiClient().getNodesApi();

            ServerQueryResponse<List<NodeListStub>> response = nodesApi.list();
            assertUpdatedServerQueryResponse(response);
            List<NodeListStub> nodes = response.getValue();
            assertThat(nodes, hasSize(1));
            assertThat(nodes.get(0).getName(), is("especially-named"));
        }
    }

    @Test
    public void shouldListNodesMatchingPrefix() throws Exception {
        try (TestAgent agent = newClientServer()) {
            NodesApi nodesApi = agent.getApiClient().getNodesApi();

            ServerQueryResponse<List<NodeListStub>> emptyPrefixResponse = nodesApi.list("");
            assertUpdatedServerQueryResponse(emptyPrefixResponse);
            assertThat(emptyPrefixResponse.getValue(), hasSize(1));
            NodeListStub node = emptyPrefixResponse.getValue().get(0);

            String prefix = node.getId().substring(0, 4);
            ServerQueryResponse<List<NodeListStub>> prefixResponse = nodesApi.list(prefix);
            assertUpdatedServerQueryResponse(prefixResponse);
            assertThat(prefixResponse.getValue(), hasSize(1));
            assertThat(prefixResponse.getValue().get(0).getId(), is(node.getId()));
            assertThat(prefixResponse.getValue().get(0).getName(), is(node.getName()));

            String otherPrefix = "0000".equals(prefix) ? "0001" : "0000";
            ServerQueryResponse<List<NodeListStub>> otherPrefixResponse = nodesApi.list(otherPrefix);
            assertUpdatedServerQueryResponse(otherPrefixResponse);
            assertThat(otherPrefixResponse.getValue(), empty());
        }
    }

    @Test
    public void shouldGetNodeInfo() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final NodesApi nodesApi = agent.getApiClient().getNodesApi();

            new ErrorResponseAssertion("node not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return nodesApi.info(NONSENSE_GUID);
                }
            };

            List<NodeListStub> nodes = nodesApi.list().getValue();
            assertThat(nodes, not(empty()));
            NodeListStub NodeListStub = nodes.get(0);

            ServerQueryResponse<Node> infoResponse = nodesApi.info(NodeListStub.getId());
            assertUpdatedServerQueryResponse(infoResponse);
            assertThat(infoResponse.getValue().getId(), is(NodeListStub.getId()));
            assertThat(infoResponse.getValue().getDatacenter(), is(NodeListStub.getDatacenter()));
            assertThat(infoResponse.getValue().getDrivers(), hasKey("mock_driver"));
        }
    }

    @Test
    public void shouldToggleDrainMode() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final NodesApi nodesApi = agent.getApiClient().getNodesApi();

            new ErrorResponseAssertion("node not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return nodesApi.toggleDrain(NONSENSE_GUID, true);
                }
            };

            List<NodeListStub> nodes = nodesApi.list().getValue();
            assertThat(nodes, not(empty()));
            String nodeId = nodes.get(0).getId();

            Node initialInfo = nodesApi.info(nodeId).getValue();
            assertThat("not initially draining", initialInfo.getDrain(), is(false));

            ServerResponse<Void> enableDrainResponse = nodesApi.toggleDrain(nodeId, true);
            assertUpdatedServerResponse(enableDrainResponse);

            Node drainingInfo = nodesApi.info(nodeId).getValue();
            assertThat("draining", drainingInfo.getDrain(), is(true));

            ServerResponse<Void> disableDrainResponse = nodesApi.toggleDrain(nodeId, false);
            assertUpdatedServerResponse(disableDrainResponse);

            Node finalInfo = nodesApi.info(nodeId).getValue();
            assertThat("not initially draining", finalInfo.getDrain(), is(false));
        }
    }

    @Test
    public void shouldListAllocations() throws Exception {
        try (TestAgent agent = newClientServer()) {
            NodesApi nodesApi = agent.getApiClient().getNodesApi();

            ServerQueryResponse<List<AllocationListStub>> nonsenseResponse = nodesApi.allocations(NONSENSE_GUID);
            assertUpdatedServerQueryResponse(nonsenseResponse);
            assertThat(nonsenseResponse.getValue(), empty());

            List<NodeListStub> nodes = nodesApi.list().getValue();
            assertThat(nodes, not(empty()));
            String nodeId = nodes.get(0).getId();

            ServerQueryResponse<List<AllocationListStub>> initialResponse = nodesApi.allocations(nodeId);
            assertUpdatedServerQueryResponse(initialResponse);
            assertThat(initialResponse.getValue(), empty());

            Job job = createTestJob();
            String evalId = registerTestJobAndPollUntilEvaluationCompletesSuccessfully(agent, job).getId();

            ServerQueryResponse<List<AllocationListStub>> allocationsReponse = nodesApi.allocations(nodeId);
            assertUpdatedServerQueryResponse(allocationsReponse);
            assertThat(allocationsReponse.getValue(), hasSize(1));
            AllocationListStub allocation = allocationsReponse.getValue().get(0);
            assertThat(allocation.getId(), not(emptyOrNullString()));
            assertThat(allocation.getJobId(), is(job.getId()));
            assertThat(allocation.getEvalId(), is(evalId));
        }
    }

    @Test
    public void shouldForceEvaluate() throws Exception {
        try (TestAgent agent = newClientServer()) {
            final NodesApi nodesApi = agent.getApiClient().getNodesApi();

            new ErrorResponseAssertion("node not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return nodesApi.forceEvaluate(NONSENSE_GUID);
                }
            };

            List<NodeListStub> nodes = nodesApi.list().getValue();
            assertThat(nodes, not(empty()));
            String nodeId = nodes.get(0).getId();

            ServerResponse<Void> response = nodesApi.forceEvaluate(nodeId);
        }
    }

}
