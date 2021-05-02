package com.fooddelivery.uniproject.exception;

public class NonExistentId extends RuntimeException {
    public NonExistentId(){
        super("No such element with this id");
    }
}
