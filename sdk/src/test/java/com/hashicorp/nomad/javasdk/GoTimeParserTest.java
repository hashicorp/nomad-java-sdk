package com.hashicorp.nomad.javasdk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class GoTimeParserTest {

    private static GoTimeParser target;
    private static final Long NANOS_PER_SECOND = 1_000_000_000L;

    @BeforeClass
    public static void setUp() {
        target = new GoTimeParser();
    }

    @Test
    public void testCommonTimeStrings() {
        assertThat(target.toNanoSeconds("1s"), is(NANOS_PER_SECOND));
        assertThat(target.toNanoSeconds("1m"), is(60 * NANOS_PER_SECOND));
        assertThat(target.toNanoSeconds("1h"), is(3600 * NANOS_PER_SECOND));
    }

    @Test
    public void testTwoTimeUnits() {
        assertThat(target.toNanoSeconds("1m30s"), is(90 * NANOS_PER_SECOND));
    }

    @Test
    public void testAllTimeUnits() {
        assertThat(target.toNanoSeconds("1h10m5s500ms"), is(4205_000_500_000L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnitsOutOfOrder() {
        target.toNanoSeconds("10s1h");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingFailed() {
        target.toNanoSeconds("1j40u");
    }
}
