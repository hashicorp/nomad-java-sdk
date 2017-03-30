package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Options for write operations to a Nomad server.
 */
public class WriteOptions {
    private final String region;

    /**
     * Creates a write options with the given region.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    public WriteOptions(String region) {
        this.region = region;
    }

    /**
     * Gets the region to use.
     * <p>
     * When null, the agent that handles the request will use its own region.
     *
     * @see <a href="https://www.nomadproject.io/docs/http/index.html#cross-region-requests">Cross-Region Requests</a>
     */
    @Nullable
    public String getRegion() {
        return region;
    }
}
