package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpHost;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Configuration for a {@link NomadApiClient}.
 * <p>
 * This class is immutable. Use the {#Builder} inner class to conveniently build a configuration.
 */
// TODO complete
public class NomadApiConfiguration {

    static final HttpHost DEFAULT_NOMAD_ADDR = new HttpHost("127.0.0.1", 4646);

    private final HttpHost address;
    private final String region;
    private final Tls tls;

    /**
     * Creates a new configuration with the given values.
     * <p>
     * Consider using the {#Builder} inner class to conveniently build a configuration.
     */
    public NomadApiConfiguration(final HttpHost address, @Nullable final String region, final Tls tls) {
        if (address == null) {
            throw new IllegalArgumentException("address cannot be null");
        }

        this.address = address;
        this.region = region;
        this.tls = tls;
    }

    /**
     * Returns the HTTP address of the agent to connect to.
     */
    public HttpHost getAddress() {
        return address;
    }

    /**
     * Returns the region to use by default.
     * <p>
     * If null, the region of the server than receives the requests will be used.
     */
    @Nullable
    public String getRegion() {
        return region;
    }

    /**
     * Returns the TLS configuration.
     */
    public Tls getTls() {
        return tls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NomadApiConfiguration that = (NomadApiConfiguration) o;

        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

    /**
     * Validates a Nomad address and converts it to an HttpHost.
     */
    public static HttpHost nomadAddressAsHttpHost(String address) {
        if (!address.startsWith("http:") && !address.startsWith("https:")) {
            throw new IllegalArgumentException(
                    "address must start with \"http:\" or \"https:\", but got \"" + address + "\"");
        }
        return HttpHost.create(address);
    }

    /**
     * TLS configuration for a {@link NomadApiClient}.
     * <p>
     * This class is immutable. Use the {#Builder} class to conveniently build a configuration.
     */
    public static class Tls {
        private final String caCertificateFile;
        private final String clientCertificateFile;
        private final String clientKeyFile;

        /**
         * Creates a new TLS configuration with the given values.
         * <p>
         * Consider using the {#Builder} class to conveniently build a configuration.
         */
        public Tls(@Nullable String caCertificateFile,
                   @Nullable String clientCertificateFile,
                   @Nullable String clientKeyFile) {

            if ((clientCertificateFile == null) != (clientKeyFile == null))
                throw new IllegalArgumentException(
                        "You cannot set only one of clientCertificateFile and clientKeyFile");

            this.caCertificateFile = caCertificateFile;
            this.clientCertificateFile = clientCertificateFile;
            this.clientKeyFile = clientKeyFile;
        }

        /**
         * Returns the path to the CA certificate.
         */
        public String getCaCertificateFile() {
            return caCertificateFile;
        }

        /**
         * Returns the path to the client certificate.
         */
        public String getClientCertificateFile() {
            return clientCertificateFile;
        }

        /**
         * Returns the path to the client key.
         */
        public String getClientKeyFile() {
            return clientKeyFile;
        }
    }

    /**
     * A builder with fluent setters to easily create a configuration.
     * <p>
     * The server HTTP address defaults to "http://127.0.0.1:4646".
     * <p>
     * Use {@link #setFromEnvironmentVariables} to read from common Nomad environment variables.
     */
    public static class Builder {
        private HttpHost address;
        private String region;
        private String tlsCaFile;
        private String tlsCertFile;
        private String tlsKeyFile;

        /**
         * Sets the HTTP address of the agent to connect to.
         *
         * @param address
         * @return this builder object, to allow method chaining
         */
        public Builder setAddress(final String address) {
            return setAddress(nomadAddressAsHttpHost(address));
        }

        /**
         * Sets the HTTP address of the agent to connect to.
         *
         * @param address
         * @return this builder object, to allow method chaining
         */
        public Builder setAddress(final HttpHost address) {
            this.address = address;
            return this;
        }

        /**
         * Sets the region to use by default.
         * <p>
         * If null, the region of the server than receives the requests will be used.
         */
        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        /**
         * Sets the path of the CA certificate, containing the certificate authority certificate
         * to use to verify the agent's TLS certificate.
         */
        public Builder setTlsCaFile(String tlsCaFile) {
            this.tlsCaFile = tlsCaFile;
            return this;
        }

        /**
         * Sets the path of the client's certificate and private key files,
         * used when communicating with an agent over TLS.
         */
        public Builder setTlsCertAndKeyFiles(String tlsCertFile, String tlsKeyFile) {
            this.tlsCertFile = tlsCertFile;
            this.tlsKeyFile = tlsKeyFile;
            return this;
        }

        /**
         * Looks for common Nomad environment variables in the given map, and sets any values found there.
         * <p>
         * This can be used with result of {@link System#getenv()}.
         * <p>
         * {@code NOMAD_ADDR} provides the agent's HTTP address.
         *
         * @return this builder object, to allow method chaining
         * @throws IllegalArgumentException if the @{code NOMAD_ADDR} environment variable is non-empty
         *                                  but doesn't represent a valid host URL
         */
        public Builder setFromEnvironmentVariables(final Map<String, String> environment) {
            if (environment.containsKey("NOMAD_ADDR")) {
                String address = environment.get("NOMAD_ADDR");
                if (!address.isEmpty())
                    this.address = HttpHost.create(address);
            }

            if (environment.containsKey("NOMAD_CA_CERT")) {
                String tlsCaFile = environment.get("NOMAD_CA_CERT");
                if (!tlsCaFile.isEmpty())
                    this.tlsCaFile = tlsCaFile;
            }

            if (environment.containsKey("NOMAD_CLIENT_CERT")) {
                String tlsCertFile = environment.get("NOMAD_CLIENT_CERT");
                if (!tlsCertFile.isEmpty())
                    this.tlsCertFile = tlsCertFile;
            }

            if (environment.containsKey("NOMAD_CLIENT_KEY")) {
                String tlsKeyFile = environment.get("NOMAD_CLIENT_KEY");
                if (!tlsKeyFile.isEmpty())
                    this.tlsKeyFile = tlsKeyFile;
            }

            return this;
        }

        /**
         * Builds a new NomadApiConfiguration with the values in the builder.
         */
        public NomadApiConfiguration build() {
            if (address == null)
                address = DEFAULT_NOMAD_ADDR;

            Tls tls = new Tls(
                    tlsCaFile,
                    tlsCertFile,
                    tlsKeyFile);

            return new NomadApiConfiguration(address, region, tls);
        }
    }
}
