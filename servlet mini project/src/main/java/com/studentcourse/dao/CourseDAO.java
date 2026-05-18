package com.studentcourse.dao;

import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO {
    private static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());

    public boolean addCourse(Course course) {
        boolean isAdded = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO courses (course_name, duration, fees, trainer_name) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, course.getCourseName());
            pst.setString(2, course.getDuration());
            pst.setDouble(3, course.getFees());
            pst.setString(4, course.getTrainerName());

            int rows = pst.executeUpdate();
            isAdded = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error adding course", e);
        }
        return isAdded;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM courses";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getString("duration"));
                course.setFees(rs.getDouble("fees"));
                course.setTrainerName(rs.getString("trainer_name"));
                courseList.add(course);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching all courses", e);
        }
        return courseList;
    }

    public Course getCourseById(int courseId) {
        Course course = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM courses WHERE course_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getString("duration"));
                course.setFees(rs.getDouble("fees"));
                course.setTrainerName(rs.getString("trainer_name"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching course by id", e);
        }
        return course;
    }

    public boolean updateCourse(Course course) {
        boolean isUpdated = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE courses SET course_name = ?, duration = ?, fees = ?, trainer_name = ? WHERE course_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, course.getCourseName());
            pst.setString(2, course.getDuration());
            pst.setDouble(3, course.getFees());
            pst.setString(4, course.getTrainerName());
            pst.setInt(5, course.getCourseId());

            int rows = pst.executeUpdate();
            isUpdated = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error updating course", e);
        }
        return isUpdated;
    }

    public boolean deleteCourse(int courseId) {
        RegistrationDAO registrationDAO = new RegistrationDAO();
        if (registrationDAO.hasActiveRegistrationForCourse(courseId)) {
            return false;
        }

        boolean isDeleted = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM courses WHERE course_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, courseId);

            int rows = pst.executeUpdate();
            isDeleted = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error deleting course", e);
        }
        return isDeleted;
    }

    public int getTotalCourses() {
        int total = 0;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) as count FROM courses";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt("count");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error counting courses", e);
        }
        return total;
    }
}

