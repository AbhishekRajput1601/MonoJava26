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

public class StudentServiceLayer {

    private final StudentDataAccessObject studentDAO = new StudentDataAccessObject();
    private final RegistrationDataAccessObject registrationDAO = new RegistrationDataAccessObject();
    private final CourseDataAccessObject courseDAO = new CourseDataAccessObject();
    private final BranchDataAccessObject branchDAO = new BranchDataAccessObject();

    public void addNewStudent(StudentModel student) {
        if (student.getStudentId() <= 0 || student.getStudentName() == null || student.getStudentName().trim().isEmpty()
                || student.getStudentAge() <= 0) {
            System.out.println("Invalid student details.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            if (studentDAO.isStudentExists(connection, student.getStudentId())) {
                System.out.println("Duplicate student ID.");
                return;
            }
            boolean inserted = studentDAO.insertStudent(connection, student);
            System.out.println(inserted ? "Student added successfully." : "Student not added.");
        } catch (Exception e) {
            System.out.println("Failed to add student: " + e.getMessage());
        }
    }



    // New signature: register using courseId (from course table)
    public void registerStudentForCourse(int studentId, int courseId, double feesPaid) {
        if (studentId <= 0 || courseId <= 0 || feesPaid <= 0) {
            System.out.println("Invalid registration input.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            connection.setAutoCommit(false);
            try {
                if (!studentDAO.isStudentExists(connection, studentId)) {
                    throw new IllegalArgumentException("Student not found.");
                }
                if (registrationDAO.isDuplicateRegistration(connection, studentId, courseId)) {
                    throw new IllegalArgumentException("Duplicate course registration.");
                }
                boolean ok = registrationDAO.insertRegistration(connection, studentId, courseId, feesPaid);
                if (!ok) throw new IllegalStateException("Registration insert failed.");
                connection.commit();
                System.out.println("Course registration successful.");
            } catch (Exception ex) {
                connection.rollback();
                System.out.println("Registration failed. Rolled back. Reason: " + ex.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("DB failure during registration: " + e.getMessage());
        }
    }

    public void viewAllStudentsWithRegistrations() {
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            List<String> rows = registrationDAO.fetchAllStudentsWithRegistrations(connection);
            if (rows.isEmpty()) {
                System.out.println("No students found.");
                return;
            }
            rows.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Failed to fetch data: " + e.getMessage());
        }
    }

    public void searchStudentRegistrationById(int studentId) {
        if (studentId <= 0) {
            System.out.println("Invalid student ID.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            Optional<StudentModel> student = studentDAO.findStudentById(connection, studentId);
            if (student.isEmpty()) {
                System.out.println("Student not found.");
                return;
            }

            StudentModel s = student.get();
            System.out.printf("Student: ID=%d, Name=%s, Age=%d, Branch=%s%n",
                    s.getStudentId(), s.getStudentName(), s.getStudentAge(), s.getStudentBranch());

            List<RegistrationModel> regs = registrationDAO.findRegistrationsByStudentId(connection, studentId);
            if (regs.isEmpty()) {
                System.out.println("No course registrations.");
                return;
            }
            for (RegistrationModel r : regs) {
                System.out.printf("Course=%s, Fee=%.2f%n", r.getCourseName(), r.getFeesPaid());
            }
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    public void updateStudentDetails(int studentId, String name, String branch) {
        if (studentId <= 0 || name == null || name.trim().isEmpty() || branch == null || branch.trim().isEmpty()) {
            System.out.println("Invalid input for update.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            int updated = studentDAO.updateStudentDetails(connection, studentId, name, branch);
            System.out.println(updated == 1 ? "Student updated successfully." : "Student not found.");
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void updateCourseFee(int studentId, String courseName, double fee) {
        System.out.println("Deprecated. Use updateCourseFee(studentId, courseId, fee) with courseId from getAllCourses().");
    }

    public void updateCourseFee(int studentId, int courseId, double fee) {
        if (studentId <= 0 || courseId <= 0 || fee <= 0) {
            System.out.println("Invalid input for fee update.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            int updated = registrationDAO.updateCourseFee(connection, studentId, courseId, fee);
            System.out.println(updated == 1 ? "Course fee updated." : "Registration not found.");
        } catch (Exception e) {
            System.out.println("Fee update failed: " + e.getMessage());
        }
    }

    public void cancelCourseRegistration(int studentId, String courseName) {
        System.out.println("Deprecated. Use cancelCourseRegistration(studentId, courseId) with courseId from getAllCourses().");
    }

    public void cancelCourseRegistration(int studentId, int courseId) {
        if (studentId <= 0 || courseId <= 0) {
            System.out.println("Invalid input for cancel registration.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            int deleted = registrationDAO.deleteRegistration(connection, studentId, courseId);
            System.out.println(deleted == 1 ? "Registration cancelled." : "Registration not found.");
        } catch (Exception e) {
            System.out.println("Cancel failed: " + e.getMessage());
        }
    }

    public List<CourseModel> getAllCourses() {
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            return courseDAO.fetchAllCourses(connection);
        } catch (Exception e) {
            System.out.println("Failed to fetch courses: " + e.getMessage());
            return List.of();
        }
    }

    public List<BranchModel> getAllBranches() {
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            return branchDAO.fetchAllBranches(connection);
        } catch (Exception e) {
            System.out.println("Failed to fetch branches: " + e.getMessage());
            return List.of();
        }
    }

    public void addCourse(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            if (courseDAO.isCourseExists(connection, courseName)) {
                System.out.println("Course already exists.");
                return;
            }
            boolean ok = courseDAO.insertCourse(connection, courseName);
            System.out.println(ok ? "Course added." : "Failed to add course.");
        } catch (Exception e) {
            System.out.println("Failed to add course: " + e.getMessage());
        }
    }

    public void updateCourseName(int courseId, String newName) {
        if (courseId <= 0 || newName == null || newName.trim().isEmpty()) {
            System.out.println("Invalid input for course update.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            boolean ok = courseDAO.updateCourseName(connection, courseId, newName);
            System.out.println(ok ? "Course updated." : "Course not found.");
        } catch (Exception e) {
            System.out.println("Failed to update course: " + e.getMessage());
        }
    }

    public void deleteCourse(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid course id.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            boolean ok = courseDAO.deleteCourseById(connection, courseId);
            System.out.println(ok ? "Course deleted." : "Course not found or cannot delete (foreign key constraint). ");
        } catch (Exception e) {
            System.out.println("Failed to delete course: " + e.getMessage());
        }
    }

    public void addBranch(String branchName) {
        if (branchName == null || branchName.trim().isEmpty()) {
            System.out.println("Branch name cannot be empty.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            if (branchDAO.isBranchExists(connection, branchName)) {
                System.out.println("Branch already exists.");
                return;
            }
            boolean ok = branchDAO.insertBranch(connection, branchName);
            System.out.println(ok ? "Branch added." : "Failed to add branch.");
        } catch (Exception e) {
            System.out.println("Failed to add branch: " + e.getMessage());
        }
    }

    public void updateBranchName(int branchId, String newName) {
        if (branchId <= 0 || newName == null || newName.trim().isEmpty()) {
            System.out.println("Invalid input for branch update.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            boolean ok = branchDAO.updateBranchName(connection, branchId, newName);
            System.out.println(ok ? "Branch updated." : "Branch not found.");
        } catch (Exception e) {
            System.out.println("Failed to update branch: " + e.getMessage());
        }
    }

    public void deleteBranch(int branchId) {
        if (branchId <= 0) {
            System.out.println("Invalid branch id.");
            return;
        }
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            boolean ok = branchDAO.deleteBranchById(connection, branchId);
            System.out.println(ok ? "Branch deleted." : "Branch not found or cannot delete (foreign key constraint). ");
        } catch (Exception e) {
            System.out.println("Failed to delete branch: " + e.getMessage());
        }
    }

    // Helper method to check existence from UI layer
    public boolean isStudentExists(int studentId) {
        if (studentId <= 0) return false;
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            return studentDAO.isStudentExists(connection, studentId);
        } catch (Exception e) {
            System.out.println("Failed to verify student existence: " + e.getMessage());
            return false;
        }
    }

    // Helper to return student details for UI layer
    public Optional<StudentModel> getStudentById(int studentId) {
        if (studentId <= 0) return Optional.empty();
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            return studentDAO.findStudentById(connection, studentId);
        } catch (Exception e) {
            System.out.println("Failed to fetch student: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void deleteStudentCompletely(int studentId) {
        if (studentId <= 0) {
            System.out.println("Invalid student ID.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            connection.setAutoCommit(false);
            try {
                if (!studentDAO.isStudentExists(connection, studentId)) {
                    throw new IllegalArgumentException("Student not found.");
                }
                registrationDAO.deleteRegistrationsByStudentId(connection, studentId);
                int deleted = studentDAO.deleteStudentById(connection, studentId);
                if (deleted != 1) throw new IllegalStateException("Student delete failed.");
                connection.commit();
                System.out.println("Student deleted successfully.");
            } catch (Exception ex) {
                connection.rollback();
                System.out.println("Delete failed. Rolled back. Reason: " + ex.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.out.println("DB failure during delete: " + e.getMessage());
        }
    }

    public void generateHighPayingStudentsReport(double minFee) {
        if (minFee < 0) {
            System.out.println("Fee threshold must be >= 0.");
            return;
        }

        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            List<String> rows = registrationDAO.highPayingStudents(connection, minFee);
            if (rows.isEmpty()) {
                System.out.println("No high-paying students found.");
                return;
            }
            rows.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Report failed: " + e.getMessage());
        }
    }

    public void generateCourseWiseCountReport() {
        try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
            List<String> rows = registrationDAO.courseWiseCount(connection);
            if (rows.isEmpty()) {
                System.out.println("No course registrations found.");
                return;
            }
            rows.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Report failed: " + e.getMessage());
        }
    }
}
