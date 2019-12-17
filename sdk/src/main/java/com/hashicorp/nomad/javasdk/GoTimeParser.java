package com.hashicorp.nomad.javasdk;

import org.joda.time.MutablePeriod;
import org.joda.time.format.PeriodFormatterBuilder;
import org.joda.time.format.PeriodParser;

import java.util.Locale;

/**
 * Utility for converting a Golang Duration string to a Long.
 * Supports units: h (hour), m (minute), s (second), ms (milliseconds)
 * Similar to: https://golang.org/pkg/time/#ParseDuration
 *
 * Note: Specifying units out of order (Example: 10s5m) will result in an error
 */
public class GoTimeParser {

    private static final Long NANOSECONDS_PER_SECOND = 1_000_000_000L;
    private static final Long SECONDS_PER_MINUTE = 60L;
    private static final Long MINUTES_PER_HOUR = 60L;
    private static final Long MILLISECONDS_PER_SECOND = 1000L;
    private PeriodParser periodParser;

    /**
     * Initializes the parser.
     */
    public GoTimeParser() {
        periodParser = new PeriodFormatterBuilder()
            .rejectSignedValues(true)
            .appendHours()
            .appendSuffix("h")
            .appendMinutes()
            .appendSuffix("m")
            .appendSeconds()
            .appendSuffix("s")
            .appendMillis()
            .appendSuffix("ms")
            .toParser();
    }

    /**
     * Converts a Golang Duration string to a Long.
     * @param time Golang style duration string (Example: 1h10m)
     *             Note: specifying units out of order is unsupported (Example: 10s5m)
     * @return Number of nanoseconds equal to the duration
     * @throws IllegalArgumentException on parsing failure
     */
    public Long toNanoSeconds(String time) {
        MutablePeriod period = new MutablePeriod();
        int parseResult = periodParser.parseInto(period, time, 0, Locale.ENGLISH);
        if ((parseResult <= 0) ||  parseResult < time.length()) {
            throw new IllegalArgumentException("Failed to parse: " + time + ", error at position: " + parseResult);
        }

        return period.getHours() * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * NANOSECONDS_PER_SECOND
            + period.getMinutes() * SECONDS_PER_MINUTE * NANOSECONDS_PER_SECOND
            + period.getSeconds() * NANOSECONDS_PER_SECOND
            + period.getMillis() * MILLISECONDS_PER_SECOND;
    }
}
