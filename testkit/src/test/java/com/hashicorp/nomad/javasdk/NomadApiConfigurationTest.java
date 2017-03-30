package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpHost;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NomadApiConfigurationTest {

    @Test
    public void shouldHaveDefaultConfiguration() {
        NomadApiConfiguration config = new NomadApiConfiguration.Builder().build();

        assertThat(config.getAddress(), is(NomadApiConfiguration.DEFAULT_NOMAD_ADDR));
        assertThat(config.getRegion(), nullValue());
    }

    @Test
    public void shouldAcceptBuilderValues() throws URISyntaxException {
        final HttpHost address = new HttpHost("1.2.3.4", 5);

        final NomadApiConfiguration config = new NomadApiConfiguration.Builder()
                .setAddress(address)
                .setRegion("amber")
                .build();

        assertThat(config.getAddress(), is(address));
        assertThat(config.getRegion(), is("amber"));
    }

    @Test
    public void shouldCorrectlyDefaultConfigurationFromEnvironment() throws Exception {
        String address = "http://a.nomad.addr:1234";
        String caCertPath = "path/to/ca.pem";
        String clientCertPath = "path/to/cert.pem";
        String keyPath = "path/to/key.pem";

        HashMap<String, String> environment = new HashMap<>();
        environment.put("NOMAD_ADDR", address);
        environment.put("NOMAD_CA_CERT", caCertPath);
        environment.put("NOMAD_CLIENT_CERT", clientCertPath);
        environment.put("NOMAD_CLIENT_KEY", keyPath);

        NomadApiConfiguration config = new NomadApiConfiguration.Builder()
                .setFromEnvironmentVariables(environment)
                .build();

        assertThat(config.getAddress(), is(new HttpHost("a.nomad.addr", 1234, "http")));
        assertThat(config.getTls().getCaCertificateFile(), is(caCertPath));
        assertThat(config.getTls().getClientCertificateFile(), is(clientCertPath));
        assertThat(config.getTls().getClientKeyFile(), is(keyPath));
    }

    @Test
    public void shouldIgnoreEmptyEnvironmentVariables() throws Exception {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("NOMAD_ADDR", "");
        environment.put("NOMAD_CA_CERT", "");
        environment.put("NOMAD_CLIENT_CERT", "");
        environment.put("NOMAD_CLIENT_KEY", "");

        NomadApiConfiguration config = new NomadApiConfiguration.Builder()
                .setFromEnvironmentVariables(environment)
                .build();

        assertThat(config, is(new NomadApiConfiguration.Builder().build()));
    }

    @Test
    public void shouldNotAcceptStringAddressWithoutExplicitHttpOrHttpsScheme() throws URISyntaxException {
        final NomadApiConfiguration.Builder builder = new NomadApiConfiguration.Builder();

        builder.setAddress("http://1.2.3.4:5");
        builder.setAddress("http://1.2.3.4:5");
        builder.setAddress("https://1.2.3.4:5");

        try {
            builder.setAddress("httpx://1.2.3.4:5");
            fail("Expected address httpx://1.2.3.4:5 to be rejected");
        } catch (IllegalArgumentException ignored) { }

        try {
            builder.setAddress("1.2.3.4:5");
            fail("Expected address 1.2.3.4:5 to be rejected");
        } catch (IllegalArgumentException ignored) { }
    }

}
