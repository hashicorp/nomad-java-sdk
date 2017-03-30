package com.hashicorp.nomad.javasdk;


import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about regions,
 * exposing the functionality of the {@code /v1/regions} endpoint of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class RegionsApi extends ApiBase {

    RegionsApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * List the names of the known regions in the cluster.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/regions.html">{@code GET /v1/regions}</a>
     */
    public NomadResponse<List<String>> list() throws IOException, NomadException {
        return executePlain(get("/v1/regions"), NomadJson.parserForListOf(String.class));
    }
}
