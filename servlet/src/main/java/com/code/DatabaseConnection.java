package com.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Abhishek@1137";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("✓ MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Failed to load MySQL JDBC Driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Database Connected Successfully!");
            System.out.println("✓ Database URL: " + DB_URL);
            System.out.println("✓ Database User: " + DB_USER);
            return conn;
        } catch (SQLException e) {
            System.err.println("✗ Database connection failed: " + e.getMessage());
            throw e;
        }
    }

}
