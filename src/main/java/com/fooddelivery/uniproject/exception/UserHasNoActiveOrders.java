package com.fooddelivery.uniproject.exception;

public class UserHasNoActiveOrders extends RuntimeException{
    public UserHasNoActiveOrders() {
        super("User has no active orders");
    }
}
