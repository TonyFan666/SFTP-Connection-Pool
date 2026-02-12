package com.tonyfan.sftp.pool;

/**
 * Configuration for the connection pool
 */
public class PoolConfig {
    private int minPoolSize;
    private int maxPoolSize;
    private long idleTimeout;
    private int maxRetries;
    private long retryDelayMs;
    private long healthCheckInterval;
    private boolean enableHealthCheck;

    public PoolConfig() {
        this.minPoolSize = 5;
        this.maxPoolSize = 20;
        this.idleTimeout = 600000;
        this.maxRetries = 3;
        this.retryDelayMs = 1000;
        this.healthCheckInterval = 300000;
        this.enableHealthCheck = true;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public long getRetryDelayMs() {
        return retryDelayMs;
    }

    public void setRetryDelayMs(long retryDelayMs) {
        this.retryDelayMs = retryDelayMs;
    }

    public long getHealthCheckInterval() {
        return healthCheckInterval;
    }

    public void setHealthCheckInterval(long healthCheckInterval) {
        this.healthCheckInterval = healthCheckInterval;
    }

    public boolean isEnableHealthCheck() {
        return enableHealthCheck;
    }

    public void setEnableHealthCheck(boolean enableHealthCheck) {
        this.enableHealthCheck = enableHealthCheck;
    }
}