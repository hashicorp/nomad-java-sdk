package com.hashicorp.nomad.testutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A partial representation of the configuration of a Nomad agent,
 * to create configurations for test purposes.
 *
 * Configurations should be created using a {@link NomadAgentConfiguration.Builder}.
 */
public class NomadAgentConfiguration {

    private static final ObjectMapper CONFIG_MAPPER =
            new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private final String region;
    private final String name;
    @JsonProperty("data_dir")
    private final Path dataDir;
    @JsonProperty("log_level")
    private final String logLevel;
    @JsonProperty("bind_addr")
    private final String bindAddr;
    private final AdvertiseAddrs advertise;
    private final Ports ports;
    private final Server server;
    private final Client client;
    @JsonProperty("disable_update_check")
    private final boolean disableCheckpoint;
    private final Consul consul;
    private final Tls tls;

    NomadAgentConfiguration(String region, String name, Path dataDir, String logLevel, String bindAddr,
                            AdvertiseAddrs advertise, Ports ports, Server server, Client client,
                            boolean disableCheckpoint, Consul consul, Tls tls) {
        this.region = region;
        this.name = name;
        this.dataDir = dataDir;
        this.logLevel = logLevel;
        this.bindAddr = bindAddr;
        this.advertise = advertise;
        this.ports = ports;
        this.server = server;
        this.client = client;
        this.disableCheckpoint = disableCheckpoint;
        this.consul = consul;
        this.tls = tls;
    }

    /**
     * Returns the agent's region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Returns the agent's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the data directory.
     */
    public Path getDataDir() {
        return dataDir;
    }

    /**
     * Returns the address Nomad should bind to.
     */
    public String getBindAddr() {
        return bindAddr;
    }

    /**
     * Returns the advertised addresses configuration.
     */
    public AdvertiseAddrs getAdvertise() {
        return advertise;
    }

    /**
     * Returns the log level.
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Returns the ports configuration.
     */
    public Ports getPorts() {
        return ports;
    }

    /**
     * Returns the server configuration.
     */
    public Server getServer() {
        return server;
    }

    /**
     * Returns the client configuration.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns true iff Checkpoint is disabled.
     */
    public boolean isDisableCheckpoint() {
        return disableCheckpoint;
    }

    /**
     * Returns the Consul configuration.
     */
    public Consul getConsul() {
        return consul;
    }

    /**
     * Returns the TLS configuration.
     */
    public Tls getTls() {
        return tls;
    }

    /**
     * Returns the configuration as JSON.
     */
    public String asJson() {
        try {
            return CONFIG_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error trying to serialize Nomad agent configuration to JSON");
        }
    }

    @Override
    public String toString() {
        return asJson();
    }

    /**
     * Advertised addresses configuration.
     */
    public static class AdvertiseAddrs {

        private final String http;
        private final String rpc;
        private final String serf;

        /**
         * Creates a new advertised addresses configuration.
         */
        public AdvertiseAddrs(String http, String rpc, String serf) {

            this.http = http;
            this.rpc = rpc;
            this.serf = serf;
        }

        /**
         * Returns the advertised HTTP/HTTPS address.
         */
        public String getHttp() {
            return http;
        }

        /**
         * Returns the advertised RPC address.
         */
        public String getRpc() {
            return rpc;
        }

        /**
         * Returns the advertised Serf address.
         */
        public String getSerf() {
            return serf;
        }
    }

    /**
     * Ports configuration.
     */
    public static class Ports {

        private final int http;
        private final int rpc;
        private final int serf;

        /**
         * Creates a Ports configuration.
         */
        public Ports(int http, int rpc, int serf) {
            this.http = http;
            this.rpc = rpc;
            this.serf = serf;
        }

        /**
         * Returns the HTTP/HTTPS port.
         */
        public int getHttp() {
            return http;
        }

        /**
         * Returns the RPC port.
         */
        public int getRpc() {
            return rpc;
        }

        /**
         * Returns the Serf port.
         */
        public int getSerf() {
            return serf;
        }
    }

    /**
     * Server configuration.
     */
    public static class Server {
        private final boolean enabled;
        @JsonProperty("bootstrap_expect")
        private final int bootstrapExpect;
        @JsonProperty("start_join")
        private final String[] startJoin;

        /**
         * Creates a new Server configuration.
         */
        public Server(boolean enabled, int bootstrapExpect, String[] startJoin) {
            this.enabled = enabled;
            this.bootstrapExpect = bootstrapExpect;
            this.startJoin = startJoin;
        }

