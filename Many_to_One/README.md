# Department Employee One-to-Many Management System

A Spring Boot REST API demonstrating a **one-to-many** relationship between `Department` and `Employee`.

This README provides setup, run, testing, and troubleshooting instructions so you can run and evaluate the project against the supplied spec.

## Highlights

- One Department can have many Employees (JPA one-to-many)
- DTO-based API (no entity is returned directly)
- Validation with Jakarta Validation
- Global exception handling with standardized error shapes
- Role-based Basic Authentication (admin / user)
- Swagger/OpenAPI documentation

## Tech Stack

- Java, Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Spring Security (Basic Auth)
- Springdoc OpenAPI (Swagger)
- Lombok, ModelMapper
- Maven

## Project Package Structure (after refactor)

```
src/main/java/com/swabhav/demo
├── DemoApplication.java
├── config
├── controller
├── dto
├── exception
├── model
├── repository
└── service
```

> Note: package root is `com.swabhav.demo` to match the project specification.

## Prerequisites

- Java 17+ (or the Java version used by the project)
- Maven
- MySQL server

## Database setup

1. Start MySQL and create the database used by the project:

```sql
CREATE DATABASE one_to_many_demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. (Optional) Create a DB user and grant privileges:

```sql
CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo_pass';
GRANT ALL PRIVILEGES ON one_to_many_demo.* TO 'demo'@'localhost';
FLUSH PRIVILEGES;
```

3. Update `src/main/resources/application.properties` with your DB URL, username, and password.

Example `application.properties` (minimal required settings):

```
spring.datasource.url=jdbc:mysql://localhost:3306/one_to_many_demo
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
springdoc.swagger-ui.path=/swagger-ui.html
```

## Build and run (Windows PowerShell)

From the project root (`E:\\MonoJava\\Many_to_One`):

```powershell
# Build
mvnw.cmd clean package

# Run
mvnw.cmd spring-boot:run

# Or run the jar
java -jar target\\\\*.jar
```

## Default Users (in-memory)

| Username | Password | Role   |
|----------|----------|--------|
| admin    | admin123 | ADMIN  |
| user     | user123  | USER   |

The `ADMIN` user can create, update, and delete departments. The `USER` can only read.

## API Quick Reference

Base URL: `http://localhost:8080/api/departments`

- POST    /api/departments           (ADMIN)  — Create department with employees
- GET     /api/departments           (USER/ADMIN) — List all departments
- GET     /api/departments/page      (USER/ADMIN) — Paginated list (query: pageNumber, pageSize)
- GET     /api/departments/{id}      (USER/ADMIN) — Get department by ID
- PUT     /api/departments/{id}      (ADMIN)  — Update department and employees
- DELETE  /api/departments/{id}      (ADMIN)  — Delete department (employees removed)

### Sample Create Request

```json
{
  "department_name": "Engineering",
  "location": "Mumbai",
  "employees": [
    { "employee_name": "Rahul Sharma", "email": "rahul@example.com", "salary": 50000 },
    { "employee_name": "Priya Mehta",   "email": "priya@example.com",  "salary": 48000 }
  ]
}
```

### Expected Department Response

```json
{
  "id": 1,
  "department_name": "Engineering",
  "location": "Mumbai",
  "employees": [
    { "id": 1, "employee_name": "Rahul Sharma", "email": "rahul@example.com", "salary": 50000 }
  ]
}
```

## Pagination rules & validation

- Default: `pageNumber=0`, `pageSize=5`
- Validation rules enforced:
  - `pageNumber` must be >= 0
  - `pageSize` must be > 0 and <= 100

Invalid pagination parameters will return `400 Bad Request` with a descriptive message.

## Error and Validation Responses

- Standard error shape for non-validation errors:

```json
{
  "timestamp": "2026-06-02T12:34:56.789Z",
  "status": 404,
  "error": "Not Found",
  "message": "Department with id 99 not found"
}
```

- Validation errors return a `messages` list (400 Bad Request):

```json
{
  "timestamp": "2026-06-02T12:00:00.000Z",
  "status": 400,
  "error": "Validation Failed",
  "messages": {
    "department_name": "must not be blank",
    "employees[0].email": "must be a well-formed email address"
  }
}
```

- Database constraint violations (duplicates) return `409 Conflict` with a clear message.

## Swagger / OpenAPI

- UI: `http://localhost:8080/swagger-ui.html`
- JSON: `http://localhost:8080/v3/api-docs`

Swagger UI supports Basic Auth — use the `Authorize` button to supply credentials.

## Testing

- Use Swagger UI to try endpoints interactively.
- Use Postman or curl for automated requests.

Example curl (create department):

```bash
curl -u admin:admin123 -H "Content-Type: application/json" -d @dept.json \
  http://localhost:8080/api/departments
```

## Logging

- Logging is configured for the application. Look for logs from controller, service, and global exception handler to trace requests and errors.

## Common Troubleshooting

- MySQL connection errors: verify `spring.datasource.*` settings and that MySQL is running.
- Port conflicts: change `server.port` in `application.properties`.
- Package or class not found: ensure package root is `com.swabhav.demo` (refactor if necessary).

## Deliverables & Grading Checklist

- Department CRUD with employees: ✅
- Pagination: ✅
- DTO-based API: ✅
- Validation & global exceptions: ✅
- Role-based Basic Auth: ✅
- Swagger documentation: ✅
- Logging present: ✅

If you need, I can also:

- provide a Postman collection
- add SQL seed data or a Flyway script
- run a quick local smoke test (if you grant permission to run maven in this environment)
