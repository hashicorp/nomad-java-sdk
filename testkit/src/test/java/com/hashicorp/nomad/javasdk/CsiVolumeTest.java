package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.*;
import com.hashicorp.nomad.testutils.TestAgent;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsiVolumeTest extends ApiTestBase {

    // TODO: need some tests against actual CSI volumes, which will require configuring a client with CSI volumes

    @Test
    public void shouldListVolumes() throws Exception {
        try (TestAgent agent = newServer()) {
            CSIVolumesApi volumesApi = agent.getApiClient().getCSIVolumesApi();

            ServerQueryResponse<List<CsiVolumeListStub>> list = volumesApi.list();
            assertThat(list.getValue(), empty());
        }
    }

}
