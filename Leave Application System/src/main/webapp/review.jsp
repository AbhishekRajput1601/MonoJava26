<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Review</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css">
</head>
<body>
<div class="page-shell">
    <div class="card">
        <div class="card-inner">
            <div class="kicker">Submission Review</div>
            <h2 class="form-title">Leave Application Review</h2>
            <p class="form-subtitle">Please review the submitted leave details below.</p>

            <div class="summary-grid">
                <div class="summary-item">
                    <span class="summary-label">Employee Name</span>
                    <div class="summary-value">${employeeName}</div>
                </div>
                <div class="summary-item">
                    <span class="summary-label">Employee ID</span>
                    <div class="summary-value">${employeeId}</div>
                </div>
                <div class="summary-item">
                    <span class="summary-label">Department</span>
                    <div class="summary-value">${department}</div>
                </div>
                <div class="summary-item">
                    <span class="summary-label">Leave Type</span>
                    <div class="summary-value">${leaveType}</div>
                </div>
                <div class="summary-item">
                    <span class="summary-label">Leave Days</span>
                    <div class="summary-value">${leaveDays}</div>
                </div>
                <div class="summary-item">
                    <span class="summary-label">Reason</span>
                    <div class="summary-value">${reason}</div>
                </div>
            </div>

            <div class="summary-item" style="margin-top: 18px;">
                <span class="summary-label">Approval Message</span>
                <div class="approval-badge">${approvalMessage}</div>
            </div>

            <div class="footer-links">
                <a class="btn btn-primary" href="<%= request.getContextPath() %>/leave">Submit Another Request</a>
                <a class="btn btn-secondary" href="<%= request.getContextPath() %>/index.jsp">Back to Home</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
