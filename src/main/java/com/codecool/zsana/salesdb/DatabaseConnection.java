package com.codecool.zsana.salesdb;

import java.sql.*;
import java.lang.*;

class DatabaseConnection {

    private String DATABASE = "jdbc:postgresql://localhost:5432/normalized_sales";
    private String DB_USER = System.getProperty("DB_USER");
    private String DB_PASSWORD = System.getProperty("DB_PASSWORD");

    DatabaseConnection() {}

    DatabaseConnection(String database, String user, String password) {
        this.DATABASE = database;
        this.DB_USER = user;
        this.DB_PASSWORD = password;
    }

    Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
