package com.tonyfan.sftp.pool;

/**
 * Configuration for a single SFTP connection
 */
public class ConnectionConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String privateKeyPath;
    private String privateKeyPassphrase;
    private int connectionTimeout;
    private String serverName;
    private AuthType authType;

    public enum AuthType {
        PASSWORD,
        PRIVATE_KEY,
        BOTH
    }

    public ConnectionConfig(String serverName, String host, int port, String username, String password) {
        this.serverName = serverName;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.connectionTimeout = 30000;
        this.authType = AuthType.PASSWORD;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getPrivateKeyPassphrase() {
        return privateKeyPassphrase;
    }

    public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
        this.privateKeyPassphrase = privateKeyPassphrase;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    @Override
    public String toString() {
        return "ConnectionConfig{" +
                "serverName='" + serverName + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", authType=" + authType +
                ", connectionTimeout=" + connectionTimeout +
                '}';
    }
}