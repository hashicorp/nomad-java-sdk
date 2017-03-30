package com.hashicorp.nomad.apimodel;

import com.hashicorp.nomad.javasdk.NomadJson;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConstraintTest {

    @Test
    public void shouldWorkAfterJsonRoundtrip() throws Exception {

        final Constraint constraint = new Constraint()
                .setLTarget("foo")
                .setOperand("bar")
                .setRTarget("baz");

        final Constraint deserialized = Constraint.fromJson(constraint.toString());
        assertEquals("foo", deserialized.getLTarget());
        assertEquals("bar", deserialized.getOperand());
        assertEquals("baz", deserialized.getRTarget());
    }

    @Test
    public void shouldHaveCorrectJsonRepresentation() throws Exception {

        final Constraint constraint = new Constraint()
                .setLTarget("foo")
                .setOperand("bar")
                .setRTarget("baz");

        final Map<String, String> expected = new HashMap<>();
        expected.put("LTarget", "foo");
        expected.put("Operand", "bar");
        expected.put("RTarget", "baz");

        assertEquals(expected, NomadJson.deserialize(constraint.toString(), Map.class));
    }

}
