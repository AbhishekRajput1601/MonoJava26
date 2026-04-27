package com.project.app.service;

import com.project.app.dao.BranchDataAccessObject;
import com.project.app.dao.CourseDataAccessObject;
import com.project.app.dao.RegistrationDataAccessObject;
import com.project.app.dao.StudentDataAccessObject;
import com.project.app.model.BranchModel;
import com.project.app.model.CourseModel;
import com.project.app.model.RegistrationModel;
import com.project.app.model.StudentModel;
import com.project.app.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import com.project.app.exceptions.DuplicateEntityException;
import com.project.app.exceptions.EntityNotFoundException;
import com.project.app.exceptions.TransactionFailureException;

public class StudentServiceLayer {

    private final StudentDataAccessObject studentDAO = new StudentDataAccessObject();
    private final RegistrationDataAccessObject registrationDAO = new RegistrationDataAccessObject();
    private final CourseDataAccessObject courseDAO = new CourseDataAccessObject();
    private final BranchDataAccessObject branchDAO = new BranchDataAccessObject();

    public void addNewStudent(StudentModel student) {
        if (student.getStudentId() <= 0 || student.getStudentName() == null || student.getStudentName().trim().isEmpty()
                || student.getStudentAge() <= 0) {
            throw new IllegalArgumentException("Invalid student details.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                boolean inserted = studentDAO.insertStudent(conn, student);
                if (!inserted) throw new TransactionFailureException("Student not added due to unknown reason.");
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }



    // New signature: register using courseId (from course table)
    public void registerStudentForCourse(int studentId, int courseId, double feesPaid) {
        if (studentId <= 0 || courseId <= 0 || feesPaid <= 0) {
            throw new IllegalArgumentException("Invalid registration input.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                conn.setAutoCommit(false);
                try {
                    if (!studentDAO.isStudentExists(conn, studentId)) {
                        throw new EntityNotFoundException("Student not found.");
                    }
                    if (registrationDAO.isDuplicateRegistration(conn, studentId, courseId)) {
                        throw new DuplicateEntityException("Duplicate course registration.");
                    }
                    boolean ok = registrationDAO.insertRegistration(conn, studentId, courseId, feesPaid);
                    if (!ok) throw new TransactionFailureException("Registration insert failed.");
                    conn.commit();
                } catch (Exception ex) {
                    try { conn.rollback(); } catch (Exception ignore) {}
                    throw ex;
                } finally {
                    try { conn.setAutoCommit(true); } catch (Exception ignore) {}
                }
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<String> viewAllStudentsWithRegistrations() {
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return registrationDAO.fetchAllStudentsWithRegistrations(conn);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<String> searchStudentRegistrationById(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                Optional<StudentModel> student = studentDAO.findStudentById(conn, studentId);
                if (student.isEmpty()) {
                    throw new EntityNotFoundException("Student not found.");
                }

                List<String> out = new java.util.ArrayList<>();
                StudentModel s = student.get();
                out.add(String.format("Student: ID=%d, Name=%s, Age=%d, Branch=%s",
                        s.getStudentId(), s.getStudentName(), s.getStudentAge(), s.getStudentBranch()));

                List<RegistrationModel> regs = registrationDAO.findRegistrationsByStudentId(conn, studentId);
                if (regs.isEmpty()) {
                    out.add("No course registrations.");
                    return out;
                }
                for (RegistrationModel r : regs) {
                    out.add(String.format("Course=%s, Fee=%.2f", r.getCourseName(), r.getFeesPaid()));
                }
                return out;
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean updateStudentDetails(int studentId, String name, String branch) {
        if (studentId <= 0 || name == null || name.trim().isEmpty() || branch == null || branch.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input for update.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                int updated = studentDAO.updateStudentDetails(conn, studentId, name, branch);
                return updated == 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean updateCourseFee(int studentId, int courseId, double fee) {
        if (studentId <= 0 || courseId <= 0 || fee <= 0) {
            throw new IllegalArgumentException("Invalid input for fee update.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                int updated = registrationDAO.updateCourseFee(conn, studentId, courseId, fee);
                return updated == 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean cancelCourseRegistration(int studentId, int courseId) {
        if (studentId <= 0 || courseId <= 0) {
            throw new IllegalArgumentException("Invalid input for cancel registration.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                int deleted = registrationDAO.deleteRegistration(conn, studentId, courseId);
                return deleted == 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<CourseModel> getAllCourses() {
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return courseDAO.fetchAllCourses(conn);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<BranchModel> getAllBranches() {
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return branchDAO.fetchAllBranches(conn);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public void addCourse(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                boolean ok = courseDAO.insertCourse(conn, courseName);
                if (!ok) throw new TransactionFailureException("Failed to add course.");
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean updateCourseName(int courseId, String newName) {
        if (courseId <= 0 || newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input for course update.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return courseDAO.updateCourseName(conn, courseId, newName);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean deleteCourse(int courseId) {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Invalid course id.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return courseDAO.deleteCourseById(conn, courseId);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public void deleteCourseCompletely(int courseId) {
        if (courseId <= 0) throw new IllegalArgumentException("Invalid course id.");
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                conn.setAutoCommit(false);
                try {
                    // delete registrations referencing the course first
                    registrationDAO.deleteRegistrationsByCourseId(conn, courseId);
                    boolean ok = courseDAO.deleteCourseById(conn, courseId);
                    if (!ok) throw new TransactionFailureException("Course delete failed.");
                    conn.commit();
                } catch (Exception ex) {
                    try { conn.rollback(); } catch (Exception ignore) {}
                    throw ex;
                } finally {
                    try { conn.setAutoCommit(true); } catch (Exception ignore) {}
                }
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public void addBranch(String branchName) {
        if (branchName == null || branchName.trim().isEmpty()) {
            throw new IllegalArgumentException("Branch name cannot be empty.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                boolean ok = branchDAO.insertBranch(conn, branchName);
                if (!ok) throw new TransactionFailureException("Failed to add branch.");
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean updateBranchName(int branchId, String newName) {
        if (branchId <= 0 || newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input for branch update.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return branchDAO.updateBranchName(conn, branchId, newName);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public boolean deleteBranch(int branchId) {
        if (branchId <= 0) {
            throw new IllegalArgumentException("Invalid branch id.");
        }
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return branchDAO.deleteBranchById(conn, branchId);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    // Helper method to check existence from UI layer
    public boolean isStudentExists(int studentId) {
        if (studentId <= 0) return false;
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return studentDAO.isStudentExists(conn, studentId);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    // Helper to return student details for UI layer
    public Optional<StudentModel> getStudentById(int studentId) {
        if (studentId <= 0) return Optional.empty();
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return studentDAO.findStudentById(conn, studentId);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public void deleteStudentCompletely(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                conn.setAutoCommit(false);
                try {
                    if (!studentDAO.isStudentExists(conn, studentId)) {
                        throw new EntityNotFoundException("Student not found.");
                    }
                    registrationDAO.deleteRegistrationsByStudentId(conn, studentId);
                    int deleted = studentDAO.deleteStudentById(conn, studentId);
                    if (deleted != 1) throw new TransactionFailureException("Student delete failed.");
                    conn.commit();
                } catch (Exception ex) {
                    try { conn.rollback(); } catch (Exception ignore) {}
                    throw ex;
                } finally {
                    try { conn.setAutoCommit(true); } catch (Exception ignore) {}
                }
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<String> generateHighPayingStudentsReport(double minFee) {
        if (minFee < 0) {
            throw new IllegalArgumentException("Fee threshold must be >= 0.");
        }

        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return registrationDAO.highPayingStudents(conn, minFee);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }

    public List<String> generateCourseWiseCountReport() {
        try {
            Connection connection = DatabaseConnectionUtil.getDatabaseConnection();
            try (Connection conn = connection) {
                return registrationDAO.courseWiseCount(conn);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.project.app.exceptions.DataAccessException(ex);
        }
    }
}
