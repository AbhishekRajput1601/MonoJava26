# Student Course Registration Portal

A comprehensive Java web application for student course registration with Servlet and JSP.

## Project Overview

This is a complete MVC-based web application that demonstrates:
- **Model**: Database operations via JDBC
- **View**: JSP pages for UI
- **Controller**: Servlet for request handling

## Features

- **Home Page** with welcome message and navigation
- **Registration Form** with multiple input fields
- **Form Validation** (client-side and server-side)
- **Database Integration** with MySQL
- **Confirmation Page** with registration details
- **Servlet Lifecycle Methods** implementation (init, doPost, destroy)
- **Modern UI** with CSS styling

## Tech Stack

- **Frontend**: HTML5, CSS3, JavaScript
- **Backend**: Java Servlet, JSP
- **Database**: MySQL
- **Build Tool**: Maven
- **Application Server**: Apache Tomcat

## Project Structure

```
student CR portal/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/studentcrp/
│   │   │       ├── servlet/
│   │   │       │   └── RegistrationServlet.java
│   │   │       ├── dao/
│   │   │       │   └── RegistrationDAO.java
│   │   │       └── util/
│   │   │           └── DatabaseConnection.java
│   │   ├── webapp/
│   │   │   ├── index.jsp
│   │   │   ├── register.jsp
│   │   │   ├── confirmation.jsp
│   │   │   └── WEB-INF/
│   │   │       └── web.xml
│   │   └── resources/
│   └── test/
├── pom.xml
├── database_setup.sql
└── README.md
```

## Installation & Setup

### Prerequisites

1. **Java Development Kit (JDK)**: Java 8 or higher
2. **Apache Tomcat**: Version 8.5 or higher
3. **MySQL Server**: Version 5.7 or higher
4. **Maven**: For building the project

### Step 1: Database Setup

