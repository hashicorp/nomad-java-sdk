package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Options that control how an operation that writes to a Nomad server is performed.
 */
public class WriteOptions {
    private final String region;

    /**
     * Creates a WriteOptions that controls which region a request is forwarded to.
     *
     * @param region the region the request should be routed to.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    public WriteOptions(String region) {
        this.region = region;
    }

    /**
     * Gets the region to which requests should be forwarded.
     *
     * @return the region the request should be routed to,
     * or null if the API client's default region configuration should be used.
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    public String getRegion() {
        return region;
    }
}
