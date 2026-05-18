package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        System.out.println("DashboardServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Dashboard GET request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        RegistrationDAO registrationDAO = new RegistrationDAO();

        int totalStudents = studentDAO.getTotalStudents();
        int totalCourses = courseDAO.getTotalCourses();
        int totalRegistrations = registrationDAO.getTotalRegistrations();

        request.setAttribute("totalStudents", totalStudents);
        request.setAttribute("totalCourses", totalCourses);
        request.setAttribute("totalRegistrations", totalRegistrations);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("DashboardServlet destroyed");
    }
}

