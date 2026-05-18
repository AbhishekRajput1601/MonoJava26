package com.studentcourse.dao;

import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public boolean addStudent(Student student) {
        boolean isAdded = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO students (student_name, email, phone, age, city) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, student.getStudentName());
            pst.setString(2, student.getEmail());
            pst.setString(3, student.getPhone());
            pst.setInt(4, student.getAge());
            pst.setString(5, student.getCity());

            int rows = pst.executeUpdate();
            isAdded = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error adding student", e);
        }
        return isAdded;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM students";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setAge(rs.getInt("age"));
                student.setCity(rs.getString("city"));
                studentList.add(student);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching all students", e);
        }
        return studentList;
    }

    public Student getStudentById(int studentId) {
        Student student = null;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setAge(rs.getInt("age"));
                student.setCity(rs.getString("city"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error fetching student by id", e);
        }
        return student;
    }

    public boolean updateStudent(Student student) {
        boolean isUpdated = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE students SET student_name = ?, email = ?, phone = ?, age = ?, city = ? WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, student.getStudentName());
            pst.setString(2, student.getEmail());
            pst.setString(3, student.getPhone());
            pst.setInt(4, student.getAge());
            pst.setString(5, student.getCity());
            pst.setInt(6, student.getStudentId());

            int rows = pst.executeUpdate();
            isUpdated = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error updating student", e);
        }
        return isUpdated;
    }

    public boolean deleteStudent(int studentId) {
        RegistrationDAO registrationDAO = new RegistrationDAO();
        if (registrationDAO.hasAnyRegistrationForStudent(studentId)) {
            return false;
        }

        boolean isDeleted = false;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, studentId);

            int rows = pst.executeUpdate();
            isDeleted = (rows > 0);
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error deleting student", e);
        }
        return isDeleted;
    }

    public int getTotalStudents() {
        int total = 0;
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) as count FROM students";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                total = rs.getInt("count");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error counting students", e);
        }
        return total;
    }
}

