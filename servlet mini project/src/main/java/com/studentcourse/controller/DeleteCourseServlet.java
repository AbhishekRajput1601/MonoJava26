package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/deleteCourse")
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DeleteCourse GET request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid course ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        int courseId;
        try {
            courseId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid course ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        RegistrationDAO registrationDAO = new RegistrationDAO();
        if (registrationDAO.hasActiveRegistrationForCourse(courseId)) {
            String encoded = URLEncoder.encode("Cannot delete course. Active registrations exist for this course.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        CourseDAO courseDAO = new CourseDAO();
        boolean isDeleted = courseDAO.deleteCourse(courseId);
        if (!isDeleted) {
            String encoded = URLEncoder.encode("Course could not be deleted.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewCourses?error=" + encoded);
            return;
        }

        response.sendRedirect("viewCourses");
    }
}

