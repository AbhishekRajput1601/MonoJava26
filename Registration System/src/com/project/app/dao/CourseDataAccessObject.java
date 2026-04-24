package com.project.app.dao;

import com.project.app.model.CourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDataAccessObject {

    public boolean insertCourse(Connection connection, String courseName) throws SQLException {
        String sql = "INSERT INTO course (course_name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseName);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean isCourseExists(Connection connection, String courseName) throws SQLException {
        String sql = "SELECT 1 FROM course WHERE course_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean updateCourseName(Connection connection, int courseId, String newName) throws SQLException {
        String sql = "UPDATE course SET course_name = ? WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, courseId);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteCourseById(Connection connection, int courseId) throws SQLException {
        String sql = "DELETE FROM course WHERE course_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            return ps.executeUpdate() == 1;
        }
    }

    public List<CourseModel> fetchAllCourses(Connection connection) throws SQLException {
        String sql = "SELECT course_id, course_name FROM course ORDER BY course_name";
        List<CourseModel> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new CourseModel(rs.getInt("course_id"), rs.getString("course_name")));
            }
        }
        return out;
    }
}

