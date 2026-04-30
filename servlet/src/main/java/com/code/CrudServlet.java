package com.code;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CrudServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/")) {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                updateUser(request, response, id, readFormBody(request));
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid user ID");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("User ID required");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/")) {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                deleteUser(response, id);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid user ID");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("User ID required");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String branch = request.getParameter("branch");
        String marksStr = request.getParameter("marks");

        if (name != null && !name.isEmpty() && ageStr != null && !ageStr.isEmpty() && branch != null && !branch.isEmpty() && marksStr != null && !marksStr.isEmpty()) {
            try {
                int age = Integer.parseInt(ageStr);
                int marks = Integer.parseInt(marksStr);
                UserService.addUser(name, age, branch, marks);
                response.sendRedirect(request.getContextPath() + "/curdoperation.html?success=" + encode("User added successfully"));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + encode("Invalid age or marks"));
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + encode("Invalid input"));
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, int id, Map<String, String> params)
            throws IOException {
        User currentUser = UserService.getUserById(id);
        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("User not found");
            return;
        }

        String name = firstNonBlank(getParam(request, params, "name"), currentUser.getName());
        String ageStr = getParam(request, params, "age");
        String branch = firstNonBlank(getParam(request, params, "branch"), currentUser.getBranch());
        String marksStr = getParam(request, params, "marks");

        int age = currentUser.getAge();
        if (ageStr != null && !ageStr.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageStr.trim());
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid age");
                return;
            }
        }

        int marks = currentUser.getMarks();
        if (marksStr != null && !marksStr.trim().isEmpty()) {
            try {
                marks = Integer.parseInt(marksStr.trim());
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid marks");
                return;
            }
        }

        boolean updated = UserService.updateUser(id, name, age, branch, marks);
        if (updated) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("User updated successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("User not found");
        }
    }

    private void deleteUser(HttpServletResponse response, int id)
            throws IOException {
        boolean deleted = UserService.deleteUser(id);
        if (deleted) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("User deleted successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("User not found");
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

        String json = "{"
                + "\"id\":" + user.getId() + ","
                + "\"name\":\"" + escapeJson(user.getName()) + "\","
                + "\"age\":" + user.getAge() + ","
                + "\"branch\":\"" + escapeJson(user.getBranch()) + "\","
                + "\"marks\":" + user.getMarks()
                + "}";
        response.getWriter().write(json);
    }

    private void sendNextIdAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"nextId\":" + UserService.getNextUserId() + "}");
    }

    private void sendAllUsersAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        java.util.List<User> users = UserService.getAllUsers();

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (i > 0) {
                json.append(",");
            }
            json.append("{")
                    .append("\"id\":").append(user.getId()).append(",")
                    .append("\"name\":\"").append(escapeJson(user.getName())).append("\",")
                    .append("\"age\":").append(user.getAge()).append(",")
                    .append("\"branch\":\"").append(escapeJson(user.getBranch())).append("\",")
                    .append("\"marks\":").append(user.getMarks())
                    .append("}");
        }
        json.append("]");
        response.getWriter().write(json.toString());
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
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

