<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course List - Student Course Registration System</title>
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
        <h1>Course List</h1>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <div class="page-actions">
            <a href="${pageContext.request.contextPath}/addCourse" class="btn btn-primary">➕ Add New Course</a>
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">← Back to Dashboard</a>
        </div>

        <%
            java.util.List<com.studentcourse.model.Course> courseList =
                (java.util.List<com.studentcourse.model.Course>) request.getAttribute("courseList");
        %>

        <% if (courseList != null && !courseList.isEmpty()) { %>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Duration</th>
                            <th>Fees</th>
                            <th>Trainer Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (com.studentcourse.model.Course course : courseList) { %>
                            <tr>
                                <td><%= course.getCourseId() %></td>
                                <td><%= course.getCourseName() %></td>
                                <td><%= course.getDuration() %></td>
                                <td>$<%= String.format("%.2f", course.getFees()) %></td>
                                <td><%= course.getTrainerName() %></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/editCourse?id=<%= course.getCourseId() %>" class="btn btn-small btn-edit">Edit</a>
                                    <a href="${pageContext.request.contextPath}/deleteCourse?id=<%= course.getCourseId() %>" class="btn btn-small btn-delete" onclick="return confirm('Are you sure?')">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } else { %>
            <div class="empty-message">
                <p>No courses found. <a href="${pageContext.request.contextPath}/addCourse">Add one now</a></p>
            </div>
        <% } %>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

