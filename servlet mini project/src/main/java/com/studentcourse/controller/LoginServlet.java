package com.studentcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.studentcourse.dao.AdminDAO;
import com.studentcourse.model.Admin;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        System.out.println("LoginServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Login GET request received");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Login POST request received");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.validateAdmin(username, password);

        if (admin != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInUser", admin.getUsername());
            session.setAttribute("loginTime", System.currentTimeMillis());

            if ("on".equalsIgnoreCase(rememberMe)) {
                Cookie usernameCookie = new Cookie("rememberedUsername", username);
                usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                response.addCookie(usernameCookie);
            } else {
                Cookie deleteCookie = new Cookie("rememberedUsername", "");
                deleteCookie.setMaxAge(0);
                response.addCookie(deleteCookie);
            }

            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginServlet destroyed");
    }
}

