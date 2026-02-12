package com.tonyfan.sftp.exception;

/**
 * Custom exception for SFTP operations
 */
public class SFTPException extends RuntimeException {
    public SFTPException(String message) {
        super(message);
    }

    public SFTPException(String message, Throwable cause) {
        super(message, cause);
    }

    public SFTPException(Throwable cause) {
        super(cause);
    }
}