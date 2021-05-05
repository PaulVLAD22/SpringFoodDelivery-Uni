package com.fooddelivery.uniproject.exception;

public class NoUserWithThisUsername extends RuntimeException{
    public NoUserWithThisUsername(){
        super("No user with this username");
    }
}
