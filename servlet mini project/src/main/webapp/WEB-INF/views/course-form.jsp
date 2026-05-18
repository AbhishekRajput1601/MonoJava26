<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Course - Student Course Registration System</title>
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
            <h1>Add New Course</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>

            <form method="POST" action="${pageContext.request.contextPath}/addCourse" class="data-form">
                <div class="form-group">
                    <label for="courseName">Course Name:</label>
                    <input type="text" id="courseName" name="courseName" required>
                </div>

                <div class="form-group">
                    <label for="duration">Duration:</label>
                    <input type="number" id="duration" name="duration" placeholder="e.g., 3 or 3.5" required
                           min="0.1"
                           step="0.1"
                           title="Duration must be greater than 0">
                </div>

                <div class="form-group">
                    <label for="fees">Fees:</label>
                    <input type="number" id="fees" name="fees" step="0.01" required
                           min="0.01"
                           title="Fees must be greater than 0">
                </div>

                <div class="form-group">
                    <label for="trainerName">Trainer Name:</label>
                    <input type="text" id="trainerName" name="trainerName" required
                           pattern="[a-zA-Z\s]+"
                           title="Trainer name must contain letters only">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Add Course</button>
                    <a href="${pageContext.request.contextPath}/viewCourses" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

