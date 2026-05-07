package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/updateRegistrationStatus")
public class UpdateRegistrationStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String idParam = request.getParameter("registrationId");
        String status = request.getParameter("status");
        if (idParam == null || idParam.trim().isEmpty()) {
            String encoded = URLEncoder.encode("Invalid registration ID.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }
        if (!("Active".equals(status) || "Completed".equals(status) || "Cancelled".equals(status))) {
            String encoded = URLEncoder.encode("Invalid status selected.", StandardCharsets.UTF_8.name());
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
        Registration registration = registrationDAO.getRegistrationById(registrationId);
        if (registration == null) {
            String encoded = URLEncoder.encode("Registration not found.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        if ("Active".equals(status)
                && registrationDAO.hasActiveRegistration(registration.getStudentId(), registration.getCourseId())
                && !"Active".equals(registration.getStatus())) {
            String encoded = URLEncoder.encode("Cannot set status to Active due to duplicate active registration.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        boolean updated = registrationDAO.updateRegistrationStatus(registrationId, status);
        if (!updated) {
            String encoded = URLEncoder.encode("Registration status could not be updated.", StandardCharsets.UTF_8.name());
            response.sendRedirect("viewRegistrations?error=" + encoded);
            return;
        }

        response.sendRedirect("viewRegistrations");
    }
}

