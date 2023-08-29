package com.api.bank.application.exception;

public class NoContentException extends Exception {

    private static final long serialVersionUID = 1623770721255869181L;

    private String serviceName;

    public NoContentException() {
        super();

    }

    public NoContentException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
    public NoContentException(String message, Throwable cause) {
        super(message, cause);

    }
    public NoContentException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
    }
    public NoContentException(String message) {
        super(message);

    }
    public NoContentException(Throwable cause) {
        super(cause);

    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
