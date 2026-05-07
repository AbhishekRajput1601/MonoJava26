<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List - Student Course Registration System</title>
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
        <h1>Student List</h1>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <div class="page-actions">
            <a href="${pageContext.request.contextPath}/addStudent" class="btn btn-primary">➕ Add New Student</a>
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">← Back to Dashboard</a>
        </div>

        <%
            java.util.List<com.studentcourse.model.Student> studentList =
                (java.util.List<com.studentcourse.model.Student>) request.getAttribute("studentList");
        %>

        <% if (studentList != null && !studentList.isEmpty()) { %>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Age</th>
                            <th>City</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (com.studentcourse.model.Student student : studentList) { %>
                            <tr>
                                <td><%= student.getStudentId() %></td>
                                <td><%= student.getStudentName() %></td>
                                <td><%= student.getEmail() %></td>
                                <td><%= student.getPhone() %></td>
                                <td><%= student.getAge() %></td>
                                <td><%= student.getCity() %></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/editStudent?id=<%= student.getStudentId() %>" class="btn btn-small btn-edit">Edit</a>
                                    <a href="${pageContext.request.contextPath}/deleteStudent?id=<%= student.getStudentId() %>" class="btn btn-small btn-delete" onclick="return confirm('Are you sure?')">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } else { %>
            <div class="empty-message">
                <p>No students found. <a href="${pageContext.request.contextPath}/addStudent">Add one now</a></p>
            </div>
        <% } %>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

