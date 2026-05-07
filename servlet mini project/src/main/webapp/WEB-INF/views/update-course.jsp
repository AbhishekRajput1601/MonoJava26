<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Course - Student Course Registration System</title>
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
        <div class="form-container">
            <h1>Update Course Information</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>

            <%
                com.studentcourse.model.Course course =
                    (com.studentcourse.model.Course) request.getAttribute("course");
            %>

            <% if (course != null) { %>
                <form method="POST" action="${pageContext.request.contextPath}/updateCourse" class="data-form">
                    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">

                    <div class="form-group">
                        <label for="courseName">Course Name:</label>
                        <input type="text" id="courseName" name="courseName" required value="<%= course.getCourseName() %>">
                    </div>

                    <div class="form-group">
                        <label for="duration">Duration:</label>
                        <input type="text" id="duration" name="duration" required value="<%= course.getDuration() %>">
                    </div>

                    <div class="form-group">
                        <label for="fees">Fees:</label>
                        <input type="number" id="fees" name="fees" step="0.01" required value="<%= course.getFees() %>">
                    </div>

                    <div class="form-group">
                        <label for="trainerName">Trainer Name:</label>
                        <input type="text" id="trainerName" name="trainerName" required value="<%= course.getTrainerName() %>">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Update Course</button>
                        <a href="${pageContext.request.contextPath}/viewCourses" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            <% } else { %>
                <div class="error-message">Course not found.</div>
            <% } %>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

