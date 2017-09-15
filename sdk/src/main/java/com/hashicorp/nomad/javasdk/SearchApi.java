package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.SearchResponse;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * API for performing searches in the Nomad cluster,
 * exposing the <a href="https://www.nomadproject.io/api/search.html">search</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class SearchApi extends ApiBase {

    SearchApi(NomadApiClient nomadApiClient) {
        super(nomadApiClient);
    }

    /**
     * Returns a list of matches for a particular context and prefix.
     *
     * @param prefix  items with this prefix are returned
     * @param context one of the following: allocs, deployment, evals, jobs, nodes, all
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/search.html">{@code PUT /v1/search}</a>
     */
    public ServerQueryResponse<SearchResponse> prefixSearch(
            String prefix,
            String context
    ) throws IOException, NomadException {
        return prefixSearch(prefix, context, null);
    }

    /**
     * Returns a list of matches for a particular context and prefix.
     *
     * @param prefix  items with this prefix are returned
     * @param context one of the following: allocs, deployment, evals, jobs, nodes, all
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/search.html">{@code PUT /v1/search}</a>
     */
    public ServerQueryResponse<SearchResponse> prefixSearch(
            String prefix,
            String context,
            @Nullable QueryOptions<SearchResponse> options
    ) throws IOException, NomadException {

        return executeServerQueryPut(
                uri("/v1/search"),
                new SearchRequest(prefix, context),
                options,
                NomadJson.parserFor(SearchResponse.class)
        );
    }

    /**
     * Class matching the JSON request entity for search requests.
     */
    private static class SearchRequest {
        public final String prefix;  // Checkstyle suppress VisibilityModifier
        public final String context; // Checkstyle suppress VisibilityModifier

        SearchRequest(String prefix, String context) {
            this.prefix = prefix;
            this.context = context;
        }
    }

}
