package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Indicates a problem with parsing the response entity.
 */
public class ResponseParsingException extends ResponseException {
    ResponseParsingException(String message, @Nullable String rawEntity, @Nullable Throwable cause) {
        super(message, rawEntity, cause);
    }
}
