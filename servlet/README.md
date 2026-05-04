# User Management CRUD Application - Architecture & Flow Documentation

## Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Technology Stack](#technology-stack)
4. [CRUD Operations Flow](#crud-operations-flow)
5. [API Endpoints](#api-endpoints)
6. [Database Schema](#database-schema)
7. [Layer Descriptions](#layer-descriptions)

---

## Project Overview

This is a Java Servlet-based REST API for managing user records. It implements a complete CRUD (Create, Read, Update, Delete) system for user management with a MySQL database backend.

**Key Features:**
- Create new users
- Read user information (single or all users)
- Update existing user details
- Delete user records
- JSON API responses
- Form-based web interface

---

## Architecture

The application follows a **3-Tier Architecture Pattern**:

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                        │
│  (HTML Forms & JSP Pages - Web Interface for End Users)     │
├─────────────────────────────────────────────────────────────┤
│                    SERVLET LAYER (Controller)                │
│  (CrudServlet - Handles HTTP Requests/Responses)            │
├─────────────────────────────────────────────────────────────┤
│                   SERVICE LAYER (Business Logic)             │
│  (UserService - CRUD Operations & Business Rules)           │
├─────────────────────────────────────────────────────────────┤
│                    DATA ACCESS LAYER (DAO)                   │
│  (DatabaseConnection - Database Connectivity)               │
├─────────────────────────────────────────────────────────────┤
│                    DATABASE LAYER                            │
│  (MySQL - Persistent Data Storage)                          │
└─────────────────────────────────────────────────────────────┘
```

### Layer Components

```
Web Client (Browser)
        ↓
HTML Forms (adduser.html, updateuser.html, etc.)
        ↓
CrudServlet (HTTP Request Handler)
        ↓
UserService (Business Logic & DB Operations)
        ↓
DatabaseConnection (Connection Management)
        ↓
MySQL Database (Data Persistence)
```

---

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Server** | Jakarta Servlet API | 6.1.0 |
| **Database** | MySQL | 8.0+ |
| **JDBC Driver** | MySQL Connector/J | 8.0.33 |
| **Build Tool** | Apache Maven | 3.x |
| **Programming Language** | Java | 11+ |
| **Protocol** | HTTP/REST | 1.1 |

---

## CRUD Operations Flow

### 1. CREATE Operation (POST)

**Purpose:** Add a new user to the database

**Flow Diagram:**
```
Client Browser
    ↓
Submit Form (POST) → /servlet/api/user
    ↓
CrudServlet.doPost()
    ↓
createUser() method
    ├─ Extract parameters: name, age, branch, marks
    ├─ Validate input (non-empty & correct type)
    └─ Call UserService.addUser()
         ├─ Fetch next available ID via getNextUserId()
         │  └─ Execute: SELECT MAX(id) FROM user
         ├─ Insert new record
         │  └─ Execute: INSERT INTO user (id, name, age, branch, marks) VALUES (?, ?, ?, ?, ?)
         └─ Return to client
    ↓
Redirect to /curdoperation.html with success/error message
```

**Request Example:**
```
POST /servlet/api/user
Content-Type: application/x-www-form-urlencoded

name=John Doe&age=20&branch=CSE&marks=85
```

**Response:**
```
HTTP 302 Found
Location: /servlet/curdoperation.html?success=User added successfully
```

**Error Handling:**
- Invalid age or marks (non-integer) → Error message displayed
- Empty fields → Error message displayed

**Database State Change:**
```
BEFORE:  user table has entries with id 1, 2, 3
AFTER:   New entry added with id 4, name=John Doe, age=20, branch=CSE, marks=85
```

---

### 2. READ Operation (GET)

**Purpose:** Retrieve user information (single user or all users)

**Flow Diagram:**
```
Client Browser / API Client
    ↓
GET Request → /servlet/api/user/{id}  OR  /servlet/api/user/all
    ↓
CrudServlet.doGet()
    ├─ Check pathInfo
    ├─ If "/all":
    │  └─ Call sendAllUsersAsJson()
    │     ├─ Invoke UserService.getAllUsers()
    │     │  └─ Execute: SELECT * FROM user
    │     └─ Convert results to JSON array
    │
    └─ If "/{id}":
       └─ Call sendUserAsJson()
          ├─ Invoke UserService.getUserById(id)
          │  └─ Execute: SELECT * FROM user WHERE id = ?
          └─ Convert result to JSON object
    ↓
Return JSON Response with HTTP 200 or 404
```

**Request Examples:**

**Get All Users:**
```
GET /servlet/api/user/all
Accept: application/json
```

**Get Single User:**
```
GET /servlet/api/user/1
Accept: application/json
```

**Response Examples:**

**All Users Response:**
```json
[
  {
    "id": 1,
    "name": "Alice",
    "age": 20,
    "branch": "CSE",
    "marks": 85
  },
  {
    "id": 2,
    "name": "Bob",
    "age": 21,
    "branch": "ECE",
    "marks": 78
  }
]
```

**Single User Response:**
```json
{
  "id": 1,
  "name": "Alice",
  "age": 20,
  "branch": "CSE",
  "marks": 85
}
```

**Not Found Response:**
```json
{
  "message": "User not found"
}
```

**Special Endpoint:**

**Get Next ID:**
```
GET /servlet/api/user/next-id
```

**Response:**
```json
{
  "nextId": 4
}
```

---

### 3. UPDATE Operation (PUT)

**Purpose:** Modify existing user information

**Flow Diagram:**
```
Client Browser / API Client
    ↓
PUT Request → /servlet/api/user/{id}
    ├─ Body: Form-encoded or JSON
    ↓
CrudServlet.doPut()
    ↓
Extract pathInfo and parse user ID
    ↓
readFormBody() - Parse request body parameters
    ↓
updateUser() method
    ├─ Fetch current user via UserService.getUserById(id)
    ├─ If user not found:
    │  └─ Return HTTP 404 "User not found"
    │
    └─ If user exists:
       ├─ For each parameter (name, age, branch, marks):
       │  └─ If provided: use new value
       │  └─ If not provided: use existing value
       ├─ Validate numeric fields (age, marks)
       ├─ Call UserService.updateUser(id, name, age, branch, marks)
       │  └─ Execute: UPDATE user SET name=?, age=?, branch=?, marks=? WHERE id=?
       └─ Return success/failure status
    ↓
Return HTTP 200 or 404 with message
```

**Request Example:**
```
PUT /servlet/api/user/1
Content-Type: application/x-www-form-urlencoded

name=Alice Updated&age=21&marks=90
```

**Response:**
```
HTTP 200 OK
User updated successfully
```

**Error Responses:**
```
HTTP 404 Not Found
User not found

HTTP 400 Bad Request
Invalid user ID

HTTP 400 Bad Request
Invalid age
```

**Database State Change:**
```
BEFORE:  id=1, name=Alice, age=20, marks=85
AFTER:   id=1, name=Alice Updated, age=21, marks=90
```

**Key Feature:** Partial updates - Only specified fields are updated; others retain their values.

---

### 4. DELETE Operation (DELETE)

**Purpose:** Remove a user record from the database

**Flow Diagram:**
```
Client Browser / API Client
    ↓
DELETE Request → /servlet/api/user/{id}
    ↓
CrudServlet.doDelete()
    ↓
Extract pathInfo and parse user ID
    ↓
deleteUser() method
    ├─ Call UserService.deleteUser(id)
    │  ├─ Execute: DELETE FROM user WHERE id = ?
    │  └─ Check rows affected count
    │
    ├─ If rowsDeleted > 0:
    │  └─ Return true (success)
    │
    └─ If rowsDeleted = 0:
       └─ Return false (user not found)
    ↓
Return HTTP 200 or 404 with message
```

**Request Example:**
```
DELETE /servlet/api/user/1
```

**Response Success:**
```
HTTP 200 OK
User deleted successfully
```

**Response Failure:**
```
HTTP 404 Not Found
User not found
```

**Database State Change:**
```
BEFORE:  user table contains: id=1, 2, 3, 4, 5
AFTER:   user table contains: id=2, 3, 4, 5
         (id=1 record completely removed)
```

---

## API Endpoints

| Method | Endpoint | Purpose | Response Type |
|--------|----------|---------|---------------|
| GET | `/servlet/api/user/{id}` | Get user by ID | JSON |
| GET | `/servlet/api/user/all` | Get all users | JSON Array |
| GET | `/servlet/api/user/next-id` | Get next available ID | JSON |
| POST | `/servlet/api/user` | Create new user | Redirect |
| PUT | `/servlet/api/user/{id}` | Update user | Plain Text |
| DELETE | `/servlet/api/user/{id}` | Delete user | Plain Text |
| GET | `/servlet/api/user?edit=true&id={id}` | Redirect to edit form | HTML Redirect |

---

## Database Schema

### User Table Structure

```sql
CREATE TABLE user (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    branch VARCHAR(100) NOT NULL,
    marks INT NOT NULL
);
```

### Sample Data

```sql
INSERT INTO user (id, name, age, branch, marks) VALUES
(1, 'Alice', 20, 'CSE', 85),
(2, 'Bob', 21, 'ECE', 78),
(3, 'Charlie', 19, 'ME', 92),
(4, 'Diana', 20, 'CSE', 88);
```

---

## Layer Descriptions

### 1. Presentation Layer (Web UI)

**Files:**
- `adduser.html` - Form to add new users
- `updateuser.html` - Form to update user details
- `deleteuser.html` - Confirmation page for deletion
- `userview.html` - Display user information
- `alluser.html` - Display all users
- `curdoperation.html` - Main CRUD operations interface
- `index.jsp` - Home page

**Responsibilities:**
- Capture user input through HTML forms
- Display data to end users
- Provide navigation between operations
- Show success/error messages

---

### 2. Servlet Layer (Controller)

**File:** `CrudServlet.java`

**Responsibilities:**
- Handle HTTP requests (GET, POST, PUT, DELETE)
- Route requests to appropriate methods
- Validate input parameters
- Manage response types (JSON, HTML redirect)
- Error handling and status codes

**Key Methods:**

| Method | HTTP | Purpose |
|--------|------|---------|
| `doGet()` | GET | Handle read operations & redirects |
| `doPost()` | POST | Handle user creation |
| `doPut()` | PUT | Handle user updates |
| `doDelete()` | DELETE | Handle user deletion |

**Request Processing Flow:**
```
HTTP Request → CrudServlet → Parse Request → Validate → Call Service → Send Response
```

---

### 3. Service Layer (Business Logic)

**File:** `UserService.java`

**Responsibilities:**
- Execute CRUD operations
- Manage business logic
- Database query execution
- Data validation

**Key Methods:**

| Method | Returns | Purpose |
|--------|---------|---------|
| `addUser(name, age, branch, marks)` | void | Insert new user |
| `getUserById(id)` | User | Fetch specific user |
| `getAllUsers()` | List<User> | Fetch all users |
| `updateUser(id, ...)` | boolean | Update user record |
| `deleteUser(id)` | boolean | Delete user record |
| `getNextUserId()` | int | Get next available ID |

**Database Operations:**
- Uses PreparedStatement for SQL injection prevention
- Try-with-resources for proper connection management
- Exception handling with stack trace logging

---

### 4. Data Access Layer

**File:** `DatabaseConnection.java`

**Responsibilities:**
- Manage database connections
- Load JDBC driver
- Provide connection objects to services
- Connection pooling (basic implementation)

**Configuration:**
```
Database URL: jdbc:mysql://localhost:3306/school
Driver Class: com.mysql.cj.jdbc.Driver
User: root
Password: Abhishek@1137
```

---

### 5. Model Layer

**File:** `User.java`

**Responsibilities:**
- Represent user data structure
- Store user attributes (id, name, age, branch, marks)
- Provide getters/setters
- toString() for debugging

**Attributes:**
```java
private int id;           // User unique identifier
private String name;      // User's full name
private int age;         // User's age
private String branch;   // Academic branch (CSE, ECE, ME, etc.)
private int marks;       // User's marks/score
```

---

## Request/Response Cycle Example

### Complete Flow: Creating a User

```
1. User fills form in adduser.html
   ↓
2. Form submits POST request to CrudServlet
   POST /servlet/api/user
   Data: name=John&age=20&branch=CSE&marks=85
   ↓
3. CrudServlet.doPost() receives request
   ↓
4. createUser() extracts parameters
   ↓
5. Parameter validation:
   - name != null && !empty
   - age is valid integer
   - branch != null && !empty
   - marks is valid integer
   ↓
6. UserService.addUser() called
   ↓
7. Service generates next ID:
   - Query: SELECT MAX(id) FROM user
   - Result: maxId = 3, nextId = 4
   ↓
8. Insert query executed:
   INSERT INTO user (id, name, age, branch, marks)
   VALUES (4, 'John', 20, 'CSE', 85)
   ↓
9. Success response:
   HTTP 302 Redirect
   Location: /servlet/curdoperation.html?success=User added successfully
   ↓
10. Browser navigates to success page
```

---

## Error Handling Flow

### Example: Invalid Input During Create

```
User submits form with invalid age (non-numeric)
   ↓
createUser() catches NumberFormatException
   ↓
Catches exception in catch block
   ↓
Sends redirect with error message:
   /adduser.html?error=Invalid age or marks
   ↓
Browser displays error to user
```

### Example: User Not Found During Update

```
PUT /servlet/api/user/999 (user doesn't exist)
   ↓
CrudServlet.doPut() parses ID (999)
   ↓
updateUser() calls UserService.getUserById(999)
   ↓
Database query returns null (no match)
   ↓
Response sent:
   HTTP 404 Not Found
   User not found
```

---

## Data Flow in Each Operation

### CREATE - Data Movement

```
HTML Form
   ↓ (form submission)
CrudServlet.createUser()
   ↓ (parameters extracted)
UserService.addUser()
   ↓ (INSERT statement)
MySQL Database
   ↓ (data persisted)
Client receives redirect response
```

### READ - Data Movement

```
Client request
   ↓
CrudServlet.doGet()
   ↓
UserService.getUserById() / getAllUsers()
   ↓ (SELECT query)
MySQL Database returns ResultSet
   ↓ (User object created)
User/List converted to JSON
   ↓
JSON response sent to client
```

### UPDATE - Data Movement

```
Client sends PUT request with new data
   ↓
CrudServlet.doPut() parses request body
   ↓
Parameters extracted and validated
   ↓
UserService.updateUser()
   ↓ (UPDATE statement)
MySQL Database updates record
   ↓
Response sent to client
```

### DELETE - Data Movement

```
Client sends DELETE request
   ↓
CrudServlet.doDelete() extracts ID
   ↓
UserService.deleteUser()
   ↓ (DELETE statement)
MySQL Database removes record
   ↓
Response sent to client
```

---

## Security Features

### Implemented

1. **SQL Injection Prevention**
   - Uses PreparedStatement instead of string concatenation
   - Parameters bound safely

2. **JSON Escaping**
   - `escapeJson()` method handles special characters
   - Prevents JSON injection attacks

3. **URL Encoding**
   - `encode()` method uses URLEncoder
   - UTF-8 charset specification

4. **Input Validation**
   - Server-side parameter validation
   - Type checking for numeric fields

### Recommendations for Production

1. Add authentication/authorization
2. Implement password hashing
3. Use connection pooling (HikariCP)
4. Add request rate limiting
5. Implement CORS properly
6. Use HTTPS
7. Add comprehensive logging
8. Implement input sanitization
9. Add transaction management
10. Remove hardcoded database credentials

---

## Deployment Structure

```
servlet (WAR file)
├── WEB-INF/
│   ├── web.xml (Deployment descriptor)
│   ├── classes/ (Compiled Java classes)
│   └── lib/ (Dependencies - JAR files)
├── adduser.html
├── updateuser.html
├── deleteuser.html
├── userview.html
├── alluser.html
├── curdoperation.html
├── index.jsp
└── [Other static resources]
```

---

## Summary

This CRUD application demonstrates a classic **3-tier architecture** with clear separation of concerns:

1. **Presentation** → User interfaces
2. **Controller** → Request routing & handling
3. **Business Logic** → CRUD operations
4. **Data Access** → Database connectivity
5. **Database** → Data persistence

Each layer has specific responsibilities, making the code maintainable, testable, and scalable. The REST API supports both web browser interactions and programmatic API calls via JSON.

