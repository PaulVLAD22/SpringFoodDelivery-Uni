package com.fooddelivery.uniproject;

import com.fooddelivery.uniproject.DB.DBConnection;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.fooddelivery.uniproject.repository.UserRepository;
import com.fooddelivery.uniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class UniprojectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UniprojectApplication.class, args);

    }

}