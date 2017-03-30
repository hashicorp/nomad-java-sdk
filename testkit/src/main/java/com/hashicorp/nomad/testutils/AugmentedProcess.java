package com.hashicorp.nomad.testutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Encapsulates a {@link java.lang.Process} with threads that pump its stdout and stdin to {@link PrintWriter}s.
 */
class AugmentedProcess {
    private final Process process;
    private final LinePump stdoutPump;
    private final LinePump stderrPump;

    AugmentedProcess(List<String> command, PrintWriter sink) throws IOException {
        this(command, sink, sink);
    }

    AugmentedProcess(List<String> command, PrintWriter stdoutSink, PrintWriter stderrSink) throws IOException {
        process = Runtime.getRuntime().exec(command.toArray(new String[] {}));
        try {
            stdoutPump = new LinePump("stdout", process.getInputStream(), UTF_8, stdoutSink);
            stderrPump = new LinePump("stderr", process.getErrorStream(), UTF_8, stderrSink);
            stdoutPump.start();
            stderrPump.start();
        } catch (Exception e) {
            process.destroy();
            waitUntilStopped();
            throw e;
        }
    }

    public void close() {
        process.destroy();
        waitUntilStopped();
    }

    private void waitUntilStopped() {
        try {
            while (true) {
                try {
                    process.waitFor();
                    return;
                } catch (InterruptedException e) {
                    // we'll try agin
                }
            }
        } finally {
            try {
                if (stdoutPump != null)
                    stdoutPump.waitUntilFinished();
            } finally {
                if (stderrPump != null)
                    stderrPump.waitUntilFinished();
            }
        }
    }

    Process getProcess() {
        return process;
    }
}
