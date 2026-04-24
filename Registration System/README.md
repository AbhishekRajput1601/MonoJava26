Registration System - Student Course Registration & Fee Management

Project Description
-------------------
This is a console-based Java application that manages students, courses, branches and registrations at a training institute. It follows a layered architecture:
- UI (MainApplication) — menu-driven console interface and immediate per-input validation.
- Service (StudentServiceLayer) — business logic, validation that needs DB checks, and transaction management for critical operations.
- DAO (JDBC) — data access objects that use PreparedStatement and try-with-resources.
- Database — MySQL schema with tables: branch, course, student, registration.

Key features
- Add / update / delete students
- Register students for courses (transactional; prevents duplicate registrations)
- Update course fees and cancel registrations
- Add / update / delete courses and branches
- View reports: all students with registrations, high-paying students, course-wise counts
- Input validation on each field (non-empty, name format, positive numbers, etc.)
- Safe DB operations (PreparedStatement, transactions where required)

Project structure (important files)
- src/com/project/app/app/MainApplication.java — console UI and menu
- src/com/project/app/service/StudentServiceLayer.java — business logic and transactions
- src/com/project/app/dao/StudentDataAccessObject.java — student DAO
- src/com/project/app/dao/RegistrationDataAccessObject.java — registration DAO
- src/com/project/app/dao/CourseDataAccessObject.java — course DAO
- src/com/project/app/dao/BranchDataAccessObject.java — branch DAO
- src/com/project/app/model/*Model.java — POJOs for Student, Registration, Course, Branch
- src/com/project/app/util/DatabaseConnectionUtil.java — DB connection helper
- src/com/project/app/util/InputValidationUtil.java — centralized input and validation helpers

Database schema (MySQL)
-----------------------
Use the following DDL to create the schema (database name: registration_system):

```sql
CREATE DATABASE IF NOT EXISTS registration_system;
USE registration_system;

CREATE TABLE branch (
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    branch_id INT NOT NULL,
    CONSTRAINT fk_student_branch FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);

CREATE TABLE registration (
    reg_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    fees_paid DOUBLE NOT NULL CHECK (fees_paid > 0),
    CONSTRAINT fk_registration_student FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    CONSTRAINT fk_registration_course FOREIGN KEY (course_id) REFERENCES course(course_id),
    CONSTRAINT uq_student_course UNIQUE (student_id, course_id)
);
```

Seed example (optional)
```sql
INSERT INTO branch (branch_name) VALUES ('Computer Science'), ('Information Technology')
  ON DUPLICATE KEY UPDATE branch_name = branch_name;

INSERT INTO course (course_name) VALUES ('Java'), ('Python'), ('Data Science')
  ON DUPLICATE KEY UPDATE course_name = course_name;
```


Mermaid diagrams
----------------
You can also view the diagrams using Mermaid. Paste the blocks below into https://mermaid.live/ or a Mermaid-enabled README viewer.

Class diagram (Mermaid):

```mermaid
classDiagram
    %% Models
    class StudentModel {
      - int studentId
      - String studentName
      - int studentAge
      - String studentBranch
      + StudentModel(int,String,int,String)
      + getStudentId() int
      + getStudentName() String
      + getStudentAge() int
      + getStudentBranch() String
    }

    class RegistrationModel {
      - int registrationId
      - int studentId
      - int courseId
      - String courseName
      - double feesPaid
      + RegistrationModel(int,int,int,String,double)
      + getRegId() int
      + getStudentId() int
      + getCourseId() int
      + getCourseName() String
      + getFeesPaid() double
    }

    class CourseModel {
      - int courseId
      - String courseName
      + CourseModel(int,String)
      + getCourseId() int
      + getCourseName() String
    }

    class BranchModel {
      - int branchId
      - String branchName
      + BranchModel(int,String)
      + getBranchId() int
      + getBranchName() String
    }

    %% DAOs
    class StudentDataAccessObject {
      + insertStudent(Connection,StudentModel) boolean
      + isStudentExists(Connection,int) boolean
      + findStudentById(Connection,int) Optional~StudentModel~
      + updateStudentDetails(Connection,int,String,String) int
      + deleteStudentById(Connection,int) int
    }

    class RegistrationDataAccessObject {
      + isDuplicateRegistration(Connection,int,int) boolean
      + insertRegistration(Connection,int,int,double) boolean
      + updateCourseFee(Connection,int,int,double) int
      + deleteRegistration(Connection,int,int) int
      + findRegistrationsByStudentId(Connection,int) List~RegistrationModel~
      + fetchAllStudentsWithRegistrations(Connection) List~String~
      + fetchAllCourses(Connection) List~CourseModel~
    }

    class CourseDataAccessObject {
      + insertCourse(Connection,String) boolean
      + isCourseExists(Connection,String) boolean
      + updateCourseName(Connection,int,String) boolean
      + deleteCourseById(Connection,int) boolean
      + fetchAllCourses(Connection) List~CourseModel~
    }

    class BranchDataAccessObject {
      + insertBranch(Connection,String) boolean
      + isBranchExists(Connection,String) boolean
      + updateBranchName(Connection,int,String) boolean
      + deleteBranchById(Connection,int) boolean
      + fetchAllBranches(Connection) List~BranchModel~
    }

    %% Service & Util & App
    class StudentServiceLayer {
      - StudentDataAccessObject studentDAO
      - RegistrationDataAccessObject registrationDAO
      - CourseDataAccessObject courseDAO
      - BranchDataAccessObject branchDAO
      + addNewStudent(StudentModel)
      + registerStudentForCourse(int,int,double)
      + deleteStudentCompletely(int)
      + updateStudentDetails(int,String,String)
      + updateCourseFee(int,int,double)
      + cancelCourseRegistration(int,int)
      + getAllCourses() List~CourseModel~
      + getAllBranches() List~BranchModel~
      + addCourse(String)
      + addBranch(String)
    }

    class InputValidationUtil {
      + readIntInRange(Scanner,int,int,String) int
      + readPositiveInt(Scanner,String) int
      + readPositiveDouble(Scanner,String) double
      + readNonBlank(Scanner,String) String
      + readValidName(Scanner,String) String
    }

    class DatabaseConnectionUtil {
      + getDatabaseConnection() Connection
    }

    class MainApplication {
      + main(String[])
      - selectCourseId(...)
      - getUniqueStudentIdForAdd(...)
      - getExistingStudentId(...)
    }

    MainApplication ..> StudentServiceLayer : uses
    MainApplication ..> InputValidationUtil : uses

    StudentServiceLayer --> StudentDataAccessObject : uses
    StudentServiceLayer --> RegistrationDataAccessObject : uses
    StudentServiceLayer --> CourseDataAccessObject : uses
    StudentServiceLayer --> BranchDataAccessObject : uses

    StudentDataAccessObject ..> StudentModel
    RegistrationDataAccessObject ..> RegistrationModel
    CourseDataAccessObject ..> CourseModel
    BranchDataAccessObject ..> BranchModel

    StudentDataAccessObject --> DatabaseConnectionUtil
    RegistrationDataAccessObject --> DatabaseConnectionUtil
    CourseDataAccessObject --> DatabaseConnectionUtil
    BranchDataAccessObject --> DatabaseConnectionUtil

```

ER diagram (Mermaid):

```mermaid
erDiagram
    BRANCH {
        int branch_id PK "auto-increment"
        string branch_name "UNIQUE, NOT NULL"
    }
    COURSE {
        int course_id PK "auto-increment"
        string course_name "UNIQUE, NOT NULL"
    }
    STUDENT {
        int id PK
        string name "NOT NULL"
        int age "NOT NULL, CHECK(age > 0)"
        int branch_id "NOT NULL"
    }
    REGISTRATION {
        int reg_id PK "auto-increment"
        int student_id "NOT NULL"
        int course_id "NOT NULL"
        double fees_paid "NOT NULL, CHECK(fees_paid > 0)"
        string unique_student_course "UNIQUE(student_id, course_id)"
    }

    BRANCH ||--o{ STUDENT : "branch_id -> branch.branch_id"
    COURSE ||--o{ REGISTRATION : "course_id -> course.course_id"
    STUDENT ||--o{ REGISTRATION : "student_id -> student.id (ON DELETE CASCADE)"

```
