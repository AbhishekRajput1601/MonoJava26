<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Student - Student Course Registration System</title>
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
            <h1>Update Student Information</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>

            <%
                com.studentcourse.model.Student student =
                    (com.studentcourse.model.Student) request.getAttribute("student");
            %>

            <% if (student != null) { %>
                <form method="POST" action="${pageContext.request.contextPath}/updateStudent" class="data-form">
                    <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">

                    <div class="form-group">
                        <label for="studentName">Student Name:</label>
                        <input type="text" id="studentName" name="studentName" required value="<%= student.getStudentName() %>"
                               pattern="[a-zA-Z\s]+"
                               title="Student name must contain letters only">
                    </div>

                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required value="<%= student.getEmail() %>">
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" id="phone" name="phone" required value="<%= student.getPhone() %>"
                               pattern="[\d\s\-]{10,}"
                               title="Please enter a valid 10-digit phone number">
                    </div>

                    <div class="form-group">
                        <label for="age">Age:</label>
                        <input type="number" id="age" name="age" required value="<%= student.getAge() %>"
                               min="18"
                               title="Age must be 18 or above">
                    </div>

                    <div class="form-group">
                        <label for="city">City:</label>
                        <input type="text" id="city" name="city" required value="<%= student.getCity() %>">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Update Student</button>
                        <a href="${pageContext.request.contextPath}/viewStudents" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            <% } else { %>
                <div class="error-message">Student not found.</div>
            <% } %>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

