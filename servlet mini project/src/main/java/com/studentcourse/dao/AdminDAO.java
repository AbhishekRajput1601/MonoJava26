package com.studentcourse.dao;

import com.studentcourse.model.Admin;
import com.studentcourse.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

    public Admin validateAdmin(String username, String password) {
        Admin admin = null;
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error validating admin", e);
        }
        return admin;
    }

    public Admin getAdminByUsername(String username) {
        Admin admin = null;
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching admin by username", e);
        }
        return admin;
    }
}

