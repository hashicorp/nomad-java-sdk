package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpHost;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Configuration for a {@link NomadApiClient}.
 * <p>
 * This class is immutable. Use the {#Builder} inner class to conveniently build a configuration.
 */
public class NomadApiConfiguration {

    static final HttpHost DEFAULT_NOMAD_ADDR = new HttpHost("127.0.0.1", 4646);

    private final HttpHost address;
    private final String region;
    private final String namespace;
    private final String authToken;
    private final Tls tls;

    /**
     * Creates a new configuration with the given values.
     * <p>
     * Consider using the {#Builder} inner class to conveniently build a configuration.
     *
     * @param address   HTTP address of the agent to connect to
     * @param region    default region to forward requests to, or null to use the region of the agent we connect to
     * @param namespace the namespace to use in requests by default, or null to use Nomad's default namespace
     * @param authToken  the secret ID for the API client to use
     * @param tls       TLS configuration to use
     */
    public NomadApiConfiguration(
            final HttpHost address,
            @Nullable final String region,
            final String namespace,
            final String authToken,
            final Tls tls
    ) {
        if (address == null) {
            throw new IllegalArgumentException("address cannot be null");
        }

        this.address = address;
        this.region = region;
        this.namespace = namespace;
        this.authToken = authToken;
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
     * If null, the region of the server that receives the requests will be used.
     */
    @Nullable
    public String getRegion() {
        return region;
    }

    /**
     * Returns the namespace to use in requests by default.
     * <p>
     * If null, the Nomad's default namespace will be used.
     */
    @Nullable
    public String getNamespace() {
        return namespace;
    }

    /**
     * Creates a copy of this configuration with the given namespace.
     *
     * @param namespace the namespace to use in the new configuration.
     */
    public NomadApiConfiguration withNamespace(String namespace) {
        return new NomadApiConfiguration(address, region, namespace, authToken, tls);
    }

    /**
     * Returns the secret ID for the API client to use.
     */
    @Nullable
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Creates a copy of this configuration with the given secret ID.
     *
     * @param authToken the secret ID to use in the new configuration.
     */
    public NomadApiConfiguration withAuthToken(String authToken) {
        return new NomadApiConfiguration(address, region, namespace, authToken, tls);
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
     *
     * @param address HTTP address of the agent to connect to, as an HTTP or HTTPS URL
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
         *
         * @param caCertificateFile     path to the CA certificate
         * @param clientCertificateFile path to the client certificate
         * @param clientKeyFile         path to the client key
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
        private String namespace;
        private String authToken;
        private String tlsCaFile;
        private String tlsCertFile;
        private String tlsKeyFile;

        /**
         * Sets the HTTP address of the agent to connect to.
         *
         * @param address HTTP address of the agent to connect to, as an HTTP or HTTPS URL
         * @return this builder object, to allow method chaining
         */
        public Builder setAddress(final String address) {
            return setAddress(nomadAddressAsHttpHost(address));
        }

        /**
         * Sets the HTTP address of the agent to connect to.
         *
         * @param address HTTP address of the agent to connect to
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
         *
         * @param region default region to forward requests to, or null to use the region of the agent we connect to
         */
        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        /**
         * Sets the namespace to use in requests by default.
         * <p>
         * If null, the defailt namespace will be used.
         *
         * @param namespace the namespace to use
         */
        public Builder setNamespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        /**
         * Sets the secret ID for the client to use.
         *
         * @param authToken the secret ID
         */
        public Builder setAuthToken(String authToken) {
            this.authToken = authToken;
            return this;
        }

        /**
         * Sets the path of the CA certificate, containing the certificate authority certificate
         * to use to verify the agent's TLS certificate.
         *
         * @param tlsCaFile path to the CA certificate
         */
        public Builder setTlsCaFile(String tlsCaFile) {
            this.tlsCaFile = tlsCaFile;
            return this;
        }

        /**
         * Sets the path of the client's certificate and private key files,
         * used when communicating with an agent over TLS.
         *
         * @param tlsCertFile path to the client certificate
         * @param tlsKeyFile  path to the client key
         */
        public Builder setTlsCertAndKeyFiles(String tlsCertFile, String tlsKeyFile) {
            this.tlsCertFile = tlsCertFile;
            this.tlsKeyFile = tlsKeyFile;
            return this;
        }

        /**
         * Looks for common Nomad environment variables in the given map, and sets any values found there.
         * <p>
         * The following variables are recognised:
         * <ul>
         * <li>{@code NOMAD_ADDR}</li>
         * <li>{@code NOMAD_CA_CERT}</li>
         * <li>{@code NOMAD_CLIENT_CERT}</li>
         * <li>{@code NOMAD_CLIENT_KEY}</li>
         * </ul>
         *
         * @param environment environment variable map, as returned by {@link System#getenv()}.
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

            if (environment.containsKey("NOMAD_REGION")) {
                String region = environment.get("NOMAD_REGION");
                if (!region.isEmpty())
                    this.region = region;
            }

            if (environment.containsKey("NOMAD_NAMESPACE")) {
                String namespace = environment.get("NOMAD_NAMESPACE");
                if (!namespace.isEmpty())
                    this.namespace = namespace;
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

            if (environment.containsKey("NOMAD_TOKEN")) {
                String token = environment.get("NOMAD_TOKEN");
                if (!token.isEmpty())
                    this.authToken = token;
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

            return new NomadApiConfiguration(address, region, namespace, authToken, tls);
        }
    }
}