        /**
         * Returns true iff the server is enabled.
         */
        public boolean getEnabled() {
            return enabled;
        }

        /**
         * Returns the number of servers to expect for bootstrapping, or 0 if bootstrapping is disabled.
         */
        public int getBootstrapExpect() {
            return bootstrapExpect;
        }

        /**
         * Returns the servers to try joining when starting.
         */
        public String[] getStartJoin() {
            return startJoin;
        }
    }

    /**
     * Client configuration.
     */
    public static class Client {
        private final boolean enabled;
        private final Map<String, String> options;

        /**
         * Creates a new Client configuration.
         */
        public Client(boolean enabled, Map<String, String> options) {
            this.enabled = enabled;
            this.options = options;
        }

        /**
         * Returns true iff the client is enabled.
         */
        public boolean getEnabled() {
            return enabled;
        }

        /**
         * Returns the client options map.
         */
        public Map<String, String> getOptions() {
            return options;
        }
    }

    /**
     * Consul configuration.
     */
    public static class Consul {
        @JsonProperty("auto_advertise")
        private final boolean autoAdvertise;
        @JsonProperty("server_auto_join")
        private final boolean serverAutoJoin;
        @JsonProperty("client_auto_join")
        private final boolean clientAutoJoin;

        /**
         * Creates a new Consul configuration.
         */
        public Consul(boolean autoAdvertise, boolean serverAutoJoin, boolean clientAutoJoin) {
            this.autoAdvertise = autoAdvertise;
            this.serverAutoJoin = serverAutoJoin;
            this.clientAutoJoin = clientAutoJoin;
        }

        /**
         * Returns true iff the client should advertise services in Consul.
         */
        public boolean isAutoAdvertise() {
            return autoAdvertise;
        }

        /**
         * Returns true iff the server should use Consul to find other servers.
         */
        public boolean isServerAutoJoin() {
            return serverAutoJoin;
        }

        /**
         * Returns true iff the client should use Consul to find servers.
         */
        public boolean isClientAutoJoin() {
            return clientAutoJoin;
        }
    }

    /**
     * TLS configuration.
     */
    public static class Tls {
        private final boolean http;
        @JsonProperty("ca_file")
        private final String caFile;
        @JsonProperty("cert_file")
        private final String certFile;
        @JsonProperty("key_file")
        private final String keyFile;

        /**
         * Creates a new TLS configuration.
         */
        public Tls(boolean http, String caFile, String certFile, String keyFile) {
            this.http = http;
            this.caFile = caFile;
            this.certFile = certFile;
            this.keyFile = keyFile;
        }

        /**
         * Returns true iff TLS is enabled for HTTP (HTTPS).
         */
        public boolean isHttp() {
            return http;
        }

        /**
         * Sets the certificate authority file to use for TLS.
         */
        public String getCaFile() {
            return caFile;
        }

        /**
         * Sets the certificate file to use for TLS.
         */
        public String getCertFile() {
            return certFile;
        }

        /**
         * Sets the certificate key to use for TLS.
         */
        public String getKeyFile() {
            return keyFile;
        }
    }

    /**
     * A configuration builder.
     */
    public static class Builder {

        private static AtomicInteger buildCount = new AtomicInteger();

        private String region;
        private String name;
        private Path dataDir;
        private String bindAddr = "127.0.0.1";
        private String httpAdvertiseAddr;
        private String rpcAdvertiseAddr;
        private String serfAdvertiseAddr;
        private int httpPort;
        private int rpcPort;
        private int serfPort;
        private boolean serverEnabled = true;
        private int serverBootstrapExpect = 1;
        private String[] serverStartJoin;
        private boolean clientEnabled = false;
        private HashMap<String, String> clientOptions;
        private boolean consulAutoAdvertise = false;
        private boolean consulServerAutoJoin = false;
        private boolean consulClientAutoJoin = false;
        private boolean tlsHttp = false;
        private String tlsCaFile;
        private String tlsCertFile;
        private String tlsKeyFile;

