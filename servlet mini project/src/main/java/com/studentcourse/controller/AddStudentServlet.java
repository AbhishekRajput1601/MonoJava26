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

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        System.out.println("AddStudentServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddStudent GET request received");

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        request.getRequestDispatcher("WEB-INF/views/student-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddStudent POST request received");

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
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
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("WEB-INF/views/student-form.jsp").forward(request, response);
            return;
        }

        // Add student
        Student student = new Student();
        student.setStudentName(studentName);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAge(Integer.parseInt(ageStr));
        student.setCity(city);

        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.addStudent(student)) {
            response.sendRedirect("viewStudents");
        } else {
            request.setAttribute("errorMessage", "Failed to add student. Please try again.");
            request.getRequestDispatcher("WEB-INF/views/student-form.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AddStudentServlet destroyed");
    }
}

