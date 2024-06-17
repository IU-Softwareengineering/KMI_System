package com.iu.kmi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Utility class for defining and resolving the database credentials
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://159.69.241.119:3306/<DATABASE>";
    private static final String USER = "<USER>";
    private static final String PASSWORD = "<PASSWORD>";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
