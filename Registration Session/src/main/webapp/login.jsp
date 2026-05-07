<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jakarta.servlet.http.Cookie"%>
<%!
    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>
<%
    String rememberedUsername = "";
    boolean rememberChecked = false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                rememberedUsername = cookie.getValue();
                rememberChecked = true;
                break;
            }
        }
    }
%>
<%
    String errorMessage = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        .login-container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 360px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-top: 0;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.5);
        }
        .remember-row {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 20px;
            color: #555;
            font-size: 14px;
        }
        .remember-row input {
            width: auto;
            margin: 0;
        }
        .button-row {
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }
        button,
        .secondary-btn {
            flex: 1;
            display: inline-block;
            padding: 10px;
            border-radius: 4px;
            font-size: 15px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: transform 0.2s, background 0.2s;
        }
        button {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
        }
        button:hover,
        .secondary-btn:hover {
            transform: translateY(-2px);
        }
        .secondary-btn {
            background: #f0f0f0;
            color: #333;
            border: 1px solid #ddd;
        }
        .info {
            margin-top: 15px;
            padding: 10px;
            background: #f0f0f0;
            border-radius: 4px;
            font-size: 13px;
            color: #666;
            line-height: 1.5;
        }
        .error-alert {
            background-color: #fee;
            border: 1px solid #fcc;
            border-radius: 4px;
            padding: 12px 15px;
            margin-bottom: 20px;
            color: #c33;
            font-size: 14px;
            font-weight: bold;
            display: flex;
            align-items: flex-start;
            gap: 10px;
        }
        .error-alert::before {
            content: "⚠";
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Student Login</h2>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-alert"><%= escapeHtml(errorMessage) %></div>
        <% } %>
        <form action="<%= request.getContextPath() %>/login" method="POST">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<%= escapeHtml(rememberedUsername) %>" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <label class="remember-row" for="remember">
                <input type="checkbox" id="remember" name="remember" <%= rememberChecked ? "checked" : "" %> />
                Remember Username
            </label>
            <div class="button-row">
                <button type="submit">Login</button>
                <a class="secondary-btn" href="<%= request.getContextPath() %>/deletecookie">Forget Username</a>
            </div>
        </form>
        <div class="info">
            Don't have an account? Please contact the administrator to create one.<br>
            The username is the only value saved in a cookie, never your password.
        </div>
    </div>
</body>
</html>
