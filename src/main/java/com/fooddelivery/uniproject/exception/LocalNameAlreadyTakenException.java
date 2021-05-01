package com.fooddelivery.uniproject.exception;

public class LocalNameAlreadyTakenException extends RuntimeException {
    public LocalNameAlreadyTakenException() {
        super("Local Name Already Taken");
    }
}
