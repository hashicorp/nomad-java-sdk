package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.UpdateStrategy;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NomadJsonTest {

    @Test
    public void shouldReadJsonJobSpec() throws Exception {
        String jobSpec = IOUtils.toString(getClass().getResourceAsStream("job.json"), UTF_8);
        final Job job = NomadJson.readJobSpec(jobSpec);
        assertThat(job.getId(), is("Job-ID"));
    }

    @Test
    public void shouldPreserveJobInWritingAndReadingJobSpec() throws Exception {
        final Job job = new Job().setName("Alice");
        final Job afterRoundtrip = NomadJson.readJobSpec(NomadJson.asJobSpec(job));
        assertThat(job.getName(), is("Alice"));
    }

    @Test
    public void shouldPreserveUnknownKeysInDeserialisation() throws Exception {
        String jobSpec = IOUtils.toString(getClass().getResourceAsStream("job.json"), UTF_8);
        final Job job = NomadJson.readJobSpec(jobSpec);
        assertThat(job.getId(), is("Job-ID"));

        assertThat(job.getUnmappedProperties().get("Unknown Job String"), is((Object) "Hi"));

        final HashMap<String, String> expectedJobObject = new HashMap<>();
        expectedJobObject.put("Foo", "Bar");
        assertThat(job.getUnmappedProperties().get("Unknown Job Object"), is((Object) expectedJobObject));

        final UpdateStrategy update = job.getUpdate();

        assertThat(update.getUnmappedProperties().get("Unknown Update String"), is((Object) "here too"));

        final HashMap<String, String> expectedUpdateObject = new HashMap<>();
        expectedUpdateObject.put("anything", "goes");
        assertThat(update.getUnmappedProperties().get("Unknown Update Object"), is((Object) expectedUpdateObject));
    }

}
