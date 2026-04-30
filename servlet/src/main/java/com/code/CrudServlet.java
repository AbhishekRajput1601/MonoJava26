package com.code;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CrudServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/next-id".equals(pathInfo)) {
            sendNextIdAsJson(response);
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
                updateUser(request, response, id);
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
        String mobileNo = request.getParameter("mobileNo");
        String departmentName = request.getParameter("departmentName");
        String courseName = request.getParameter("courseName");

        if (name != null && !name.isEmpty() && ageStr != null && !ageStr.isEmpty() && mobileNo != null && !mobileNo.isEmpty() && departmentName != null && !departmentName.isEmpty() && courseName != null && !courseName.isEmpty()) {
            int age = Integer.parseInt(ageStr);
            UserService.addUser(name, age, mobileNo, departmentName, courseName);
            response.sendRedirect(request.getContextPath() + "/curdoperation.html?success=" + encode("User added successfully"));
        } else {
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + encode("Invalid input"));
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException {
        User currentUser = UserService.getUserById(id);
        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("User not found");
            return;
        }

        String name = firstNonBlank(request.getParameter("name"), currentUser.getName());
        String ageStr = request.getParameter("age");
        String mobileNo = firstNonBlank(request.getParameter("mobileNo"), currentUser.getMobileNo());
        String departmentName = firstNonBlank(request.getParameter("departmentName"), currentUser.getDepartmentName());
        String courseName = firstNonBlank(request.getParameter("courseName"), currentUser.getCourseName());

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

        boolean updated = UserService.updateUser(id, name, age, mobileNo, departmentName, courseName);
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
                + "\"mobileNo\":\"" + escapeJson(user.getMobileNo()) + "\","
                + "\"departmentName\":\"" + escapeJson(user.getDepartmentName()) + "\","
                + "\"courseName\":\"" + escapeJson(user.getCourseName()) + "\""
                + "}";
        response.getWriter().write(json);
    }

    private void sendNextIdAsJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"nextId\":" + UserService.getNextUserId() + "}");
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
}

