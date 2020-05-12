package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.CsiVolume;
import com.hashicorp.nomad.apimodel.CsiVolumeListStub;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * API for querying for information about CSI volumes,
 * exposing the <a href="https://www.nomadproject.io/api-docs/volumes/">volumes</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class CSIVolumesApi extends ApiBase {

    CSIVolumesApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Lists CSI volumes.
     *
     * @param pluginId filter the results to only volumes from the specified pluginID
     * @param options     options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#list-volumes">{@code GET /v1/volumes}</a>
     */
    public ServerQueryResponse<List<CsiVolumeListStub>> list(
            @Nullable final String pluginId,
            @Nullable final QueryOptions<List<CsiVolumeListStub>> options
    ) throws IOException, NomadException {

        final URIBuilder uri = uri("/v1/volumes");
        uri.addParameter("type", "csi");
        if (pluginId != null) {
            uri.addParameter("prefix", pluginId);
        }
        return executeServerQuery(uri, options,
                NomadJson.parserForListOf(CsiVolumeListStub.class));
    }

    /**
     * Lists CSI volumes.
     *
     * @param pluginId the results to only volumes from the specified pluginID
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#list-volumes">{@code GET /v1/volumes}</a>
     */
    public ServerQueryResponse<List<CsiVolumeListStub>> list(
            @Nullable final String pluginId
    ) throws IOException, NomadException {

        return list(pluginId, null);
    }

    /**
     * Lists CSI volumes.
     *
     * @param options     options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#list-volumes">{@code GET /v1/volumes}</a>
     */
    public ServerQueryResponse<List<CsiVolumeListStub>> list(
            @Nullable final QueryOptions<List<CsiVolumeListStub>> options
    ) throws IOException, NomadException {

        return list(null, options);
    }

    /**
     * Lists CSI volumes.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#list-volumes">{@code GET /v1/volumes}</a>
     */
    public ServerQueryResponse<List<CsiVolumeListStub>> list() throws IOException, NomadException {

        return list(null, null);
    }

    /**
     * Retrieves detailed info for a single CSI volume.
     *
     * @param volumeId        the volume ID
     * @param options         options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#read-volume">{@code GET /v1/volume/csi/{ID}}</a>
     */
    public ServerQueryResponse<CsiVolume> info(
            @Nullable final String volumeId,
            @Nullable final QueryOptions<CsiVolume> options
    ) throws IOException, NomadException {

        return executeServerQuery(
                "/v1/volume/csi/" + volumeId,
                options,
                NomadJson.parserFor(CsiVolume.class));
    }

    /**
     * Retrieves detailed info for a single CSI volume.
     *
     * @param volumeId        the volume ID
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api-docs/volumes/#read-volume">{@code GET /v1/volume/csi/{ID}}</a>
     */
    public ServerQueryResponse<CsiVolume> info(
            @Nullable final String volumeId
    ) throws IOException, NomadException {

        return info(volumeId, null);
    }


}
