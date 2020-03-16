package com.codecool.zsana.salesdb;

import java.sql.*;
import java.lang.*;

public class DatabaseConnection {

    private String DATABASE = "jdbc:postgresql://localhost:5432/normalized_sales";
    private String DB_USER = System.getenv("DB_USER");
    private String DB_PASSWORD = System.getenv("DB_PASSWORD");

    DatabaseConnection() {}

    public DatabaseConnection(String database, String dbUser, String dbPassword) {
        this.DATABASE = database;
        this.DB_USER = dbUser;
        this.DB_PASSWORD = dbPassword;
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
