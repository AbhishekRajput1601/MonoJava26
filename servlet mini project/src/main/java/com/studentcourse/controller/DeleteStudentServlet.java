package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.dao.RegistrationDAO;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DeleteStudent GET request received");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        int studentId;
        try {
            studentId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        RegistrationDAO registrationDAO = new RegistrationDAO();
        if (registrationDAO.hasAnyRegistrationForStudent(studentId)) {
            String encoded = URLEncoder.encode("Cannot delete student. Registrations exist for this student.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        boolean isDeleted = studentDAO.deleteStudent(studentId);
        if (!isDeleted) {
            String encoded = URLEncoder.encode("Student could not be deleted.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        response.sendRedirect("viewStudents");
    }

    @Override
    public void destroy() {
        System.out.println("DeleteStudentServlet destroyed");
    }
}

