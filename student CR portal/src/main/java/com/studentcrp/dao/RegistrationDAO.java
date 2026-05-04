package com.studentcrp.dao;

import com.studentcrp.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDAO {

    public boolean saveRegistration(String studentName, String email, int age,
                                   String courseName, String batchTime) {
        String sql = "INSERT INTO registrations (student_name, email, age, course_name, batch_time) " +
                     "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setString(1, studentName);
            pstmt.setString(2, email);
            pstmt.setInt(3, age);
            pstmt.setString(4, courseName);
            pstmt.setString(5, batchTime);

            // Execute update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student registration saved successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error saving registration: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }
}

