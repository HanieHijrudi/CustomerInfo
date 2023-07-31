package com.example.customerinformationsystem.error;

public class CustomerDetailsInvalidException extends RuntimeException{
    public CustomerDetailsInvalidException(String message) {
        super(message);
    }
}
