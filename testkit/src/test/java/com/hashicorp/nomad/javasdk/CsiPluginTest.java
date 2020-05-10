package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.CsiPluginListStub;
import com.hashicorp.nomad.apimodel.CsiVolumeListStub;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class CsiPluginTest extends ApiTestBase {

    // TODO: need some tests against actual CSI plugins, which will require configuring a client with CSI plugins

    @Test
    public void shouldListPlugins() throws Exception {
        try (TestAgent agent = newServer()) {
            CSIPluginsApi pluginsApi = agent.getApiClient().getCSIPluginsApi();

            ServerQueryResponse<List<CsiPluginListStub>> list = pluginsApi.list();
            assertThat(list.getValue(), empty());
        }
    }

}
