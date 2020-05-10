package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.CsiPlugin;
import com.hashicorp.nomad.apimodel.CsiPluginListStub;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about CSI plugins,
 * exposing the <a href="https://www.nomadproject.io/api-docs/plugins/">plugins</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class CSIPluginsApi extends ApiBase {

    CSIPluginsApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Lists CSI plugins.
     *
     * @param options         options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/plugins/#list-plugins">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<CsiPluginListStub>> list(
            @Nullable final QueryOptions<List<CsiPluginListStub>> options
    ) throws IOException, NomadException {

        final URIBuilder uri = uri("/v1/plugins");
        uri.addParameter("type", "csi");
        return executeServerQuery(uri, options,
                NomadJson.parserForListOf(CsiPluginListStub.class));
    }

    /**
     * Lists CSI plugins.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/plugins/#list-plugins">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<List<CsiPluginListStub>> list() throws IOException, NomadException {

        return list(null);
    }

    /**
     * Retrieves detailed info a single CSI plugin.
     *
     * @param pluginId        the plugin ID
     * @param options         options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/plugins/#read-plugin">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<CsiPlugin> info(
            @Nullable final String pluginId,
            @Nullable final QueryOptions<CsiPlugin> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/plugin/csi/" + pluginId,
                options,
                NomadJson.parserFor(CsiPlugin.class));
    }

    /**
     * Retrieves detailed info a single CSI plugin.
     *
     * @param pluginId        the plugin ID
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/plugins/#read-plugin">{@code GET /v1/jobs}</a>
     */
    public ServerQueryResponse<CsiPlugin> info(
            @Nullable final String pluginId
    ) throws IOException, NomadException {

        return info(pluginId, null);
    }

}
