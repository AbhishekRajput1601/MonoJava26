package com.project.app.dao;

import com.project.app.model.BranchModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDataAccessObject {

    public boolean insertBranch(Connection connection, String branchName) throws SQLException {
        String sql = "INSERT INTO branch (branch_name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, branchName);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean isBranchExists(Connection connection, String branchName) throws SQLException {
        String sql = "SELECT 1 FROM branch WHERE branch_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, branchName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean updateBranchName(Connection connection, int branchId, String newName) throws SQLException {
        String sql = "UPDATE branch SET branch_name = ? WHERE branch_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, branchId);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteBranchById(Connection connection, int branchId) throws SQLException {
        String sql = "DELETE FROM branch WHERE branch_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, branchId);
            return ps.executeUpdate() == 1;
        }
    }

    public List<BranchModel> fetchAllBranches(Connection connection) throws SQLException {
        String sql = "SELECT branch_id, branch_name FROM branch ORDER BY branch_name";
        List<BranchModel> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new BranchModel(rs.getInt("branch_id"), rs.getString("branch_name")));
            }
        }
        return out;
    }
}

