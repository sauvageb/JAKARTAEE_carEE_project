package com.example.app.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:8889/caree";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // SINGLETON INSTANCE
    private static Connection connection;

    private ConnectionManager() {
        // Avoid instantiate
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                loadDriver();
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);

            } catch (SQLException e) {
                throw new RuntimeException("Cannot create connection");
            }
        }
        return connection;
    }

    private static void loadDriver() {
        // Loading Database driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver MySQL not found");
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Cannot closing connection");
        }
    }

}
