package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.StreamFrame;

/**
 * A wrapper for Exceptions thrown thrown by {@link StreamHandler#handleFrame}.
 */
public class StreamFrameHandlingException extends NomadException {
    private final StreamFrame frame;

    StreamFrameHandlingException(StreamFrame frame, Throwable cause) {
        super("Error while handling frame " + frame, cause);
        this.frame = frame;
    }

    /**
     * Returns the frame which was being processed when the exception was thrown.
     */
    public StreamFrame getFrame() {
        return frame;
    }
}
