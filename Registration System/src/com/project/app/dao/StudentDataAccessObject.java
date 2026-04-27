package com.project.app.dao;

import com.project.app.exceptions.DataAccessException;
import com.project.app.exceptions.DuplicateEntityException;
import com.project.app.model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public class StudentDataAccessObject {

    public boolean insertStudent(Connection connection, StudentModel student) {
        try {
            // Resolve or create branch_id from branch name
            int branchId = getOrCreateBranchId(connection, student.getStudentBranch());
            String sql = "INSERT INTO student (id, name, age, branch_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, student.getStudentId());
                ps.setString(2, student.getStudentName());
                ps.setInt(3, student.getStudentAge());
                ps.setInt(4, branchId);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DuplicateEntityException("Student with ID already exists: " + student.getStudentId(), ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean isStudentExists(Connection connection, int studentId) {
        try {
            String sql = "SELECT 1 FROM student WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public Optional<StudentModel> findStudentById(Connection connection, int studentId) {
        try {
            String sql = "SELECT s.id, s.name, s.age, b.branch_name AS branch FROM student s LEFT JOIN branch b ON s.branch_id = b.branch_id WHERE s.id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) return Optional.empty();
                    return Optional.of(new StudentModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("branch")
                    ));
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public int updateStudentDetails(Connection connection, int studentId, String studentName, String studentBranch) {
        try {
            int branchId = getOrCreateBranchId(connection, studentBranch);
            String sql = "UPDATE student SET name = ?, branch_id = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, studentName);
                ps.setInt(2, branchId);
                ps.setInt(3, studentId);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public int deleteStudentById(Connection connection, int studentId) {
        try {
            String sql = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    private int getOrCreateBranchId(Connection connection, String branchName) {
        try {
            if (branchName == null) branchName = "";
            String selectSql = "SELECT branch_id FROM branch WHERE branch_name = ?";
            try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
                ps.setString(1, branchName);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("branch_id");
                }
            }

            String insertSql = "INSERT INTO branch (branch_name) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, branchName);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
            // Fallback
            throw new DataAccessException("Unable to resolve or create branch id for: " + branchName);
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DuplicateEntityException("Branch already exists: " + branchName, ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }
}
