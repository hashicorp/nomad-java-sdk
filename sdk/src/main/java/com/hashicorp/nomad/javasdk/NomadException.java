package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;

/**
 * An error either signaled by the remote agent or arising while interpreting its response.
 */
public class NomadException extends Exception {
    NomadException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
