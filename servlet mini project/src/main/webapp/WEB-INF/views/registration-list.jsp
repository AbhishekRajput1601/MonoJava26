<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration List - Student Course Registration System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="navbar-brand">Student Course Registration System</div>
        <div class="navbar-menu">
            <span class="welcome-text">Welcome, <%= session.getAttribute("loggedInUser") %></span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout">Logout</a>
        </div>
    </div>

    <div class="container">
        <h1>Student Course Registrations</h1>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <div class="page-actions">
            <a href="${pageContext.request.contextPath}/registrationForm" class="btn btn-primary">➕ Register Student to Course</a>
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">← Back to Dashboard</a>
        </div>

        <%
            java.util.List<com.studentcourse.model.Registration> registrationList =
                (java.util.List<com.studentcourse.model.Registration>) request.getAttribute("registrationList");
        %>

        <% if (registrationList != null && !registrationList.isEmpty()) { %>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Registration ID</th>
                            <th>Student Name</th>
                            <th>Course Name</th>
                            <th>Registration Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (com.studentcourse.model.Registration registration : registrationList) { %>
                            <tr>
                                <td><%= registration.getRegistrationId() %></td>
                                <td><%= registration.getStudentName() %></td>
                                <td><%= registration.getCourseName() %></td>
                                <td><%= registration.getRegistrationDate() %></td>
                                <td>
                                    <span class="badge <%= "Active".equals(registration.getStatus()) ? "badge-success" : "badge-warning" %>">
                                        <%= registration.getStatus() %>
                                    </span>
                                </td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/updateRegistrationStatus" style="display:inline-block; margin-right: 8px;">
                                        <input type="hidden" name="registrationId" value="<%= registration.getRegistrationId() %>">
                                        <select name="status" required>
                                            <option value="Active" <%= "Active".equals(registration.getStatus()) ? "selected" : "" %>>Active</option>
                                            <option value="Completed" <%= "Completed".equals(registration.getStatus()) ? "selected" : "" %>>Completed</option>
                                            <option value="Cancelled" <%= "Cancelled".equals(registration.getStatus()) ? "selected" : "" %>>Cancelled</option>
                                        </select>
                                        <button type="submit" class="btn btn-small btn-edit">Update</button>
                                    </form>
                                    <a href="${pageContext.request.contextPath}/deleteRegistration?id=<%= registration.getRegistrationId() %>" class="btn btn-small btn-delete" onclick="return confirm('Delete this registration?')">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } else { %>
            <div class="empty-message">
                <p>No registrations found. <a href="${pageContext.request.contextPath}/registrationForm">Create one now</a></p>
            </div>
        <% } %>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

