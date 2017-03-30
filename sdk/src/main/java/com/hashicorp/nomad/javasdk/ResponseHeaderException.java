package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * Thrown when there is a problem with a response header.
 */
public class ResponseHeaderException extends RuntimeException {
    ResponseHeaderException(String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    static ResponseHeaderException parsing(String name, String value, Throwable cause) {
        return new ResponseHeaderException(
                "Error parsing " + name + " header with value \"" + value + "\":" + cause,
                cause);
    }
}
