package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import java.io.IOException;

@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        System.out.println("AddCourseServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddCourse GET request received");

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        request.getRequestDispatcher("WEB-INF/views/course-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddCourse POST request received");

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String courseName = request.getParameter("courseName");
        String duration = request.getParameter("duration");
        String feesStr = request.getParameter("fees");
        String trainerName = request.getParameter("trainerName");

        // Validation
        String errorMessage = "";
        if (courseName == null || courseName.trim().isEmpty()) {
            errorMessage += "Course name is required. ";
        }
        if (duration == null || duration.trim().isEmpty()) {
            errorMessage += "Duration is required. ";
        }
        if (feesStr == null || feesStr.trim().isEmpty()) {
            errorMessage += "Fees is required. ";
        } else {
            try {
                double fees = Double.parseDouble(feesStr);
                if (fees <= 0) {
                    errorMessage += "Fees must be greater than 0. ";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Fees must be a valid number. ";
            }
        }
        if (trainerName == null || trainerName.trim().isEmpty()) {
            errorMessage += "Trainer name is required.";
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("WEB-INF/views/course-form.jsp").forward(request, response);
            return;
        }

        // Add course
        Course course = new Course();
        course.setCourseName(courseName);
        course.setDuration(duration);
        course.setFees(Double.parseDouble(feesStr));
        course.setTrainerName(trainerName);

        CourseDAO courseDAO = new CourseDAO();
        if (courseDAO.addCourse(course)) {
            response.sendRedirect("viewCourses");
        } else {
            request.setAttribute("errorMessage", "Failed to add course. Please try again.");
            request.getRequestDispatcher("WEB-INF/views/course-form.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AddCourseServlet destroyed");
    }
}

