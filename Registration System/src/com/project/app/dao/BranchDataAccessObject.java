package com.project.app.dao;

import com.project.app.exceptions.DataAccessException;
import com.project.app.exceptions.DuplicateEntityException;
import com.project.app.model.BranchModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class BranchDataAccessObject {

    public boolean insertBranch(Connection connection, String branchName) {
        try {
            String sql = "INSERT INTO branch (branch_name) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, branchName);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DuplicateEntityException("Branch already exists: " + branchName, ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean isBranchExists(Connection connection, String branchName) {
        try {
            String sql = "SELECT 1 FROM branch WHERE branch_name = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, branchName);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean updateBranchName(Connection connection, int branchId, String newName) {
        try {
            String sql = "UPDATE branch SET branch_name = ? WHERE branch_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setInt(2, branchId);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean deleteBranchById(Connection connection, int branchId) {
        try {
            String sql = "DELETE FROM branch WHERE branch_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, branchId);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new com.project.app.exceptions.ForeignKeyConstraintException("Cannot delete branch: it is referenced by students.", ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public List<BranchModel> fetchAllBranches(Connection connection) {
        String sql = "SELECT branch_id, branch_name FROM branch ORDER BY branch_name";
        List<BranchModel> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new BranchModel(rs.getInt("branch_id"), rs.getString("branch_name")));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }
}

