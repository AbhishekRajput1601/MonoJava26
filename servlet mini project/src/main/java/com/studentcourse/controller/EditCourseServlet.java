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

@WebServlet("/editCourse")
public class EditCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("EditCourse GET request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        int courseId = Integer.parseInt(request.getParameter("id"));
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourseById(courseId);

        if (course != null) {
            request.setAttribute("course", course);
            request.getRequestDispatcher("WEB-INF/views/update-course.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Course not found");
            request.getRequestDispatcher("WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}

