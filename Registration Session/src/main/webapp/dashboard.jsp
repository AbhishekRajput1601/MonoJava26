<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String username = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .dashboard-container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 500px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #667eea;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }
        .header-buttons {
            display: flex;
            gap: 15px;
            align-items: center;
        }
        .header h1 {
            color: #333;
            font-size: 22px;
        }
        .logout-btn {
            background: #ff6b6b;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            transition: background 0.3s;
        }
        .logout-btn:hover {
            background: #ee5a52;
        }
        .forget-btn {
            background: #f0f0f0;
            color: #333;
            padding: 10px 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            transition: background 0.3s;
        }
        .forget-btn:hover {
            background: #e4e4e4;
        }
        .welcome-section {
            background: #f8f9fa;
            padding: 25px;
            border-radius: 6px;
            border-left: 4px solid #667eea;
        }
        .welcome-section p {
            color: #555;
            font-size: 16px;
            margin-bottom: 15px;
            line-height: 1.6;
        }
        .username-display {
            background: white;
            padding: 15px;
            border-radius: 4px;
            margin-top: 15px;
            border: 1px solid #ddd;
        }
        .username-display label {
            display: block;
            color: #666;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .username-display .value {
            color: #667eea;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <h1>Dashboard</h1>
            <div class="header-buttons">
                <a href="<%= request.getContextPath() %>/deletecookie" class="forget-btn">Forget Username</a>
                <a href="logout" class="logout-btn">Logout</a>
            </div>
        </div>
        <div class="welcome-section">
            <p>Welcome to the Student Dashboard! You have successfully logged in.</p>
            <div class="username-display">
                <label>Logged in as:</label>
                <div class="value"><%= username %></div>
            </div>
        </div>
    </div>
</body>
</html>
