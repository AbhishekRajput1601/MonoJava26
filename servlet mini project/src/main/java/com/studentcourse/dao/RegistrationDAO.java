package com.studentcourse.dao;

import com.studentcourse.model.Registration;
import com.studentcourse.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    private static final Logger LOGGER = Logger.getLogger(RegistrationDAO.class.getName());

    public boolean registerStudentToCourse(int studentId, int courseId, LocalDate registrationDate, String status) throws com.studentcourse.exception.DuplicateActiveRegistrationException {
        if (hasActiveRegistration(studentId, courseId) && "Active".equals(status)) {
            return false;
        }

        boolean isRegistered = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO registrations (student_id, course_id, registration_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.setDate(3, Date.valueOf(registrationDate));
            pst.setString(4, status);

            int rows = pst.executeUpdate();
            isRegistered = (rows > 0);
            pst.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.log(Level.WARNING, "Integrity constraint violation when inserting registration", e);
            throw new com.studentcourse.exception.DuplicateActiveRegistrationException("Duplicate active registration", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error while registering student to course", e);
        }
        return isRegistered;
    }

    public boolean hasActiveRegistration(int studentId, int courseId) {
        String query = "SELECT COUNT(*) as count FROM registrations WHERE student_id = ? AND course_id = ? AND status = 'Active'";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next() && rs.getInt("count") > 0;
            rs.close();
            pst.close();
            return exists;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error checking active registration", e);
            return false;
        }
    }

    public boolean hasAnyRegistrationForStudent(int studentId) {
        String query = "SELECT COUNT(*) as count FROM registrations WHERE student_id = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next() && rs.getInt("count") > 0;
            rs.close();
            pst.close();
            return exists;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error checking registrations for student", e);
            return false;
        }
    }

    public boolean hasActiveRegistrationForCourse(int courseId) {
        String query = "SELECT COUNT(*) as count FROM registrations WHERE course_id = ? AND status = 'Active'";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, courseId);
            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next() && rs.getInt("count") > 0;
            rs.close();
            pst.close();
            return exists;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error checking active registrations for course", e);
            return false;
        }
    }

    private boolean hasActiveRegistrationExcludingId(int studentId, int courseId, int registrationId) {
        String query = "SELECT COUNT(*) as count FROM registrations WHERE student_id = ? AND course_id = ? AND status = 'Active' AND registration_id <> ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.setInt(3, registrationId);
            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next() && rs.getInt("count") > 0;
            rs.close();
            pst.close();
            return exists;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error checking active registration excluding id", e);
            return false;
        }
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> registrationList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT r.*, s.student_name, c.course_name FROM registrations r " +
                    "JOIN students s ON r.student_id = s.student_id " +
                    "JOIN courses c ON r.course_id = c.course_id";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Registration registration = new Registration();
                registration.setRegistrationId(rs.getInt("registration_id"));
                registration.setStudentId(rs.getInt("student_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
                registration.setStatus(rs.getString("status"));
                registration.setStudentName(rs.getString("student_name"));
                registration.setCourseName(rs.getString("course_name"));
                registrationList.add(registration);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching all registrations", e);
        }
        return registrationList;
    }

    public Registration getRegistrationById(int registrationId) {
        Registration registration = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT r.*, s.student_name, c.course_name FROM registrations r " +
                    "JOIN students s ON r.student_id = s.student_id " +
                    "JOIN courses c ON r.course_id = c.course_id " +
                    "WHERE r.registration_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, registrationId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                registration = new Registration();
                registration.setRegistrationId(rs.getInt("registration_id"));
                registration.setStudentId(rs.getInt("student_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
                registration.setStatus(rs.getString("status"));
                registration.setStudentName(rs.getString("student_name"));
                registration.setCourseName(rs.getString("course_name"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching registration by id", e);
        }
        return registration;
    }

    public boolean updateRegistrationStatus(int registrationId, String status) {
        Registration existing = getRegistrationById(registrationId);
        if (existing == null) {
            return false;
        }
        if ("Active".equals(status)
                && hasActiveRegistrationExcludingId(existing.getStudentId(), existing.getCourseId(), registrationId)) {
            return false;
        }

        boolean isUpdated = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE registrations SET status = ? WHERE registration_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, status);
            pst.setInt(2, registrationId);

            int rows = pst.executeUpdate();
            isUpdated = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error updating registration status", e);
        }
        return isUpdated;
    }

    public boolean deleteRegistration(int registrationId) {
        boolean isDeleted = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM registrations WHERE registration_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, registrationId);

            int rows = pst.executeUpdate();
            isDeleted = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error deleting registration", e);
        }
        return isDeleted;
    }

    public int getTotalRegistrations() {
        int total = 0;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) as count FROM registrations";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt("count");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error counting registrations", e);
        }
        return total;
    }
}

