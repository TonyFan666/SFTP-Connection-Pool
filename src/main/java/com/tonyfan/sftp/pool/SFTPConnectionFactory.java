package com.tonyfan.sftp.pool;

import com.jcraft.jsch.*;
import com.tonyfan.sftp.client.SFTPClient;
import com.tonyfan.sftp.exception.SFTPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

public class SFTPConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(SFTPConnectionFactory.class);
    private final ConnectionConfig config;
    private final JSch jsch;

    public SFTPConnectionFactory(ConnectionConfig config) {
        this.config = config;
        this.jsch = new JSch();
        initializePrivateKeys();
    }

    private void initializePrivateKeys() {
        if ((config.getAuthType() == ConnectionConfig.AuthType.PRIVATE_KEY || config.getAuthType() == ConnectionConfig.AuthType.BOTH) && config.getPrivateKeyPath() != null && !config.getPrivateKeyPath().isEmpty()) {
            try {
                String keyPath = config.getPrivateKeyPath();
                String passphrase = config.getPrivateKeyPassphrase();
                if (passphrase != null && !passphrase.isEmpty()) {
                    jsch.addIdentity(keyPath, passphrase);
                    logger.debug("Added private key with passphrase: {}", keyPath);
                } else {
                    jsch.addIdentity(keyPath);
                    logger.debug("Added private key: {}", keyPath);
                }
            } catch (JSchException e) {
                logger.error("Failed to load private key: {}", config.getPrivateKeyPath(), e);
                throw new SFTPException("Failed to load private key", e);
            }
        }
    }

    public SFTPClient createConnection() {
        try {
            Session session = createSession();
            ChannelSftp channelSftp = createChannelSftp(session);
            return new SFTPClient(session, channelSftp, config);
        } catch (JSchException e) {
            logger.error("Failed to create SFTP connection for server: {}", config.getServerName(), e);
            throw new SFTPException("Failed to create SFTP connection", e);
        }
    }

    private Session createSession() throws JSchException {
        Session session = jsch.getSession(config.getUsername(), config.getHost(), config.getPort());
        if (config.getAuthType() == ConnectionConfig.AuthType.PASSWORD) {
            if (config.getPassword() != null && !config.getPassword().isEmpty()) {
                session.setPassword(config.getPassword());
                logger.debug("Using password authentication for: {}@{}", config.getUsername(), config.getHost());
            } else {
                throw new SFTPException("Password authentication selected but no password provided");
            }
        } else if (config.getAuthType() == ConnectionConfig.AuthType.PRIVATE_KEY) {
            logger.debug("Using private key authentication for: {}@{}", config.getUsername(), config.getHost());
        } else if (config.getAuthType() == ConnectionConfig.AuthType.BOTH) {
            if (config.getPassword() != null && !config.getPassword().isEmpty()) {
                session.setPassword(config.getPassword());
            }
            logger.debug("Using combined password and private key authentication");
        }
        Properties sessionConfig = new Properties();
        sessionConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sessionConfig);
        session.setTimeout(config.getConnectionTimeout());
        logger.debug("Connecting to SFTP server: {}:{}", config.getHost(), config.getPort());
        session.connect();
        return session;
    }

    private ChannelSftp createChannelSftp(Session session) throws JSchException {
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        return channelSftp;
    }
}