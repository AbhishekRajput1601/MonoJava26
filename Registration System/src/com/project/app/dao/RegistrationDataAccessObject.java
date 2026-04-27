package com.project.app.dao;

import com.project.app.exceptions.DataAccessException;
import com.project.app.exceptions.DuplicateEntityException;
import com.project.app.model.CourseModel;
import com.project.app.model.RegistrationModel;

import java.sql.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDataAccessObject {

    public boolean isDuplicateRegistration(Connection connection, int studentId, int courseId) {
        try {
            String sql = "SELECT 1 FROM registration WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                ps.setInt(2, courseId);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean insertRegistration(Connection connection, int studentId, int courseId, double feesPaid) {
        try {
            String sql = "INSERT INTO registration (student_id, course_id, fees_paid) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                ps.setInt(2, courseId);
                ps.setDouble(3, feesPaid);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DuplicateEntityException("Duplicate registration for student " + studentId + " and course " + courseId, ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public int updateCourseFee(Connection connection, int studentId, int courseId, double fee) {
        try {
            String sql = "UPDATE registration SET fees_paid = ? WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, fee);
                ps.setInt(2, studentId);
                ps.setInt(3, courseId);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public int deleteRegistration(Connection connection, int studentId, int courseId) {
        try {
            String sql = "DELETE FROM registration WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                ps.setInt(2, courseId);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public void deleteRegistrationsByStudentId(Connection connection, int studentId) {
        try {
            String sql = "DELETE FROM registration WHERE student_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public void deleteRegistrationsByCourseId(Connection connection, int courseId) {
        try {
            String sql = "DELETE FROM registration WHERE course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, courseId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public List<RegistrationModel> findRegistrationsByStudentId(Connection connection, int studentId) {
        String sql = "SELECT r.reg_id, r.student_id, r.course_id, c.course_name, r.fees_paid " +
                "FROM registration r JOIN course c ON r.course_id = c.course_id WHERE r.student_id = ?";
        List<RegistrationModel> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new RegistrationModel(
                            rs.getInt("reg_id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getDouble("fees_paid")
                    ));
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }

    public List<String> fetchAllStudentsWithRegistrations(Connection connection) {
        String sql =
                "SELECT s.id, s.name, s.age, b.branch_name AS branch, c.course_name, r.fees_paid " +
                        "FROM student s " +
                        "LEFT JOIN branch b ON s.branch_id = b.branch_id " +
                        "LEFT JOIN registration r ON s.id = r.student_id " +
                        "LEFT JOIN course c ON r.course_id = c.course_id " +
                        "ORDER BY s.id";
        List<String> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String course = rs.getString("course_name");
                String fee = (course == null) ? "-" : String.valueOf(rs.getDouble("fees_paid"));
                out.add(String.format("ID=%d, Name=%s, Age=%d, Branch=%s, Course=%s, Fee=%s",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
                        (rs.getString("branch") == null ? "-" : rs.getString("branch")), (course == null ? "-" : course), fee));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }

    public List<String> highPayingStudents(Connection connection, double minFee) {
        String sql =
                "SELECT s.id, s.name, c.course_name, r.fees_paid " +
                        "FROM student s JOIN registration r ON s.id = r.student_id " +
                        "JOIN course c ON r.course_id = c.course_id " +
                        "WHERE r.fees_paid > ? ORDER BY r.fees_paid DESC";
        List<String> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, minFee);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(String.format("ID=%d, Name=%s, Course=%s, Paid=%.2f",
                            rs.getInt("id"), rs.getString("name"),
                            rs.getString("course_name"), rs.getDouble("fees_paid")));
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }

    public List<String> courseWiseCount(Connection connection) {
        String sql = "SELECT c.course_name, COUNT(*) AS cnt FROM registration r JOIN course c ON r.course_id = c.course_id GROUP BY c.course_name ORDER BY c.course_name";
        List<String> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(rs.getString("course_name") + " -> " + rs.getInt("cnt"));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }

    public List<CourseModel> fetchAllCourses(Connection connection) {
        String sql = "SELECT course_id, course_name FROM course ORDER BY course_name";
        List<CourseModel> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new CourseModel(rs.getInt("course_id"), rs.getString("course_name")));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
        return out;
    }
}
