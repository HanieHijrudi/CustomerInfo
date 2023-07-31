package com.example.customerinformationsystem.error;

public class NoCustomersToSaveException extends RuntimeException {

    public NoCustomersToSaveException() {
        super();
    }

    public NoCustomersToSaveException(String message) {
        super(message);
    }

    public NoCustomersToSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCustomersToSaveException(Throwable cause) {
        super(cause);
    }

    protected NoCustomersToSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
