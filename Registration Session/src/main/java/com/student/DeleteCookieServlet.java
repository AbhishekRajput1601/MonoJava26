package com.student;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteCookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        clearUsernameCookie(request, response);
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

    private void clearUsernameCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        String cookiePath = request.getContextPath();
        if (cookiePath == null || cookiePath.isEmpty()) {
            cookiePath = "/";
        }

        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                cookie.setPath(cookiePath);
                cookie.setMaxAge(0);
                cookie.setHttpOnly(true);
                if (request.isSecure()) {
                    cookie.setSecure(true);
                }
                response.addCookie(cookie);
                break;
            }
        }
    }
}


