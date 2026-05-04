package com.code;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class CrudServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CrudServlet.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_BRANCH_LENGTH = 100;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/next-id".equals(pathInfo)) {
            sendNextIdAsJson(response);
            return;
        }
        if ("/all".equals(pathInfo)) {
            sendAllUsersAsJson(response);
            return;
        }
        Integer userId = parseIdFromPath(pathInfo);

        if (userId != null) {
            sendUserAsJson(response, userId);
            return;
        }

        if (request.getParameter("edit") != null && request.getParameter("id") != null) {
            response.sendRedirect(request.getContextPath() + "/updateuser.html?id=" + encode(request.getParameter("id")));
            return;
        }

        response.sendRedirect(request.getContextPath() + "/curdoperation.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        createUser(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        Integer id = parseIdFromPath(pathInfo);
        if (id != null) {
            updateUser(request, response, id, readFormBody(request));
        } else {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing user ID");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        Integer id = parseIdFromPath(pathInfo);
        if (id != null) {
            deleteUser(response, id);
        } else {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing user ID");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = sanitizeString(request.getParameter("name"));
        String ageStr = request.getParameter("age");
        String branch = sanitizeString(request.getParameter("branch"));
        String marksStr = request.getParameter("marks");

        // Validation
        String error = validateUserInput(name, ageStr, branch, marksStr);
        if (error != null) {
            logger.warning("User creation validation failed: " + error);
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + encode(error));
            return;
        }

        try {
            int age = Integer.parseInt(ageStr.trim());
            int marks = Integer.parseInt(marksStr.trim());

            UserService.addUser(name, age, branch, marks);
            logger.info("User created successfully: name=" + name);
            response.sendRedirect(request.getContextPath() + "/curdoperation.html?success=" + encode("User added successfully"));
        } catch (NumberFormatException e) {
            logger.warning("Invalid number format for age or marks");
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + encode("Invalid age or marks"));
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, int id, Map<String, String> params)
            throws IOException {
        User currentUser = UserService.getUserById(id);
        if (currentUser == null) {
            logger.warning("Update failed: User not found with ID: " + id);
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        String name = firstNonBlank(sanitizeString(getParam(request, params, "name")), currentUser.getName());
        String ageStr = getParam(request, params, "age");
        String branch = firstNonBlank(sanitizeString(getParam(request, params, "branch")), currentUser.getBranch());
        String marksStr = getParam(request, params, "marks");

        int age = currentUser.getAge();
        if (ageStr != null && !ageStr.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageStr.trim());
            } catch (NumberFormatException e) {
                logger.warning("Update failed: Invalid age value: " + ageStr);
                sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid age");
                return;
            }
        }

        int marks = currentUser.getMarks();
        if (marksStr != null && !marksStr.trim().isEmpty()) {
            try {
                marks = Integer.parseInt(marksStr.trim());
            } catch (NumberFormatException e) {
                logger.warning("Update failed: Invalid marks value: " + marksStr);
                sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid marks");
                return;
            }
        }

        // Validate field lengths
        if (name.length() > MAX_NAME_LENGTH || branch.length() > MAX_BRANCH_LENGTH) {
            logger.warning("Update failed: Field length exceeded");
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Field length exceeded");
            return;
        }

        boolean updated = UserService.updateUser(id, name, age, branch, marks);
        if (updated) {
            logger.info("User updated successfully: ID=" + id);
            sendJsonSuccess(response, HttpServletResponse.SC_OK, "User updated successfully");
        } else {
            logger.warning("Update failed: User not found with ID: " + id);
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    private void deleteUser(HttpServletResponse response, int id)
            throws IOException {
        boolean deleted = UserService.deleteUser(id);
        if (deleted) {
            logger.info("User deleted successfully: ID=" + id);
            sendJsonSuccess(response, HttpServletResponse.SC_OK, "User deleted successfully");
        } else {
            logger.warning("Delete failed: User not found with ID: " + id);
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    private Integer parseIdFromPath(String pathInfo) {
        if (pathInfo == null || !pathInfo.startsWith("/")) {
            return null;
        }
        try {
            return Integer.parseInt(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void sendUserAsJson(HttpServletResponse response, int id) throws IOException {
        User user = UserService.getUserById(id);
        response.setContentType("application/json;charset=UTF-8");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\":\"User not found\"}");
            return;
        }

        String json = mapper.writeValueAsString(user);
        response.getWriter().write(json);
    }

    private void sendNextIdAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"nextId\":" + UserService.getNextUserId() + "}");
    }

    private void sendAllUsersAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        java.util.List<User> users = UserService.getAllUsers();
        String json = mapper.writeValueAsString(users);
        response.getWriter().write(json);
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private void sendJsonSuccess(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> result = new HashMap<>();
        result.put("message", message);
        response.getWriter().write(mapper.writeValueAsString(result));
    }

    private void sendJsonError(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        response.getWriter().write(mapper.writeValueAsString(error));
    }

    private String sanitizeString(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    private String validateUserInput(String name, String ageStr, String branch, String marksStr) {
        if (name == null || name.isEmpty()) {
            return "Name is required";
        }
        if (name.length() > MAX_NAME_LENGTH) {
            return "Name exceeds maximum length of " + MAX_NAME_LENGTH;
        }
        if (ageStr == null || ageStr.isEmpty()) {
            return "Age is required";
        }
        if (branch == null || branch.isEmpty()) {
            return "Branch is required";
        }
        if (branch.length() > MAX_BRANCH_LENGTH) {
            return "Branch exceeds maximum length of " + MAX_BRANCH_LENGTH;
        }
        if (marksStr == null || marksStr.isEmpty()) {
            return "Marks is required";
        }
        try {
            int age = Integer.parseInt(ageStr.trim());
            if (age < 0 || age > 120) {
                return "Age must be between 0 and 120";
            }
            int marks = Integer.parseInt(marksStr.trim());
            if (marks < 0 || marks > 100) {
                return "Marks must be between 0 and 100";
            }
        } catch (NumberFormatException e) {
            return "Age and Marks must be valid numbers";
        }
        return null; // No validation errors
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }

    private String firstNonBlank(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }

    private String getParam(HttpServletRequest request, Map<String, String> params, String name) {
        String value = request.getParameter(name);
        if (value != null) {
            return value;
        }
        return params.get(name);
    }

    private Map<String, String> readFormBody(HttpServletRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        StringBuilder body = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        if (body.length() == 0) {
            return params;
        }

        String[] pairs = body.toString().split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            if (idx > -1) {
                String key = java.net.URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
                String value = java.net.URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
                params.put(key, value);
            }
        }
        return params;
    }
}

