package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.StreamFrame;

/**
 * A handler for API responses consisting of streaming file data.
 */
public interface StreamHandler {

    /**
     * Handle a {@code Throwable} that arose during stream processing.
     *
     * @param throwable
     */
    void onThrowable(Throwable throwable);

    /**
     * Handle a non-keepalive stream frame.
     *
     * @throws Exception if there is a problem while handling the frame.
     */
    void onFrame(StreamFrame frame) throws Exception;

    /**
     * Handle the end of the stream.
     *
     * @throws Exception if there is a problem handling the close event.
     */
    void onClose() throws Exception;
}
