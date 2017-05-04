package com.hashicorp.nomad.testutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * A thread that pumps text from an input BufferReader to an output PrintWriter one line at a time.
 * <p>
 * This can be used with {@link Process} to pump the consume stdout and stderr (accessible via
 * {@link Process#getInputStream()} and {@link Process#getErrorStream()} respectively)
 * so that process doesn't block waiting for those streams to be consumed.
 * <p>
 * Since text is written line-by-line, multiple LinePumps can be used to multiplex multiple inputs into a single output.
 */
class LinePump extends Thread {

    private final String inputName;
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * Creates a new line pump.
     *
     * @param inputName a name for the input that will be appended to the line pump thread name
     * @param in        the reader to read from
     * @param out       the writer to write to
     */
    LinePump(String inputName, InputStream in, Charset charset, PrintWriter out) {
        this(inputName, new InputStreamReader(in, charset), out);
    }

    /**
     * Creates a new line pump.
     *
     * @param inputName a name for the input that will be appended to the line pump thread name
     * @param in        the reader to read from
     * @param out       the writer to write to
     */
    LinePump(String inputName, Reader in, PrintWriter out) {
        this(inputName, bufferedReader(in), out);
    }

    /**
     * Creates a new line pump.
     *
     * @param inputName a name for the input that will be appended to the line pump thread name
     * @param in        the reader to read from
     * @param out       the writer to write to
     */
    LinePump(String inputName, BufferedReader in, PrintWriter out) {
        super("line-pump-" + inputName);
        this.inputName = inputName;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                line = in.readLine();
            } catch (IOException e) {
                out.println("<<<POTENTIALLY TRUNCATED: error while reading from " + inputName + ": " + e + ">>>");
                out.flush();
                return;
            }
            if (line == null)
                return;
            out.println(line);
            out.flush();
        }
    }

    void waitUntilFinished() {
        while (true) {
            try {
                join();
                return;
            } catch (InterruptedException e) {
                // we'll try again
            }
        }
    }

    private static BufferedReader bufferedReader(Reader in) {
        return new BufferedReader(in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in));
    }
}
