package com.hashicorp.nomad.testutils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestMethodLogRule extends PrintWriter implements TestRule {

    private final StringWriter stringWriter;

    public TestMethodLogRule() {
        this(new StringWriter());
    }

    public TestMethodLogRule(StringWriter stringWriter) {
        super(stringWriter);
        this.stringWriter = stringWriter;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                stringWriter.getBuffer().delete(0, Integer.MAX_VALUE);
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    System.err.println(description + " has failed with " + t + "\nLog follows:\n");
                    System.err.print(stringWriter.getBuffer());
                    System.err.flush();
                    throw t;
                }
            }
        };
    }

}
