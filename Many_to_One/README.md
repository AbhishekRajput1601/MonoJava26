# Department Employee One-to-Many Management System

A Spring Boot REST API demonstrating a **one-to-many** relationship between `Department` and `Employee`.

## Features

- Create a department with multiple employees
- View all departments
- View department by ID
- View departments with pagination
- Update department details and employee list
- Delete a department and its employees
- Prevent duplicate department names
- Prevent duplicate employee emails
- Validate request data with Jakarta Validation
- Global exception handling
- Basic authentication with role-based access
- Swagger/OpenAPI documentation
- Logging with SLF4J + Lombok

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Spring Security
- Swagger / OpenAPI
- Lombok
- ModelMapper
- Maven

## Project Package Structure

```text
src/main/java/org/abhishek/many_to_one
├── config
├── controller
├── dto
├── exception
├── model
├── repository
└── service
```

## Database

- **Database name:** `one_to_many_demo`
- **Tables:** `departments`, `employees`

## Configuration

Update `src/main/resources/application.properties` with your MySQL credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/one_to_many_demo
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
springdoc.swagger-ui.path=/swagger-ui.html
```

## Default Users

| Username | Password | Role |
|----------|----------|------|
| admin    | admin123 | ADMIN |
| user     | user123  | USER  |

## API Security Rules

- `GET /api/departments/**` → `USER` or `ADMIN`
- `POST /api/departments/**` → `ADMIN` only
- `PUT /api/departments/**` → `ADMIN` only
- `DELETE /api/departments/**` → `ADMIN` only

## Swagger

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Swagger supports **Basic Authentication**.

## Main Endpoints

### Department APIs

- `POST /api/departments`
- `GET /api/departments`
- `GET /api/departments/page?pageNumber=0&pageSize=5`
- `GET /api/departments/{id}`
- `PUT /api/departments/{id}`
- `DELETE /api/departments/{id}`

## Request Format

### Create / Update Department

```json
{
  "department_name": "Engineering",
  "location": "Pune",
  "employees": [
    {
      "employee_name": "Rahul Sharma",
      "email": "rahul@example.com",
      "salary": 50000
    },
    {
      "employee_name": "Priya Mehta",
      "email": "priya@example.com",
      "salary": 55000
    }
  ]
}
```

## Response Format

### Department Response

```json
{
  "id": 1,
  "department_name": "Engineering",
  "location": "Pune",
  "employees": [
    {
      "id": 1,
      "employee_name": "Rahul Sharma",
      "email": "rahul@example.com",
      "salary": 50000
    }
  ]
}
```

### Pagination Response

```json
{
  "content": [],
  "pageNumber": 0,
  "pageSize": 5,
  "totalElements": 0,
  "totalPages": 0,
  "lastPage": true
}
```

## Build and Run

### 1. Create the database

```sql
CREATE DATABASE one_to_many_demo;
```

### 2. Build the project

```powershell
mvn clean install
```

### 3. Run the application

```powershell
mvn spring-boot:run
```

## Testing

Test the APIs using:

- Swagger UI
- Postman

## Expected Behavior

- Duplicate department names return **409 Conflict**
- Duplicate employee emails return **409 Conflict**
- Validation errors return **400 Bad Request**
- Missing resources return **404 Not Found**
- Unauthorized access returns **403 Forbidden**

## Notes

- Entities are not exposed directly from controller responses
- Department deletion also removes associated employees
- Logging is included in controller, service, and exception handler

