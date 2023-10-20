package com.example.splogin.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {

                String host = "eventtracker.ctgvnaaozzpy.us-east-2.rds.amazonaws.com";
                String user = "admin";
                String password = "Mnas1506$";
                String database = "eventtracker";
                int port = 3306;

                String url = "jdbc:mysql://" + host + ":" + port + "/" + database;


                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

