# Employee Leave Application System

Java web application built with JSP, Servlets, HTML, JDBC, and MySQL.

## Features

- Home page with a welcome message and link to the leave form
- Leave application form with validation
- `doGet()` support for direct `/leave` access
- Validation rules for all required fields and leave-day limits
- Approval message logic based on leave days
- Redirect-based error handling with a user-friendly message
- Review page that displays submitted leave details
- JDBC insert into MySQL table `leave_applications`

## Project Structure

- `pom.xml` - Maven build file and dependencies
- `src/main/resources/db.properties` - database configuration
- `src/main/java/org/example/leave/servlet/LeaveServlet.java` - controller
- `src/main/java/org/example/leave/dao/LeaveApplicationDAO.java` - database access
- `src/main/java/org/example/leave/util/ValidationUtil.java` - validation and approval message logic
- `src/main/java/org/example/leave/util/RequestErrorUtil.java` - redirect error helper
- `src/main/java/org/example/leave/util/DBConnection.java` - JDBC connection helper
- `src/main/webapp/index.jsp` - home page
- `src/main/webapp/leave.jsp` - leave form page
- `src/main/webapp/review.jsp` - review page
- `src/main/webapp/WEB-INF/web.xml` - servlet mapping and welcome page config

The app intentionally uses a simple `servlet + dao + util` design and does not use DTO or repository layers.

## Database Setup

Create the database and table before running the application:

```sql
CREATE DATABASE employee_leave_system;

USE employee_leave_system;

CREATE TABLE leave_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    employee_id VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    leave_type VARCHAR(50) NOT NULL,
    leave_days INT NOT NULL,
    reason TEXT NOT NULL,
    approval_message VARCHAR(255),
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Configuration

Edit `src/main/resources/db.properties` for your local MySQL setup:

- `db.url`
- `db.user`
- `db.password`

You can also override these values with environment variables:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

`DBConnection` reads `db.properties` first and then falls back to environment variables if they are present.

## Requirements

- Java 11 or later
- Maven 3.x
- MySQL 8.x or compatible
- Jakarta-compatible servlet container such as Tomcat 10.1+

## Build

Package the application as a WAR:

```powershell
Set-Location "E:\MonoJava\Leave Application System"
mvn clean package
```

The packaged file is created at:

```text
target/leave_application_system.war
```

## Deploy

Deploy the WAR to your Jakarta servlet container and start the server.

For Tomcat 10.1+, copy the WAR into the `webapps` folder and open the application in your browser.

Example URL:

```text
http://localhost:8080/leave_application_system/
```

## Usage Flow

1. Open the home page.
2. Click **Open Leave Form**.
3. Fill in the leave request form.
4. Submit the form.
5. If validation fails, the app redirects back to `leave.jsp` and shows an error.
6. If validation succeeds, the app saves the record in MySQL and forwards to `review.jsp`.

## Validation Rules

- Employee Name must not be empty
- Employee ID must not be empty
- Department must not be empty
- Leave Type must be selected
- Leave Days must be between 1 and 10
- Reason must contain at least 10 characters

## Business Rule

- If leave days are greater than 5, the approval message is:
  - `This leave request requires manager approval`
- Otherwise, the approval message is:
  - `This leave request can be processed normally`

## Troubleshooting

- If the application does not connect to MySQL, verify `db.properties` values and that MySQL is running.
- If you get a 404 for `/leave`, make sure the WAR is deployed and `web.xml` is included in the build.
- If JSP pages do not load, confirm that you are using Tomcat 10.1+ or another Jakarta EE compatible server.
- If database insert fails, check that the `leave_applications` table exists and the column names match exactly.

## MVC Mapping

- Controller: `src/main/java/org/example/leave/servlet/LeaveServlet.java`
- DAO: `src/main/java/org/example/leave/dao/LeaveApplicationDAO.java`
- Utilities: `src/main/java/org/example/leave/util/ValidationUtil.java`, `src/main/java/org/example/leave/util/RequestErrorUtil.java`, `src/main/java/org/example/leave/util/DBConnection.java`
- Views: `src/main/webapp/index.jsp`, `src/main/webapp/leave.jsp`, `src/main/webapp/review.jsp`
- Config: `src/main/webapp/WEB-INF/web.xml`
