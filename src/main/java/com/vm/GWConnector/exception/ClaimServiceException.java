package com.vm.GWConnector.exception;

public class ClaimServiceException extends RuntimeException {

    public ClaimServiceException(String message) {
        super(message);
    }

    public ClaimServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
