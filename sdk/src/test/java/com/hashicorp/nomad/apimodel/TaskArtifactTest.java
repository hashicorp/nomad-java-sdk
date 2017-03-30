package com.hashicorp.nomad.apimodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskArtifactTest {

    @Test
    public void shouldWorkAfterJsonRoundtrip() throws Exception {

        final TaskArtifact artifact = new TaskArtifact()
                .setGetterSource("foo")
                .setRelativeDest("bar");

        final TaskArtifact deserialized = TaskArtifact.fromJson(artifact.toString());
        assertEquals("foo", deserialized.getGetterSource());
        assertEquals("bar", deserialized.getRelativeDest());
        assertEquals(null, deserialized.getGetterOptions());
    }

}
