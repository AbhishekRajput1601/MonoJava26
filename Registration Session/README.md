# Student Login and Dashboard System

A Java web application that demonstrates login authentication, session-based access control, and optional username remember/forget behavior using Servlets, JSP, and MySQL.

## What This Project Does

- Authenticates users from a `students` table in MySQL.
- Creates a server-side `HttpSession` on successful login.
- Restricts `dashboard.jsp` to logged-in users only.
- Supports "Remember Username" using a cookie named `username`.
- Supports "Forget Username" from both login and dashboard pages.
- Invalidates session on logout.

## Tech Stack

- Java Servlets + JSP (Jakarta APIs)
- MySQL + JDBC
- Maven (`war` packaging)
- Apache Tomcat (Jakarta-compatible)

## Project Structure

```text
src/
└── main/
    ├── java/com/student/
    │   ├── DBConnection.java         # Creates JDBC connection
    │   ├── LoginServlet.java         # Validates login + manages username cookie
    │   ├── LogoutServlet.java        # Invalidates session
    │   └── DeleteCookieServlet.java  # Deletes username cookie
    └── webapp/
        ├── index.jsp                 # Landing page
        ├── login.jsp                 # Login form + error display + remembered username
        ├── dashboard.jsp             # Protected page
        └── WEB-INF/
            └── web.xml               # Servlet mappings + session cookie config
pom.xml                               # Dependencies and build settings
```

## Request Flow (How Each Process Works)

### 1) User opens login page

1. Browser requests `/login.jsp`.
2. `login.jsp` reads request cookies.
3. If cookie `username` exists, it pre-fills the username input and checks "Remember Username".
4. If URL has `?error=...`, the page renders the message safely (HTML-escaped).

### 2) User submits credentials

1. Form submits `POST` to `/login`.
2. `LoginServlet#doPost` reads:
   - `username`
   - `password`
   - `remember` checkbox state
3. Servlet opens DB connection using `DBConnection.getConnection()`.
4. It executes a prepared query:

```sql
SELECT * FROM students WHERE username = ? AND password = ?
```

5. If record exists:
   - Creates/gets session: `request.getSession(true)`
   - Stores `session.setAttribute("user", username)`
   - Updates `username` cookie based on remember checkbox
   - Redirects to `/dashboard.jsp`
6. If record does not exist:
   - Redirects to `/login.jsp?error=Invalid username or password`
7. If DB/driver error occurs:
   - Logs server error
   - Redirects to `/login.jsp?error=An error occurred. Please try again.`

### 3) Remember Username process

Handled in `LoginServlet#updateUsernameCookie`:

- If remember is checked:
  - Sets cookie `username=<value>`
  - Path = app context path
  - Max age = 7 days
  - `HttpOnly = true`
  - `Secure = true` only when request is HTTPS
- If remember is not checked and cookie exists:
  - Same cookie path
  - Max age = `0` to delete cookie

### 4) Dashboard protection process

When browser requests `dashboard.jsp`:

1. JSP checks `session.getAttribute("user")`.
2. If not present, it redirects to `login.jsp`.
3. If present, it shows dashboard content and current username.

### 5) Logout process

When browser requests `/logout`:

1. `LogoutServlet#doGet` gets existing session (`getSession(false)`).
2. If session exists, it calls `session.invalidate()`.
3. Redirects user to `login.jsp`.

### 6) Forget Username process

When browser requests `/deletecookie`:

1. `DeleteCookieServlet` searches cookies for `username`.
2. If found, sets same path and `Max-Age=0` to delete it.
3. Redirects to `/login.jsp`.

## Servlet and URL Mapping

Configured in `src/main/webapp/WEB-INF/web.xml`:

- `/login` -> `LoginServlet`
- `/logout` -> `LogoutServlet`
- `/deletecookie` -> `DeleteCookieServlet`

Session cookie config includes:

- `HttpOnly=true` for session cookie.

## Database Setup

Create database and table:

```sql
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO students (username, password, email)
VALUES ('student', 'student123', 'student@example.com');
```

## Configuration

Update DB credentials in `src/main/java/com/student/DBConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "<your-password>";
private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
```

## Prerequisites

- Java 17+ recommended
- Maven 3.8+
- MySQL 5.7+
- Apache Tomcat 10.1+ (Jakarta namespace)

Note: this project uses `jakarta.servlet` APIs, so Tomcat 9 (javax) is not compatible without migration changes.

## Build and Deploy

Build WAR with Maven:

```bash
mvn clean package
```

Deploy options:

1. Copy `target/Registration_Session.war` to Tomcat `webapps/`.
2. Start/restart Tomcat.
3. Open:
   - `http://localhost:8080/Registration_Session/`
   - or directly `http://localhost:8080/Registration_Session/login.jsp`

## Default Test Credentials

- Username: `student`
- Password: `student123`

## Security Notes (Current vs Production)

Current implementation is good for learning but should be hardened for production:

- Passwords are stored and compared as plain text (should use salted hashing, e.g., BCrypt).
- Error text is passed via query parameter (escaped in JSP, which is good).
- Cookie stores only username, never password.
- Session and username cookies are `HttpOnly`; cookie `Secure` is only set under HTTPS requests.
- Add HTTPS and CSRF protection before production use.

## Troubleshooting

### Login always fails

- Check DB credentials in `DBConnection.java`.
- Confirm row exists in `students` table.
- Confirm app is connected to expected DB/schema.

### `ClassNotFoundException` for MySQL driver

- Run Maven build again and redeploy WAR.
- Confirm dependency `mysql:mysql-connector-java:8.0.33` exists in `pom.xml`.

### HTTP 404 on `/login` or `/logout`

- Confirm application is deployed under context path `Registration_Session`.
- Confirm servlet mappings in `WEB-INF/web.xml`.

### Dashboard redirects to login repeatedly

- Session was not created or already expired.
- Verify login success path actually redirects to `dashboard.jsp`.

## Lifecycle Reference

`LoginServlet` includes lifecycle hooks:

- `init()` called once when servlet is first initialized.
- `doPost()` called per login request.
- `destroy()` called when servlet is taken out of service.
