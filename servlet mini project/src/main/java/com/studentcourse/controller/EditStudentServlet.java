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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        int studentId;
        try {
            studentId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/WEB-INF/views/update-student.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Student not found");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("EditStudentServlet destroyed");
    }
}

