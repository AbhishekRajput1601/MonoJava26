<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Student Course Registration System</title>
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
        <div class="error-container">
            <h1>❌ Error Occurred</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } else { %>
                <div class="error-message">
                    An unexpected error has occurred. Please try again later.
                </div>
            <% } %>

            <div class="error-actions">
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">← Back to Dashboard</a>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

