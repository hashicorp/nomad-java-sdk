package com.hashicorp.nomad.javasdk;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * A strategy to use for generating "wait" values that indicate how long the server should wait for a potential change
 * when using long poll. This is useful when repeatedly polling until a condition is met, e.g. with
 * {@link QueryOptions#pollRepeatedlyUntil}.
 *
 * @see <a href="https://www.nomadproject.io/docs/http/index.html#blocking-queries">Blocking Queries</a>
 */
public abstract class WaitStrategy {

    /**
     * A strategy to wait indefinitely.
     */
    public static final WaitStrategy WAIT_INDEFINITELY = new WaitStrategy() {
        @Override
        public String getWait() {
            return null;
        }
    };

    /**
     * Called just before a request to the API to get the wait value that should be used.
     *
     * @return the wait value to use, or null if the poll should last indefinitely.
     * @throws WaitStrategyExhaustedException if the request shouldn't be made because too much time has elapsed.
     */
    @Nullable
    public abstract String getWait() throws WaitStrategyExhaustedException;

    /**
     * Creates a wait strategy that will wait until the given deadline and then timeout.
     */
    public static WaitStrategy until(final Date deadline) {
        return until(deadline, Long.MAX_VALUE);
    }

    /**
     * Creates a wait strategy that will wait until the given deadline and then timeout.
     *
     * @param maximumPollDurationMillis maximum duration for each long-poll request to the server, in milliseconds
     */
    public static WaitStrategy until(final Date deadline, final long maximumPollDurationMillis) {
        return new WaitStrategy() {
            @Override
            public String getWait() throws WaitStrategyExhaustedException {
                long remainingMillis = deadline.getTime() - System.currentTimeMillis();
                if (remainingMillis < 0)
                    throw new WaitStrategyExhaustedException("Past deadline of " + deadline);
                return Math.min(remainingMillis, maximumPollDurationMillis) + "ms";
            }
        };
    }

    /**
     * Creates a wait strategy that waits until the given number of seconds from now.
     */
    public static WaitStrategy waitForSeconds(int seconds) {
        return waitForMilliseconds(seconds * 1000L);
    }

    /**
     * Creates a wait strategy that waits until the given number of seconds from now.
     */
    public static WaitStrategy waitForMilliseconds(long waitMillis) {
        return waitForMilliseconds(waitMillis, Long.MAX_VALUE);
    }

    /**
     * Creates a wait strategy that waits until the given number of seconds from now.
     */
    public static WaitStrategy waitForMilliseconds(long waitMillis, long maximumPollDurationMillis) {
        return until(new Date(System.currentTimeMillis() + waitMillis), maximumPollDurationMillis);
    }

    /**
     * Creates a wait strategy that delegates to another strategy as long as the given process is running,
     * and times out if the process has terminated.
     */
    public static WaitStrategy whileProcessIsRunning(final Process process, final WaitStrategy delegate) {
        return new WaitStrategy() {
            @Override
            public String getWait() throws WaitStrategyExhaustedException {
                try {
                    throw new WaitStrategyExhaustedException(
                            "The process has terminated with exit status " + process.exitValue());
                } catch (IllegalThreadStateException e) { // process is still running
                    return delegate.getWait();
                }
            }
        };
    }
}