        /**
         * Builds a new configuration using the values in the builder.
         *
         * If the node name or port numbers haven't been explicitly set, they will be automatically assigned.
         */
        public NomadAgentConfiguration build() {
            int id = buildCount.incrementAndGet();

            String name = this.name == null ? "node" + id : this.name;

            AdvertiseAddrs advertise = new AdvertiseAddrs(
                    httpAdvertiseAddr != null ? httpAdvertiseAddr : bindAddr,
                    rpcAdvertiseAddr != null ? rpcAdvertiseAddr : bindAddr,
                    serfAdvertiseAddr != null ? serfAdvertiseAddr : bindAddr
            );

            Ports ports = new Ports(
                    httpPort == 0 ? 20000 + id : httpPort,
                    rpcPort == 0 ? 21000 + id : rpcPort,
                    serfPort == 0 ? 22000 + id : serfPort);

            Server server = new Server(serverEnabled, serverBootstrapExpect, serverStartJoin);

            Consul consul = new Consul(consulAutoAdvertise, consulServerAutoJoin, consulClientAutoJoin);

            Tls tls = new Tls(tlsHttp, tlsCaFile, tlsCertFile, tlsKeyFile);

            return new NomadAgentConfiguration(region, name, dataDir, "DEBUG", bindAddr, advertise, ports, server,
                    new Client(clientEnabled, clientOptions), true, consul, tls);
        }

        /**
         * Sets the agent's region.
         */
        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        /**
         * Sets the agent's name.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the data directory.
         */
        public Builder setDataDir(Path dataDir) {
            this.dataDir = dataDir;
            return this;
        }

        /**
         * Sets the address Nomad should bind to.
         */
        public Builder setBindAddr(String bindAddr) {
            this.bindAddr = bindAddr;
            return this;
        }

        /**
         * Sets the HTTP port.
         */
        public Builder setHttpPort(int httpPort) {
            this.httpPort = httpPort;
            return this;
        }

        /**
         * Sets the RPC port.
         */
        public Builder setRpcPort(int rpcPort) {
            this.rpcPort = rpcPort;
            return this;
        }

        /**
         * Sets the Serf port.
         */
        public Builder setSerfPort(int serfPort) {
            this.serfPort = serfPort;
            return this;
        }

        /**
         * Sets the advertised HTTP address.
         */
        public Builder setHttpAdvertiseAddr(String httpAdvertiseAddr) {
            this.httpAdvertiseAddr = httpAdvertiseAddr;
            return this;
        }

        /**
         * Sets the advertised RPC address.
         */
        public Builder setRpcAdvertiseAddr(String rpcAdvertiseAddr) {
            this.rpcAdvertiseAddr = rpcAdvertiseAddr;
            return this;
        }

        /**
         * Sets the advertised Serf address.
         */
        public Builder setSerfAdvertiseAddr(String serfAdvertiseAddr) {
            this.serfAdvertiseAddr = serfAdvertiseAddr;
            return this;
        }

        /**
         * Enables or disables the Nomad server in the agent.
         */
        public Builder setServerEnabled(boolean serverEnabled) {
            this.serverEnabled = serverEnabled;
            return this;
        }

        /**
         * Sets the number of servers to expect for bootstrapping, or disables bootstrapping if 0 is specified.
         */
        public Builder setServerBootstrapExpect(int serverBootstrapExpect) {
            this.serverBootstrapExpect = serverBootstrapExpect;
            return this;
        }

        /**
         * Sets the servers to try joining when starting.
         */
        public Builder setServerStartJoin(String... serverStartJoin) {
            this.serverStartJoin = serverStartJoin;
            return this;
        }

        /**
         * Enables or disables the client.
         */
        public Builder setClientEnabled(boolean clientEnabled) {
            this.clientEnabled = clientEnabled;
            return this;
        }

        /**
         * Adds a client option.
         */
        public Builder addClientOption(String key, String value) {
            if (clientOptions == null)
                clientOptions = new HashMap<>();
            clientOptions.put(key, value);
            return this;
        }

        /**
         * Enables or disables TLS for HTTP connections (HTTPS).
         */
        public Builder setTlsHttp(boolean tlsHttp) {
            this.tlsHttp = tlsHttp;
            return this;
        }

        /**
         * Sets the certificate authority file to use for TLS.
         */
        public Builder setTlsCaFile(String tlsCaFile) {
            this.tlsCaFile = tlsCaFile;
            return this;
        }

        /**
         * Sets the certificate file and and key to use for TLS.
         */
        public Builder setTlsCertAndKeyFiles(String tlsCertFile, String tlsKeyFile) {
            this.tlsCertFile = tlsCertFile;
            this.tlsKeyFile = tlsKeyFile;
            return this;
        }
    }
}
