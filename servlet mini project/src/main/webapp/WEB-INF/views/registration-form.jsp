<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Student to Course - Student Course Registration System</title>
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
            <h1>Register Student to Course</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                String selectedStudentId = (String) request.getAttribute("selectedStudentId");
                String selectedCourseId = (String) request.getAttribute("selectedCourseId");
                String selectedRegistrationDate = (String) request.getAttribute("selectedRegistrationDate");
                String defaultRegistrationDate = (String) request.getAttribute("defaultRegistrationDate");
                String selectedStatus = (String) request.getAttribute("selectedStatus");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>

            <form method="POST" action="${pageContext.request.contextPath}/registerStudentCourse" class="data-form">
                <div class="form-group">
                    <label for="studentId">Select Student:</label>
                    <select id="studentId" name="studentId" required>
                        <option value="">-- Select a Student --</option>
                        <%
                            java.util.List<com.studentcourse.model.Student> studentList =
                                (java.util.List<com.studentcourse.model.Student>) request.getAttribute("studentList");
                            if (studentList != null) {
                                for (com.studentcourse.model.Student student : studentList) {
                        %>
                            <option value="<%= student.getStudentId() %>" <%= String.valueOf(student.getStudentId()).equals(selectedStudentId) ? "selected" : "" %>>
                                <%= student.getStudentName() %> (ID: <%= student.getStudentId() %>)
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="courseId">Select Course:</label>
                    <select id="courseId" name="courseId" required>
                        <option value="">-- Select a Course --</option>
                        <%
                            java.util.List<com.studentcourse.model.Course> courseList =
                                (java.util.List<com.studentcourse.model.Course>) request.getAttribute("courseList");
                            if (courseList != null) {
                                for (com.studentcourse.model.Course course : courseList) {
                        %>
                            <option value="<%= course.getCourseId() %>" <%= String.valueOf(course.getCourseId()).equals(selectedCourseId) ? "selected" : "" %>>
                                <%= course.getCourseName() %> (ID: <%= course.getCourseId() %>)
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="registrationDate">Registration Date:</label>
                    <input type="date" id="registrationDate" name="registrationDate" required
                           value="<%= selectedRegistrationDate != null && !selectedRegistrationDate.isEmpty() ? selectedRegistrationDate : defaultRegistrationDate %>">
                </div>

                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status" required>
                        <option value="">-- Select Status --</option>
                        <option value="Active" <%= "Active".equals(selectedStatus) ? "selected" : "" %>>Active</option>
                        <option value="Completed" <%= "Completed".equals(selectedStatus) ? "selected" : "" %>>Completed</option>
                        <option value="Cancelled" <%= "Cancelled".equals(selectedStatus) ? "selected" : "" %>>Cancelled</option>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Register Student</button>
                    <a href="${pageContext.request.contextPath}/viewRegistrations" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

