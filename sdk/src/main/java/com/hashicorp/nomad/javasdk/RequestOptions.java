package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Interface for options shared by all API requests.
 */
public interface RequestOptions {

    /**
     * Gets the region to which requests should be forwarded.
     *
     * @return the region the request should be routed to,
     * or null if the API client's default region configuration should be used.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    String getRegion();

    /**
     * Gets the namespace for this request.
     * <p>
     * When null, falls back to the NomadApiClient's namespace.
     */
    @Nullable
    String getNamespace();

    /**
     * Gets the secret ID of the ACL token to use for this request.
     * <p>
     * When null, falls back to the NomadApiClient's auth token.
     */
    @Nullable
    String getAuthToken();

}
