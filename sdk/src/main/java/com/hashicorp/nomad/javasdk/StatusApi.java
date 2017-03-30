package com.hashicorp.nomad.javasdk;

import org.apache.http.client.methods.RequestBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about the status of the cluster,
 * exposing the functionality of the {@code /v1/status/…} endpoints of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/status.html">{@code /v1/status documentation}</a>
 */
public class StatusApi extends ApiBase {

    StatusApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Queries the address of the Raft leader in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/status.html">{@code GET /v1/status/peers}</a>
     */
    public NomadResponse<String> leader() throws IOException, NomadException {
        return leader(null);
    }

    /**
     * Queries the address of the Raft leader in the given or active region.
     *
     * @param region the region to forward the request to
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/status.html">{@code GET /v1/status/peers}</a>
     */
    public NomadResponse<String> leader(@Nullable String region) throws IOException, NomadException {
        final RequestBuilder request = get("/v1/status/leader");
        if (region != null)
            request.addParameter("region", region);
        return executePlain(request, NomadJson.parserFor(String.class));
    }

    /**
     * List the addresses of the Raft peers in the active region.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/status.html">{@code GET /v1/status/peers}</a>
     */
    public NomadResponse<List<String>> peers() throws IOException, NomadException {
        return peers(null);
    }

    /**
     * List the addresses of the Raft peers in the given or active region.
     *
     * @param region the region to forward the request to
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/docs/http/status.html">{@code GET /v1/status/peers}</a>
     */
    public NomadResponse<List<String>> peers(@Nullable String region) throws IOException, NomadException {
        RequestBuilder request = get("/v1/status/peers");
        if (region != null)
            request.addParameter("region", region);
        return executePlain(request, NomadJson.parserForListOf(String.class));
    }
}
