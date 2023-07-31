package com.example.customerinformationsystem.error;

public class NoCustomersToSaveException extends RuntimeException {

    public NoCustomersToSaveException(String message) {
        super(message);
    }
}
