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
    private final int    serverErrorCode;

    private ErrorResponseException(HttpUriRequest request,
                                   String errorLocation,
                                   String serverErrorMessage,
                                   int serverErrorCode,
                                   @Nullable String rawEntity) {

        super(
                request.getMethod() + " " + request.getURI()
                        + " resulted in error " + errorLocation + ": " + serverErrorMessage,
                rawEntity,
                null);
        this.serverErrorMessage = serverErrorMessage;
        this.serverErrorCode = serverErrorCode;
    }

    /**
     * @return the error message sent by the server
     */
    public String getServerErrorMessage() {
        return serverErrorMessage;
    }

    /**
     * @return the HTTP error code from the server, or 0 for non-HTTP errors
     */
    public int getServerErrorCode() {
        return serverErrorCode;
    }

    static ErrorResponseException signaledInStatus(HttpUriRequest request, HttpResponse response) {
        String rawEntity;
        String errorMessage;
        int    errorCode;
        try {
            rawEntity = EntityUtils.toString(response.getEntity());
            errorMessage = rawEntity;
            errorCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            rawEntity = null;
            errorMessage = "!!!ERROR GETTING ERROR MESSAGE FROM RESPONSE ENTITY: " + e + "!!!";
            errorCode = 0;
        }
        String errorLocation = "response status " + response.getStatusLine();
        return new ErrorResponseException(request, errorLocation, errorMessage, errorCode, rawEntity);
    }

    static ErrorResponseException signaledInEntity(HttpUriRequest request, HttpResponse response, String message) {
        String rawEntity;
        int errorCode;
        try {
            rawEntity = EntityUtils.toString(response.getEntity());
            errorCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            rawEntity = null;
            errorCode = 0;
        }
        return new ErrorResponseException(request, "in response entity", message, errorCode, rawEntity);
    }
}
