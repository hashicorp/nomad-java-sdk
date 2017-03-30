package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Indicates that server signaled an error.
 */
public final class ErrorResponseException extends ResponseException {
    private final String serverErrorMessage;

    private ErrorResponseException(HttpUriRequest request,
                                   String errorLocation,
                                   String serverErrorMessage,
                                   @Nullable String rawEntity) {

        super(
                request.getMethod() + " " + request.getURI()
                        + " resulted in error " + errorLocation + ": " + serverErrorMessage,
                rawEntity,
                null);
        this.serverErrorMessage = serverErrorMessage;
    }

    /**
     * @return the error message sent by the server
     */
    public String getServerErrorMessage() {
        return serverErrorMessage;
    }

    static ErrorResponseException signaledInStatus(HttpUriRequest request, HttpResponse response) {
        String rawEntity;
        String errorMessage;
        try {
            rawEntity = EntityUtils.toString(response.getEntity());
            errorMessage = rawEntity;
        } catch (IOException e) {
            rawEntity = null;
            errorMessage = "!!!ERROR GETTING ERROR MESSAGE FROM RESPONSE ENTITY: " + e + "!!!";
        }
        String errorLocation = "response status " + response.getStatusLine();
        return new ErrorResponseException(request, errorLocation, errorMessage, rawEntity);
    }

    static ErrorResponseException signaledInEntity(HttpUriRequest request, HttpResponse response, String message) {
        String rawEntity;
        try {
            rawEntity = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            rawEntity = null;
        }
        return new ErrorResponseException(request, "in response entity", message, rawEntity);
    }
}
