package com.studentcrp.servlet;

import com.studentcrp.dao.RegistrationDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private RegistrationDAO registrationDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        registrationDAO = new RegistrationDAO();
        System.out.println("=== RegistrationServlet Initialized ===");
        System.out.println("Servlet Name: " + this.getServletName());
        System.out.println("Servlet Config: " + this.getServletConfig());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("\n=== Processing Registration Request ===");
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Remote Address: " + request.getRemoteAddr());


        String studentName = request.getParameter("studentName");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");
        String courseName = request.getParameter("courseName");
        String batchTime = request.getParameter("batchTime");

        System.out.println("\n--- Form Data Received ---");
        System.out.println("Student Name: " + studentName);
        System.out.println("Email: " + email);
        System.out.println("Age: " + ageStr);
        System.out.println("Course: " + courseName);
        System.out.println("Batch Time: " + batchTime);

        if (!isValidRequest(studentName, email, ageStr, courseName, batchTime)) {
            System.out.println("Validation failed. Redirecting to register.jsp");
            response.sendRedirect("register.jsp");
            return;
        }


        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format. Redirecting to register.jsp");
            response.sendRedirect("register.jsp");
            return;
        }


        if (age < 18) {
            System.out.println("Age validation failed (Age < 18). Redirecting to register.jsp");
            response.sendRedirect("register.jsp");
            return;
        }


        boolean isRegistered = registrationDAO.saveRegistration(studentName, email, age,
                                                                courseName, batchTime);

        if (isRegistered) {
            System.out.println("\n--- Validation Passed ---");
            System.out.println("Registration successful. Setting request attributes.");

            request.setAttribute("studentName", studentName);
            request.setAttribute("email", email);
            request.setAttribute("age", age);
            request.setAttribute("courseName", courseName);
            request.setAttribute("batchTime", batchTime);

            System.out.println("Forwarding to confirmation.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
            dispatcher.forward(request, response);
        } else {

            System.out.println("Database operation failed. Redirecting to register.jsp");
            response.sendRedirect("register.jsp");
        }
    }



    private boolean isValidRequest(String studentName, String email, String ageStr,
                                  String courseName, String batchTime) {


        if (studentName == null || studentName.trim().isEmpty()) {
            System.out.println("Validation Error: Student Name is empty");
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Validation Error: Email is empty");
            return false;
        }

        if (ageStr == null || ageStr.trim().isEmpty()) {
            System.out.println("Validation Error: Age is empty");
            return false;
        }

        if (courseName == null || courseName.trim().isEmpty()) {
            System.out.println("Validation Error: Course is not selected");
            return false;
        }

        if (batchTime == null || batchTime.trim().isEmpty()) {
            System.out.println("Validation Error: Batch Time is not selected");
            return false;
        }


        if (!isValidEmail(email)) {
            System.out.println("Validation Error: Invalid email format");
            return false;
        }

        return true;
    }


    private boolean isValidEmail(String email) {
        String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(emailPattern);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("=== RegistrationServlet Destroyed ===");
        System.out.println("Servlet cleanup completed");
    }
}