1. Open MySQL command line or MySQL Workbench
2. Execute the SQL script to create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS student_registration;
   USE student_registration;
   
   CREATE TABLE IF NOT EXISTS registrations (
       id INT AUTO_INCREMENT PRIMARY KEY,
       student_name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       age INT NOT NULL,
       course_name VARCHAR(50) NOT NULL,
       batch_time VARCHAR(50) NOT NULL,
       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

### Step 2: Configure Database Connection

1. Open `src/main/java/com/studentcrp/util/DatabaseConnection.java`
2. Update the database credentials:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/student_registration";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "your_password"; // Update with your MySQL password
   ```

### Step 3: Build the Project

```bash
# Navigate to the project directory
cd "E:\MonoJava\student CR portal"

# Build with Maven
mvn clean install

# Package as WAR
mvn package
```

### Step 4: Deploy to Tomcat

1. Copy the generated WAR file from `target/studentCRportal.war`
2. Paste it into Tomcat's `webapps` directory
3. Start Tomcat server

### Step 5: Access the Application

Open your browser and navigate to:
```
http://localhost:8080/studentCRportal/
```

## Application Pages

### 1. Home Page (index.jsp)
- Welcome message
- Course information
- Link to registration form
- Responsive design with gradient background

### 2. Registration Form (register.jsp)
- Student Name (text input)
- Email Address (email input)
- Age (number input, min 18)
- Course Selection (dropdown)
  - Java Full Stack
  - Python Full Stack
  - MERN Stack
  - Data Analytics
- Batch Time (radio buttons)
  - Morning (9 AM - 1 PM)
  - Afternoon (2 PM - 6 PM)
  - Evening (7 PM - 10 PM)
- Client-side and server-side validation
- Submit and Clear buttons

### 3. Confirmation Page (confirmation.jsp)
- Registration success message
- Display of submitted details
- Options to register another student or return home
- Database confirmation

## Servlet Lifecycle

The `RegistrationServlet` demonstrates all key lifecycle methods:

### init()
```java
- Called once when servlet is first loaded
- Used for initialization tasks
- Displays initialization logs
```

### doPost()
```java
- Handles POST requests from the registration form
- Validates form data
- Processes registration and saves to database
- Forwards to confirmation page
- Logs request details
```

### destroy()
```java
- Called when servlet is being removed from service
- Performs cleanup operations
- Logs destruction message
```

## Validation Rules

The application implements both client-side (JavaScript) and server-side (Servlet) validation:

1. **Student Name**: Must not be empty
2. **Email**: Must not be empty and valid format (xxx@xxx.xxx)
3. **Age**: Must be >= 18 years old
4. **Course**: Must be selected
5. **Batch Time**: Must be selected

### Validation Flow

```
Form Submission
    ↓
Client-side Validation (JavaScript)
    ↓
Server-side Validation (RegistrationServlet)
    ↓
Database Insertion (RegistrationDAO)
    ↓
Confirmation Page or Error Redirect
```

## Database Schema

```sql
Table: registrations
┌─────────────────────┬──────────────────────────┐
| Column              | Type                     |
├─────────────────────┼──────────────────────────┤
| id                  | INT AUTO_INCREMENT       |
| student_name        | VARCHAR(100)             |
| email               | VARCHAR(100)             |
| age                 | INT                      |
| course_name         | VARCHAR(50)              |
| batch_time          | VARCHAR(50)              |
| registration_date   | TIMESTAMP DEFAULT NOW()  |
└─────────────────────┴──────────────────────────┘
```

## Dependencies

The project uses the following Maven dependencies:

- **javax.servlet:javax.servlet-api:4.0.1** - Servlet API
- **javax.servlet.jsp:javax.servlet.jsp-api:2.3.1** - JSP API
- **javax.servlet:jstl:1.2** - JSTL tags
- **mysql:mysql-connector-java:8.0.33** - MySQL JDBC Driver

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | / | Home page |
| GET | /register.jsp | Registration form |
| POST | /register | Process registration |
| GET | /confirmation.jsp | Confirmation page |

## Console Logging

The application logs important events to the console:

```
=== RegistrationServlet Initialized ===
Servlet Name: RegistrationServlet
Servlet Config: ...

=== Processing Registration Request ===
Request Method: POST
Request URI: /studentCRportal/register
Remote Address: 127.0.0.1

--- Form Data Received ---
Student Name: John Doe
Email: john@example.com
Age: 22
Course: Java Full Stack
Batch Time: Morning

--- Validation Passed ---
Registration successful. Setting request attributes.
Forwarding to confirmation.jsp

Database connection established successfully!
Student registration saved successfully!
Database connection closed successfully!
```

## Error Handling

- Invalid form data redirects to registration form
- Database connection errors are logged with details
- User-friendly error messages on the UI

## Styling

The application features:
- Modern gradient backgrounds
- Responsive design (mobile-friendly)
- Smooth transitions and hover effects
- Accessibility-friendly form elements
- Clear visual hierarchy

## Troubleshooting

### Database Connection Error
- Ensure MySQL server is running
- Check database credentials in DatabaseConnection.java
- Verify database and table exist

### Servlet Not Found (404)
- Ensure WAR file is deployed correctly
- Check web.xml servlet mapping
- Verify Tomcat is running

### MySQL Driver Not Found
- Run `mvn dependency:resolve` to download dependencies
- Ensure mysql-connector-java is in classpath
- Check pom.xml for MySQL dependency

## Testing

### Test Registration
1. Navigate to http://localhost:8080/studentCRportal/
2. Click "Start Registration"
3. Fill in all form fields:
   - Name: Test User
   - Email: test@example.com
   - Age: 20
   - Course: Java Full Stack
   - Batch: Morning
4. Click "Submit Registration"
5. Verify confirmation page and database entry

## Future Enhancements

- User login and authentication
- Email notifications
- Admin dashboard for viewing registrations
- Student profile management
- Course schedule view
- Payment integration
- Batch management
- Export registrations to PDF/Excel

## Security Considerations

Current implementation includes:
- Server-side validation
- SQL Prepared Statements (preventing SQL injection)
- Input sanitization

For production deployment, consider adding:
- HTTPS/SSL encryption
- SQL Prepared Statements (already implemented)
- CSRF token validation
- Input encoding
- Rate limiting
- User authentication

## Author

Created for Student Course Registration Portal demonstration

## License

This project is open source and available for educational purposes.

## Support

For issues or questions, please refer to the inline code comments and servlet logs.

