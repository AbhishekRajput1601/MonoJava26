<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration Form</title>
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
<main class="page-wrap">
    <section class="card">
        <header class="header">
            <span class="badge">Registration Form</span>
            <h1>Enroll in your preferred course</h1>
            <p>Complete the form below. Required fields are marked with <span class="required">*</span>.</p>
        </header>

        <div class="note">
            Make sure your email is active. Course updates and joining instructions will be shared there.
        </div>

        <form method="POST" action="register" onsubmit="return validateForm()" novalidate>
            <div class="form-grid">
                <div class="form-group">
                    <label for="studentName">Student Name <span class="required">*</span></label>
                    <input type="text" id="studentName" name="studentName" placeholder="Enter your full name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address <span class="required">*</span></label>
                    <input type="email" id="email" name="email" placeholder="Enter your email address" required>
                </div>

                <div class="form-group">
                    <label for="age">Age <span class="required">*</span></label>
                    <input type="number" id="age" name="age" placeholder="Enter your age" min="1" max="120" required>
                </div>

                <div class="form-group">
                    <label for="courseName">Course Name <span class="required">*</span></label>
                    <select id="courseName" name="courseName" required>
                        <option value="">-- Select a Course --</option>
                        <option value="Java Full Stack">Java Full Stack</option>
                        <option value="Python Full Stack">Python Full Stack</option>
                        <option value="MERN Stack">MERN Stack</option>
                        <option value="Data Analytics">Data Analytics</option>
                    </select>
                </div>

                <div class="form-group full">
                    <label>Preferred Batch Time <span class="required">*</span></label>
                    <div class="radio-group">
                        <label class="radio-item" for="morning">
                            <input type="radio" id="morning" name="batchTime" value="Morning" required>
                            Morning (9 AM - 1 PM)
                        </label>
                        <label class="radio-item" for="afternoon">
                            <input type="radio" id="afternoon" name="batchTime" value="Afternoon" required>
                            Afternoon (2 PM - 6 PM)
                        </label>
                        <label class="radio-item" for="evening">
                            <input type="radio" id="evening" name="batchTime" value="Evening" required>
                            Evening (7 PM - 10 PM)
                        </label>
                    </div>
                </div>
            </div>

            <div class="actions">
                <button type="submit" class="btn btn-primary">Submit Registration</button>
                <button type="reset" class="btn btn-secondary">Clear Form</button>
                <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
            </div>
        </form>
    </section>
</main>

<script>
    function validateForm() {
        const name = document.getElementById("studentName").value.trim();
        const email = document.getElementById("email").value.trim();
        const age = document.getElementById("age").value;
        const course = document.getElementById("courseName").value;
        const batchTime = document.querySelector('input[name="batchTime"]:checked');

        if (name === "") {
            alert("Student Name is required!");
            document.getElementById("studentName").focus();
            return false;
        }

        if (email === "") {
            alert("Email is required!");
            document.getElementById("email").focus();
            return false;
        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            alert("Please enter a valid email address!");
            document.getElementById("email").focus();
            return false;
        }

        if (age === "" || parseInt(age, 10) < 18) {
            alert("You must be at least 18 years old to register!");
            document.getElementById("age").focus();
            return false;
        }

        if (course === "") {
            alert("Please select a course!");
            document.getElementById("courseName").focus();
            return false;
        }

        if (!batchTime) {
            alert("Please select a preferred batch time!");
            return false;
        }

        return true;
    }
</script>
</body>
</html>

