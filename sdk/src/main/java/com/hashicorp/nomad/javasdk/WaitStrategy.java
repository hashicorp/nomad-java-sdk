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
     *
     * @param deadline the time at which the wait strategy starts timing-out.
     */
    public static WaitStrategy until(final Date deadline) {
        return until(deadline, Long.MAX_VALUE);
    }

    /**
     * Creates a wait strategy that will wait until the given deadline and then timeout.
     *
     * @param deadline                  the time at which the wait strategy starts timing-out.
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
     *
     * @param seconds the number of seconds from now at which the wait strategy starts timing-out.
     */
    public static WaitStrategy waitForSeconds(int seconds) {
        return waitForMilliseconds(seconds * 1000L);
    }

    /**
     * Creates a wait strategy that waits until the given number of seconds from now.
     *
     * @param waitMillis the number of milliseconds from now at which the wait strategy starts timing-out.
     */
    public static WaitStrategy waitForMilliseconds(long waitMillis) {
        return waitForMilliseconds(waitMillis, Long.MAX_VALUE);
    }

    /**
     * Creates a wait strategy that waits until the given number of seconds from now.
     *
     * @param waitMillis                the number of milliseconds from now at which the wait strategy starts timing-out
     * @param maximumPollDurationMillis maximum duration for each long-poll request to the server, in milliseconds
     */
    public static WaitStrategy waitForMilliseconds(long waitMillis, long maximumPollDurationMillis) {
        return until(new Date(System.currentTimeMillis() + waitMillis), maximumPollDurationMillis);
    }

    /**
     * Creates a wait strategy that delegates to another strategy as long as a specific process is running,
     * and times out if the process has terminated.
     *
     * @param process  the process whose termination causes the strategy to time out
     * @param delegate the underlying WaitStrategy to use while the process is running
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
