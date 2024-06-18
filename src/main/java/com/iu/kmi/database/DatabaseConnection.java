package com.iu.kmi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Utility class for defining and resolving the database credentials
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://159.69.241.119:3306/dev3_db";
    private static final String USER = "dev3";
    private static final String PASSWORD = "dev3_passwort";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
