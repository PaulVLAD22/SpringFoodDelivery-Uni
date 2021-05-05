package com.fooddelivery.uniproject.exception;

public class LocalHasNoSuchProduct extends RuntimeException{
    public LocalHasNoSuchProduct() {
        super("Local has no such product");
    }
}
