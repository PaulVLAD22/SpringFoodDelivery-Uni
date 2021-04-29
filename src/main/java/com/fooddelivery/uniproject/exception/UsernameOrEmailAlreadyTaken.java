package com.fooddelivery.uniproject.exception;

public class UsernameOrEmailAlreadyTaken extends RuntimeException {
    public UsernameOrEmailAlreadyTaken(){
        super("Username or email already taken");
    }
}

