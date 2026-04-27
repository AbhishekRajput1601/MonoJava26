package com.project.app.dao;

import com.project.app.exceptions.DataAccessException;
import com.project.app.exceptions.DuplicateEntityException;
import com.project.app.model.CourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CourseDataAccessObject {

    public boolean insertCourse(Connection connection, String courseName) {
        try {
            String sql = "INSERT INTO course (course_name) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, courseName);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DuplicateEntityException("Course already exists: " + courseName, ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean isCourseExists(Connection connection, String courseName) {
        try {
            String sql = "SELECT 1 FROM course WHERE course_name = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, courseName);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean updateCourseName(Connection connection, int courseId, String newName) {
        try {
            String sql = "UPDATE course SET course_name = ? WHERE course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setInt(2, courseId);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    public boolean deleteCourseById(Connection connection, int courseId) {
        try {
            String sql = "DELETE FROM course WHERE course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, courseId);
                return ps.executeUpdate() == 1;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new com.project.app.exceptions.ForeignKeyConstraintException("Cannot delete course: it is referenced by registrations.", ex);
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
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

