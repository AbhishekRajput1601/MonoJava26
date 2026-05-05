package com.code;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class CrudServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CrudServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/next-id".equals(pathInfo)) {
            ResponseFormatter.sendNextIdAsJson(response);
            return;
        }
        if ("/all".equals(pathInfo)) {
            ResponseFormatter.sendAllUsersAsJson(response);
            return;
        }
        Integer userId = RequestHandler.parseIdFromPath(pathInfo);

        if (userId != null) {
            User user = UserService.getUserById(userId);
            ResponseFormatter.sendUserAsJson(response, user);
            return;
        }

        if (request.getParameter("edit") != null && request.getParameter("id") != null) {
            response.sendRedirect(request.getContextPath() + "/updateuser.html?id=" + RequestHandler.encode(request.getParameter("id")));
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

        Integer id = RequestHandler.parseIdFromPath(pathInfo);
        if (id != null) {
            updateUser(request, response, id, RequestHandler.readFormBody(request));
        } else {
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing user ID");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        Integer id = RequestHandler.parseIdFromPath(pathInfo);
        if (id != null) {
            deleteUser(response, id);
        } else {
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing user ID");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = RequestHandler.sanitizeString(request.getParameter("name"));
        String ageStr = request.getParameter("age");
        String branch = RequestHandler.sanitizeString(request.getParameter("branch"));
        String marksStr = request.getParameter("marks");

        // Validation
        String error = UserValidator.validateUserInput(name, ageStr, branch, marksStr);
        if (error != null) {
            logger.warning("User creation validation failed: " + error);
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + RequestHandler.encode(error));
            return;
        }

        try {
            int age = Integer.parseInt(ageStr.trim());
            int marks = Integer.parseInt(marksStr.trim());

            UserService.addUser(name, age, branch, marks);
            logger.info("User created successfully: name=" + name);
            response.sendRedirect(request.getContextPath() + "/curdoperation.html?success=" + RequestHandler.encode("User added successfully"));
        } catch (NumberFormatException e) {
            logger.warning("Invalid number format for age or marks");
            response.sendRedirect(request.getContextPath() + "/adduser.html?error=" + RequestHandler.encode("Invalid age or marks"));
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, int id, Map<String, String> params)
            throws IOException {
        User currentUser = UserService.getUserById(id);
        if (currentUser == null) {
            logger.warning("Update failed: User not found with ID: " + id);
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        String name = RequestHandler.firstNonBlank(RequestHandler.sanitizeString(RequestHandler.getParam(request, params, "name")), currentUser.getName());
        String ageStr = RequestHandler.getParam(request, params, "age");
        String branch = RequestHandler.firstNonBlank(RequestHandler.sanitizeString(RequestHandler.getParam(request, params, "branch")), currentUser.getBranch());
        String marksStr = RequestHandler.getParam(request, params, "marks");

        int age = currentUser.getAge();
        if (ageStr != null && !ageStr.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageStr.trim());
            } catch (NumberFormatException e) {
                logger.warning("Update failed: Invalid age value: " + ageStr);
                ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid age");
                return;
            }
        }

        int marks = currentUser.getMarks();
        if (marksStr != null && !marksStr.trim().isEmpty()) {
            try {
                marks = Integer.parseInt(marksStr.trim());
            } catch (NumberFormatException e) {
                logger.warning("Update failed: Invalid marks value: " + marksStr);
                ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid marks");
                return;
            }
        }

        // Validate field lengths
        String fieldError = UserValidator.validateFieldLengths(name, branch);
        if (fieldError != null) {
            logger.warning("Update failed: " + fieldError);
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, fieldError);
            return;
        }

        boolean updated = UserService.updateUser(id, name, age, branch, marks);
        if (updated) {
            logger.info("User updated successfully: ID=" + id);
            ResponseFormatter.sendJsonSuccess(response, HttpServletResponse.SC_OK, "User updated successfully");
        } else {
            logger.warning("Update failed: User not found with ID: " + id);
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    private void deleteUser(HttpServletResponse response, int id)
            throws IOException {
        boolean deleted = UserService.deleteUser(id);
        if (deleted) {
            logger.info("User deleted successfully: ID=" + id);
            ResponseFormatter.sendJsonSuccess(response, HttpServletResponse.SC_OK, "User deleted successfully");
        } else {
            logger.warning("Delete failed: User not found with ID: " + id);
            ResponseFormatter.sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

}

