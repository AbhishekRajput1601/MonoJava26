package org.example.leave.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RequestErrorUtil {
    private RequestErrorUtil() {
    }

    public static void redirectWithError(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("formError", message);
        response.sendRedirect(request.getContextPath() + "/leave");
    }
}

