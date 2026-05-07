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

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("EditStudent GET request received");

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        int studentId = Integer.parseInt(request.getParameter("id"));
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("WEB-INF/views/update-student.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Student not found");
            request.getRequestDispatcher("WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("EditStudentServlet destroyed");
    }
}

