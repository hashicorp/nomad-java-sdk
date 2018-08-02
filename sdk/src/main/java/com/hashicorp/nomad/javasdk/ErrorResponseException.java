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
    private final int statusCode;

    private ErrorResponseException(String requestMethod,
                                  String requestUri,
                                  String errorLocation,
                                  String serverErrorMessage,
                                  int statusCode,
                                  @Nullable String rawEntity) {

        super(
                requestMethod + " " + requestUri
                        + " resulted in error " + errorLocation + ": " + serverErrorMessage,
                rawEntity,
                null);
        this.serverErrorMessage = serverErrorMessage;
        this.statusCode = statusCode;
    }

    /**
     * @return the error message sent by the server
     */
    public String getServerErrorMessage() {
        return serverErrorMessage;
    }

    /**
     * @return status code sent by the server
     */
    public int getStatusCode() {
        return statusCode;
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
        int status = response.getStatusLine().getStatusCode();
        String errorLocation = "response status " + response.getStatusLine();
        return new ErrorResponseException(
                request.getMethod(),
                request.getURI().toString(),
                errorLocation,
                errorMessage,
                status,
                rawEntity);
    }

    static ErrorResponseException signaledInEntity(HttpUriRequest request, HttpResponse response, String message) {
        String rawEntity;
        try {
            rawEntity = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            rawEntity = null;
        }
        int status = response.getStatusLine().getStatusCode();
        return new ErrorResponseException(
                request.getMethod(),
                request.getURI().toString(),
                "in response entity",
                message,
                status,
                rawEntity);
    }
}
