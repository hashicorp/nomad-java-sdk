package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.StreamFrame;

import javax.annotation.WillCloseWhenClosed;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Pipes a Nomad file stream to an {@link OutputStream}.
 */
public abstract class OutputStreamHandler implements StreamHandler {

    private final OutputStream out;

    /**
     * Creates a new handler that writes to the given OutputStream.
     */
    public OutputStreamHandler(@WillCloseWhenClosed OutputStream out) {
        this.out = out;
    }

    /**
     * Writes the data in the frame to the OutputStream.
     *
     * @throws IOException if the OutputStream throws an IOException.
     */
    @Override
    public void onFrame(StreamFrame frame) throws IOException {
        out.write(frame.getData());
    }

    /**
     * Closes the OutputStream.
     *
     * @throws IOException if the OutputStream throws an IOException.
     */
    @Override
    public void onClose() throws IOException {
        out.close();
    }
}
