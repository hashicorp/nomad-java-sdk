package com.hashicorp.nomad.javasdk;

/**
 * Indicates that Nomad was interrupted while waiting to retry a request.
 */
public class RetryInterruptedException extends NomadException {
    RetryInterruptedException(InterruptedException e) {
        super(null, e);
    }
}
