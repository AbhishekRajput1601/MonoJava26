package com.code;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public static void addUser(String name, int age, String branch, int marks) {
        String query = "INSERT INTO user (id, name, age, branch, marks) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            int nextId = getNextUserId();
            pstmt.setInt(1, nextId);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, branch);
            pstmt.setInt(5, marks);

            pstmt.executeUpdate();
            logger.info("User added successfully: ID=" + nextId + ", Name=" + name);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating user: " + name, e);
        }
    }

    public static int getNextUserId() {
        String query = "SELECT MAX(id) FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int maxId = rs.getInt(1);
                return maxId + 1;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving next user ID", e);
        }
        return 1;
    }

    public static User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("branch"),
                    rs.getInt("marks")
                );
                logger.fine("User retrieved: ID=" + id);
                return user;
            }
            logger.fine("User not found: ID=" + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving user with ID: " + id, e);
        }
        return null;
    }

    public static List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("branch"),
                    rs.getInt("marks")
                ));
            }
            logger.info("Retrieved " + users.size() + " users from database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all users", e);
        }
        return users;
    }

    public static boolean updateUser(int id, String name, int age, String branch, int marks) {
        String query = "UPDATE user SET name = ?, age = ?, branch = ?, marks = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, branch);
            pstmt.setInt(4, marks);
            pstmt.setInt(5, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("User updated successfully: ID=" + id);
                return true;
            }
            logger.warning("Update failed: User not found with ID=" + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user with ID: " + id, e);
        }
        return false;
    }

    public static boolean deleteUser(int id) {
        String query = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("User deleted successfully: ID=" + id);
                return true;
            }
            logger.warning("Delete failed: User not found with ID=" + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user with ID: " + id, e);
        }
        return false;
    }

}
