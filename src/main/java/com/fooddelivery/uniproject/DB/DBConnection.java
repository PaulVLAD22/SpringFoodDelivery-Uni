package com.fooddelivery.uniproject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //DB Credentials
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass";

    //DB URL
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/food_delivery";

    public Connection connection;
    private static DBConnection dbConnection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static Connection getConnection() throws Exception {
        if (dbConnection == null)
            dbConnection = new DBConnection();
        return dbConnection.connection;
    }

}
