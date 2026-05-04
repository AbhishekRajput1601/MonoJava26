<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Form</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css">
</head>
<body>
<div class="page-shell">
    <div class="card">
        <div class="card-inner">
            <h2 class="form-title">Leave Application Form</h2>
            <p class="form-subtitle">Fill in your request details and submit it for review.</p>

            <div class="alert alert-error" style="${empty formError ? 'display:none;' : 'display:block;'}">${formError}</div>

            <form class="form-grid" action="<%= request.getContextPath() %>/leave" method="post">
                <div class="form-row">
                    <label for="employeeName">Employee Name</label>
                    <input type="text" id="employeeName" name="employeeName" placeholder="Enter your full name" required>
                </div>

                <div class="form-row">
                    <label for="employeeId">Employee ID</label>
                    <input type="text" id="employeeId" name="employeeId" placeholder="Enter your employee ID" required>
                </div>

                <div class="form-row">
                    <label for="department">Department</label>
                    <input type="text" id="department" name="department" placeholder="Enter your department" required>
                </div>

                <div class="form-row">
                    <label for="leaveType">Leave Type</label>
                    <select id="leaveType" name="leaveType" required>
                        <option value="">Select Leave Type</option>
                        <option value="Sick Leave">Sick Leave</option>
                        <option value="Casual Leave">Casual Leave</option>
                        <option value="Emergency Leave">Emergency Leave</option>
                        <option value="Work From Home">Work From Home</option>
                    </select>
                </div>

                <div class="form-row">
                    <label for="leaveDays">Number of Leave Days</label>
                    <input type="number" id="leaveDays" name="leaveDays" min="1" max="10" placeholder="1 to 10" required>
                </div>

                <div class="form-row">
                    <label for="reason">Reason</label>
                    <textarea id="reason" name="reason" rows="5" placeholder="Explain your leave request" required></textarea>
                </div>

                <div class="form-actions">
                    <button class="btn btn-primary" type="submit">Submit Request</button>
                    <a class="btn btn-secondary" href="<%= request.getContextPath() %>/index.jsp">Back to Home</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
