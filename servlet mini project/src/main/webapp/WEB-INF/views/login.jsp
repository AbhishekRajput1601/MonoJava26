<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login - Student Course Registration System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h1>Admin Login</h1>
            <p class="subtitle">Student Course Registration System</p>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                String rememberedUsername = null;
                if (request.getCookies() != null) {
                    for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                        if ("rememberedUsername".equals(cookie.getName())) {
                            rememberedUsername = cookie.getValue();
                            break;
                        }
                    }
                }
            %>

            <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>

            <form method="POST" action="${pageContext.request.contextPath}/login" class="login-form">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required
                           value="<%= rememberedUsername != null ? rememberedUsername : "" %>">
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-group checkbox">
                    <input type="checkbox" id="rememberMe" name="rememberMe">
                    <label for="rememberMe">Remember Username</label>
                </div>

                <button type="submit" class="btn btn-primary">Login</button>
            </form>

            <p class="default-creds">
                <strong>Default Credentials:</strong><br>
                Username: <code>admin</code><br>
                Password: <code>admin123</code>
            </p>
        </div>
    </div>
</body>
</html>


