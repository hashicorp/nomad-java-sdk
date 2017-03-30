package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * An exception that includes the raw response entity.
 */
public abstract class ResponseException extends NomadException {
    private final String rawEntity;

    ResponseException(String message, @Nullable String rawEntity, @Nullable Throwable cause) {
        super(message, cause);
        this.rawEntity = rawEntity;
    }

    /**
     * @return raw string representation of the response entity,
     *         or null for streaming response or if there was a problem receiving the entity.
     */
    public String getRawEntity() {
        return rawEntity;
    }
}
