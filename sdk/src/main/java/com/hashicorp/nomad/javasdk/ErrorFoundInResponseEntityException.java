package com.hashicorp.nomad.javasdk;

/**
 * Indicates that the server sent an HTTP 200 status code
 * but signaled an error using a field in the response entity.
 */
class ErrorFoundInResponseEntityException extends Exception {
    ErrorFoundInResponseEntityException(String error) {
        super(error);
    }
}
