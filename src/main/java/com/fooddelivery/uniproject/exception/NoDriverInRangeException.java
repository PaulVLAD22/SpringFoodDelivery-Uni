package com.fooddelivery.uniproject.exception;

public class NoDriverInRangeException extends RuntimeException {
    public NoDriverInRangeException() {
        super("There is no driver in range 1000 of the local.");
    }
}
