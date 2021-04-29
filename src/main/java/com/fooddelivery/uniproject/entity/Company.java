package com.fooddelivery.uniproject.entity;


import com.fooddelivery.uniproject.entity.account.Account;
import com.fooddelivery.uniproject.entity.account.Admin;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.order.Order;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Company {
    private static Company instance = null;

    private Set<Local> locals = new HashSet<>();
    private List<Account> costumers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    // Singleton
    private Company() {
    }

    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }

    public void setCostumers(List<Account> costumers) {
        this.costumers = costumers;
        for (Account account : costumers) {
            if (account instanceof User) {
                this.getUsers().add((User) account);
            } else {
                this.getDrivers().add((Driver) account);
            }
        }
    }

    // Set orders seteaza la useri comenzile
    public void setOrders(List<Order> orders) {
        orders.stream().forEach(order -> {
            order.getDriver().setCurrentOrder(order);
        });
    }
}
