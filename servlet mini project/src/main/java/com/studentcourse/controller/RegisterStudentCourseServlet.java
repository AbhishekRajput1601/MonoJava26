package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;
import com.studentcourse.dao.CourseDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/registerStudentCourse")
public class RegisterStudentCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RegisterStudentCourseServlet.class.getName());

    @Override
    public void init() throws ServletException {
        LOGGER.info("RegisterStudentCourseServlet initialized");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("RegisterStudentCourse POST request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String studentIdStr = request.getParameter("studentId");
        String courseIdStr = request.getParameter("courseId");
        String registrationDateStr = request.getParameter("registrationDate");
        String status = request.getParameter("status");

        String errorMessage = "";
        if (studentIdStr == null || studentIdStr.isEmpty()) {
            errorMessage += "Student must be selected. ";
        }
        if (courseIdStr == null || courseIdStr.isEmpty()) {
            errorMessage += "Course must be selected.";
        }
        if (registrationDateStr == null || registrationDateStr.trim().isEmpty()) {
            errorMessage += " Registration date is required.";
        }
        if (!("Active".equals(status) || "Completed".equals(status) || "Cancelled".equals(status))) {
            errorMessage += " Invalid status selected.";
        }

        if (!errorMessage.isEmpty()) {
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();
            request.setAttribute("studentList", studentDAO.getAllStudents());
            request.setAttribute("courseList", courseDAO.getAllCourses());
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("selectedStudentId", studentIdStr);
            request.setAttribute("selectedCourseId", courseIdStr);
            request.setAttribute("selectedRegistrationDate", registrationDateStr);
            request.setAttribute("selectedStatus", status);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
            return;
        }

        int studentId;
        int courseId;
        LocalDate registrationDate;
        try {
            studentId = Integer.parseInt(studentIdStr);
            courseId = Integer.parseInt(courseIdStr);
            registrationDate = LocalDate.parse(registrationDateStr);
        } catch (NumberFormatException | DateTimeParseException e) {
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();
            request.setAttribute("studentList", studentDAO.getAllStudents());
            request.setAttribute("courseList", courseDAO.getAllCourses());
            request.setAttribute("errorMessage", "Invalid student, course, or registration date.");
            request.setAttribute("selectedStudentId", studentIdStr);
            request.setAttribute("selectedCourseId", courseIdStr);
            request.setAttribute("selectedRegistrationDate", registrationDateStr);
            request.setAttribute("selectedStatus", status);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
            return;
        }

        RegistrationDAO registrationDAO = new RegistrationDAO();
        if ("Active".equals(status) && registrationDAO.hasActiveRegistration(studentId, courseId)) {
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();
            request.setAttribute("studentList", studentDAO.getAllStudents());
            request.setAttribute("courseList", courseDAO.getAllCourses());
            request.setAttribute("errorMessage", "This student is already actively registered for the selected course.");
            request.setAttribute("selectedStudentId", studentIdStr);
            request.setAttribute("selectedCourseId", courseIdStr);
            request.setAttribute("selectedRegistrationDate", registrationDateStr);
            request.setAttribute("selectedStatus", status);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
            return;
        }

        try {
            if (registrationDAO.registerStudentToCourse(studentId, courseId, registrationDate, status)) {
                response.sendRedirect("viewRegistrations");
                return;
            }
        } catch (com.studentcourse.exception.DuplicateActiveRegistrationException e) {
            LOGGER.log(Level.WARNING, "Duplicate active registration detected", e);
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();
            request.setAttribute("studentList", studentDAO.getAllStudents());
            request.setAttribute("courseList", courseDAO.getAllCourses());
            request.setAttribute("errorMessage", "This student is already actively registered for the selected course.");
            request.setAttribute("selectedStudentId", studentIdStr);
            request.setAttribute("selectedCourseId", courseIdStr);
            request.setAttribute("selectedRegistrationDate", registrationDateStr);
            request.setAttribute("selectedStatus", status);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        request.setAttribute("studentList", studentDAO.getAllStudents());
        request.setAttribute("courseList", courseDAO.getAllCourses());
        request.setAttribute("errorMessage", "Failed to register student. Please try again.");
        request.setAttribute("selectedStudentId", studentIdStr);
        request.setAttribute("selectedCourseId", courseIdStr);
        request.setAttribute("selectedRegistrationDate", registrationDateStr);
        request.setAttribute("selectedStatus", status);
        request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("RegisterStudentCourseServlet destroyed");
    }
}

