<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Employee Leave Application System</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css">
</head>
<body>
<div class="page-shell">
	<div class="card">
		<div class="hero">
			<div class="kicker">HR Leave Portal</div>
			<h1>Employee Leave Application System</h1>
			<p>Submit leave requests quickly, validate them instantly, and review your application details in one place.</p>
			<div class="actions">
				<a class="btn btn-primary" href="<%= request.getContextPath() %>/leave">Open Leave Form</a>
				<a class="btn btn-secondary" href="<%= request.getContextPath() %>/leave">Start New Request</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>
