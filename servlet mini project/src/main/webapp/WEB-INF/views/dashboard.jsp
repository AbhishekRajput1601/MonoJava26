<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Student Course Registration System</title>
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
        <h1>Admin Dashboard</h1>

        <div class="dashboard-stats">
            <div class="stat-card">
                <div class="stat-number"><%= request.getAttribute("totalStudents") %></div>
                <div class="stat-label">Total Students</div>
                <a href="${pageContext.request.contextPath}/viewStudents" class="stat-link">View All</a>
            </div>

            <div class="stat-card">
                <div class="stat-number"><%= request.getAttribute("totalCourses") %></div>
                <div class="stat-label">Total Courses</div>
                <a href="${pageContext.request.contextPath}/viewCourses" class="stat-link">View All</a>
            </div>

            <div class="stat-card">
                <div class="stat-number"><%= request.getAttribute("totalRegistrations") %></div>
                <div class="stat-label">Total Registrations</div>
                <a href="${pageContext.request.contextPath}/viewRegistrations" class="stat-link">View All</a>
            </div>
        </div>

        <div class="dashboard-menu">
            <h2>Quick Actions</h2>
            <div class="menu-grid">
                <div class="menu-section">
                    <h3>Student Management</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/addStudent">➕ Add Student</a></li>
                        <li><a href="${pageContext.request.contextPath}/viewStudents">📋 View Students</a></li>
                    </ul>
                </div>

                <div class="menu-section">
                    <h3>Course Management</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/addCourse">➕ Add Course</a></li>
                        <li><a href="${pageContext.request.contextPath}/viewCourses">📋 View Courses</a></li>
                    </ul>
                </div>

                <div class="menu-section">
                    <h3>Registration Management</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/registrationForm">➕ Register Student to Course</a></li>
                        <li><a href="${pageContext.request.contextPath}/viewRegistrations">📋 View Registrations</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2026 Student Course Registration System. All rights reserved.</p>
    </footer>
</body>
</html>

