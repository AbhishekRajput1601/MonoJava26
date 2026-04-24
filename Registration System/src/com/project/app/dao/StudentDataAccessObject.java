package com.project.app.dao;

import com.project.app.model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentDataAccessObject {

    public boolean insertStudent(Connection connection, StudentModel student) throws SQLException {
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
    }

    public boolean isStudentExists(Connection connection, int studentId) throws SQLException {
        String sql = "SELECT 1 FROM student WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Optional<StudentModel> findStudentById(Connection connection, int studentId) throws SQLException {
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
    }

    public int updateStudentDetails(Connection connection, int studentId, String studentName, String studentBranch) throws SQLException {
        int branchId = getOrCreateBranchId(connection, studentBranch);
        String sql = "UPDATE student SET name = ?, branch_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentName);
            ps.setInt(2, branchId);
            ps.setInt(3, studentId);
            return ps.executeUpdate();
        }
    }

    public int deleteStudentById(Connection connection, int studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            return ps.executeUpdate();
        }
    }

    private int getOrCreateBranchId(Connection connection, String branchName) throws SQLException {
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
        throw new SQLException("Unable to resolve or create branch id for: " + branchName);
    }
}
