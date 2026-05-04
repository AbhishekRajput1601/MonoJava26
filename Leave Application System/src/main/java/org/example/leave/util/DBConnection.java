package org.example.leave.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/employee_leave_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "Abhishek@1137";

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties properties = new Properties();
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Failed to load db.properties: " + e.getMessage());
        }

        URL = resolveValue("DB_URL", properties.getProperty("db.url"), DEFAULT_URL);
        USER = resolveValue("DB_USER", properties.getProperty("db.user"), DEFAULT_USER);
        PASSWORD = resolveValue("DB_PASSWORD", properties.getProperty("db.password"), DEFAULT_PASSWORD);

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load JDBC driver: " + e.getMessage());
        }
    }

    private DBConnection() {
    }

    private static String resolveValue(String envKey, String propertyValue, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return propertyValue.trim();
        }
        return defaultValue;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            throw e;
        }
    }

}

