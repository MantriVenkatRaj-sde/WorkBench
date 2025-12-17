package com.mantrivenkatraj.workbench.exceptions;

public abstract class ApplicationException extends RuntimeException {

    private final int statusCode;

    protected ApplicationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
