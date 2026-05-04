<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
<main class="page-wrap">
    <section class="card">
        <header class="header">
            <span class="badge">Registration Complete</span>
            <h1>Your seat is reserved successfully</h1>
            <p>Thank you for registering. Please verify your details below.</p>
        </header>

        <div class="note" style="border-left-color:#10b981; background:#ecfdf5; color:#065f46;">
            A confirmation email will be sent shortly with onboarding details and next steps.
        </div>

        <section class="table-like" aria-label="Registration details">
            <div class="row">
                <span class="label">Student Name</span>
                <span class="value"><%= request.getAttribute("studentName") %></span>
            </div>
            <div class="row">
                <span class="label">Email Address</span>
                <span class="value"><%= request.getAttribute("email") %></span>
            </div>
            <div class="row">
                <span class="label">Age</span>
                <span class="value"><%= request.getAttribute("age") %></span>
            </div>
            <div class="row">
                <span class="label">Course Name</span>
                <span class="value"><%= request.getAttribute("courseName") %></span>
            </div>
            <div class="row">
                <span class="label">Preferred Batch Time</span>
                <span class="value"><%= request.getAttribute("batchTime") %></span>
            </div>
        </section>

        <div class="actions">
            <a href="index.jsp" class="btn btn-primary">Back to Home</a>
            <a href="register.jsp" class="btn btn-success">Register Another Student</a>
        </div>

        <p class="footer-text">Your registration has been saved. Keep this page for your records.</p>
    </section>
</main>
</body>
</html>

