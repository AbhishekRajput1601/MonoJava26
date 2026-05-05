package com.code;

import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseFormatter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendJsonSuccess(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> result = new HashMap<>();
        result.put("message", message);
        response.getWriter().write(mapper.writeValueAsString(result));
    }

    public static void sendJsonError(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        response.getWriter().write(mapper.writeValueAsString(error));
    }

    public static void sendUserAsJson(HttpServletResponse response, User user) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\":\"User not found\"}");
            return;
        }

        String json = mapper.writeValueAsString(user);
        response.getWriter().write(json);
    }

    public static void sendNextIdAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"nextId\":" + UserService.getNextUserId() + "}");
    }

    public static void sendAllUsersAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<User> users = UserService.getAllUsers();
        String json = mapper.writeValueAsString(users);
        response.getWriter().write(json);
    }

    public static String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

