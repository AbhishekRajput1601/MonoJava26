package com.student;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    public void init() {
        System.out.println("LoginServlet initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberUsername = request.getParameter("remember") != null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM students WHERE username = ? AND password = ?")) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", username);

                    updateUsernameCookie(request, response, username, rememberUsername);
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.jsp?error=Invalid username or password");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error during login", e);
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=An error occurred. Please try again.");
        }
    }

    private void updateUsernameCookie(HttpServletRequest request, HttpServletResponse response,
                                      String username, boolean rememberUsername) {
        String cookiePath = request.getContextPath();
        if (cookiePath == null || cookiePath.isEmpty()) {
            cookiePath = "/";
        }

        Cookie existingCookie = findUsernameCookie(request.getCookies());

        if (rememberUsername) {
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setPath(cookiePath);
            usernameCookie.setMaxAge(7 * 24 * 60 * 60);
            usernameCookie.setHttpOnly(true);
            if (request.isSecure()) {
                usernameCookie.setSecure(true);
            }
            response.addCookie(usernameCookie);
        } else if (existingCookie != null) {
            existingCookie.setPath(cookiePath);
            existingCookie.setMaxAge(0);
            existingCookie.setHttpOnly(true);
            response.addCookie(existingCookie);
        }
    }

    private Cookie findUsernameCookie(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    public void destroy() {
        System.out.println("LoginServlet destroyed");
    }
}
