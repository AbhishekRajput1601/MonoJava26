package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewRegistrations")
public class ViewRegistrationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ViewRegistrations GET request received");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        RegistrationDAO registrationDAO = new RegistrationDAO();
        List<Registration> registrationList = registrationDAO.getAllRegistrations();

        request.setAttribute("registrationList", registrationList);
        String errorMessage = request.getParameter("error");
        if (errorMessage != null && !errorMessage.trim().isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
        }
        request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp").forward(request, response);
    }
}

