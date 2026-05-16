package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewStudents")
public class ViewStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ViewStudents GET request received");
        
        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        List<Student> studentList = studentDAO.getAllStudents();

        request.setAttribute("studentList", studentList);
        String errorMessage = request.getParameter("error");
        if (errorMessage != null && !errorMessage.trim().isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
        }
        request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("ViewStudentsServlet destroyed");
    }
}

