package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/deleteRegistration")
public class DeleteRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid registration ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        int registrationId;
        try {
            registrationId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String encoded = URLEncoder.encode("Invalid registration ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        RegistrationDAO registrationDAO = new RegistrationDAO();
        boolean deleted = registrationDAO.deleteRegistration(registrationId);
        if (!deleted) {
            String encoded = URLEncoder.encode("Registration could not be deleted.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        response.sendRedirect("viewRegistrations");
    }
}

