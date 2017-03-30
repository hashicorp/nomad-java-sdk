package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.core.JsonParser;
import com.hashicorp.nomad.apimodel.StreamFrame;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.Closeable;
import java.io.IOException;

/**
 * A stream of {@link StreamFrame}s, as returned by some filesystem operations.
 * <p>
 * It's important to {@link #close} the stream when it is no longer needed,
 * to avoid leaking connections to the remote Nomad agent.
 */
public class FramedStream implements Closeable, AutoCloseable {
    private final CloseableHttpResponse response;
    private final JsonParser jsonParser;

    FramedStream(CloseableHttpResponse response) throws IOException {
        this.response = response;
        this.jsonParser = NomadJson.OBJECT_MAPPER.getFactory().createParser(response.getEntity().getContent());
    }

    /**
     * Returns true iff there is another frame in the stream.
     * <p>
     * This method will block until the beginning of the next frame has been received or the
     * response has ended.
     * <p>
     * Note that most streams will never be terminated by the remote agent,
     * as the server keeps them open and sends new data as it is written on the agent.
     *
     * @throws IOException if there is an error reading the response entity.
     */
    public boolean hasNextFrame() throws IOException {
        return jsonParser.nextToken() != null;
    }

    /**
     * Returns the next frame in the stream.
     *
     * @throws IOException if there is an error reading the response entity
     *                     or the end of the stream has been reached.
     */
    public StreamFrame nextFrame() throws IOException {
        return NomadJson.OBJECT_MAPPER.readValue(jsonParser, StreamFrame.class);
    }

    /**
     * Closes the stream and frees the underlying HTTP connection.
     *
     * @throws IOException if an error is encountered while trying to close the stream.
     */
    @Override
    public void close() throws IOException {
        try {
            // We need to first close the response directly rather than the parser or the response entity's
            // InputStream in order to avoid a long delay.
            response.close();
        } catch (MalformedChunkCodingException e) {
            // that's because we forced the stream closed
        }
    }
}
