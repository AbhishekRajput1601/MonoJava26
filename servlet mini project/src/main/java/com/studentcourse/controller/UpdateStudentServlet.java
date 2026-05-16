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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UpdateStudent POST request received");
        
        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null || studentIdParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        int studentId;
        try {
            studentId = Integer.parseInt(studentIdParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid student ID.", StandardCharsets.UTF_8);
            response.sendRedirect("viewStudents?error=" + encoded);
            return;
        }

        String studentName = request.getParameter("studentName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String ageStr = request.getParameter("age");
        String city = request.getParameter("city");

        // Validation
        String errorMessage = "";
        if (studentName == null || studentName.trim().isEmpty()) {
            errorMessage += "Student name is required. ";
        }
        if (email == null || email.trim().isEmpty()) {
            errorMessage += "Email is required. ";
        }
        if (phone == null || phone.trim().isEmpty()) {
            errorMessage += "Phone is required. ";
        }
        if (ageStr == null || ageStr.trim().isEmpty()) {
            errorMessage += "Age is required. ";
        } else {
            try {
                int age = Integer.parseInt(ageStr);
                if (age < 18) {
                    errorMessage += "Age must be 18 or above. ";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Age must be a valid number. ";
            }
        }
        if (city == null || city.trim().isEmpty()) {
            errorMessage += "City is required.";
        }

        if (!errorMessage.isEmpty()) {
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentById(studentId);
            request.setAttribute("student", student);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/views/update-student.jsp").forward(request, response);
            return;
        }

        // Update student
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAge(Integer.parseInt(ageStr));
        student.setCity(city);

        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.updateStudent(student)) {
            response.sendRedirect("viewStudents");
        } else {
            StudentDAO dao = new StudentDAO();
            Student fetchedStudent = dao.getStudentById(studentId);
            request.setAttribute("student", fetchedStudent);
            request.setAttribute("errorMessage", "Failed to update student. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/update-student.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("UpdateStudentServlet destroyed");
    }
}

