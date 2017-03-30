package com.hashicorp.nomad.javasdk;

/**
 * Indicates that there was a problem serialising the request entity.
 */
public class RequestSerializationException extends RuntimeException {
    RequestSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
