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

How to build & run (local)
--------------------------
1. Ensure JDK is installed and `javac`/`java` are on PATH.
2. Add MySQL JDBC connector JAR to your classpath when running.
3. Compile:

```powershell
# from project root (Windows PowerShell)
New-Item -ItemType Directory -Force -Path out | Out-Null
Get-ChildItem -Path "src" -Recurse -Filter *.java | ForEach-Object { $_.FullName } | %{ & javac -d out $_ }
```

4. Run with the JDBC driver on the classpath:

```powershell
java -cp "out;C:\path\to\mysql-connector-java.jar" com.project.app.app.MainApplication
```

Notes
- Update DB credentials in `src/com/project/app/util/DatabaseConnectionUtil.java`.
- The application expects the database `registration_system` to exist and be reachable.

UML Class Diagram (PlantUML)
----------------------------
Paste this into a PlantUML editor or save as `class_diagram.puml` and render.

```plantuml
@startuml
title Registration System - Class Diagram (high-level)

package com.project.app.model {
  class StudentModel {
    - int studentId
    - String studentName
    - int studentAge
    - String studentBranch
    + StudentModel(int,String,int,String)
    + getStudentId(): int
    + getStudentName(): String
    + getStudentAge(): int
    + getStudentBranch(): String
  }

  class RegistrationModel {
    - int registrationId
    - int studentId
    - int courseId
    - String courseName
    - double feesPaid
    + RegistrationModel(int,int,int,String,double)
    + getRegId(): int
    + getStudentId(): int
    + getCourseId(): int
    + getCourseName(): String
    + getFeesPaid(): double
  }

  class CourseModel {
    - int courseId
    - String courseName
    + CourseModel(int,String)
    + getCourseId(): int
    + getCourseName(): String
  }

  class BranchModel {
    - int branchId
    - String branchName
    + BranchModel(int,String)
    + getBranchId(): int
    + getBranchName(): String
  }
}

package com.project.app.dao {
  class StudentDataAccessObject {
    + insertStudent(Connection, StudentModel): boolean
    + isStudentExists(Connection, int): boolean
    + findStudentById(Connection, int): Optional<StudentModel>
    + updateStudentDetails(Connection,int,String,String): int
    + deleteStudentById(Connection,int): int
    - getOrCreateBranchId(Connection,String): int
  }

  class RegistrationDataAccessObject {
    + isDuplicateRegistration(Connection,int,int): boolean
    + insertRegistration(Connection,int,int,double): boolean
    + updateCourseFee(Connection,int,int,double): int
    + deleteRegistration(Connection,int,int): int
    + deleteRegistrationsByStudentId(Connection,int): void
    + findRegistrationsByStudentId(Connection,int): List<RegistrationModel>
    + fetchAllStudentsWithRegistrations(Connection): List<String>
    + highPayingStudents(Connection,double): List<String>
    + courseWiseCount(Connection): List<String>
    + fetchAllCourses(Connection): List<CourseModel>
  }

  class CourseDataAccessObject {
    + insertCourse(Connection,String): boolean
    + isCourseExists(Connection,String): boolean
    + updateCourseName(Connection,int,String): boolean
    + deleteCourseById(Connection,int): boolean
    + fetchAllCourses(Connection): List<CourseModel>
  }

  class BranchDataAccessObject {
    + insertBranch(Connection,String): boolean
    + isBranchExists(Connection,String): boolean
    + updateBranchName(Connection,int,String): boolean
    + deleteBranchById(Connection,int): boolean
    + fetchAllBranches(Connection): List<BranchModel>
  }
}

package com.project.app.service {
  class StudentServiceLayer {
    - StudentDataAccessObject studentDAO
    - RegistrationDataAccessObject registrationDAO
    - CourseDataAccessObject courseDAO
    - BranchDataAccessObject branchDAO
    + addNewStudent(StudentModel): void
    + registerStudentForCourse(int, int, double): void
    + viewAllStudentsWithRegistrations(): void
    + searchStudentRegistrationById(int): void
    + updateStudentDetails(int,String,String): void
    + updateCourseFee(int,int,double): void
    + cancelCourseRegistration(int,int): void
    + deleteStudentCompletely(int): void
    + generateHighPayingStudentsReport(double): void
    + generateCourseWiseCountReport(): void
    + getAllCourses(): List<CourseModel>
    + getAllBranches(): List<BranchModel>
    + isStudentExists(int): boolean
    + getStudentById(int): Optional<StudentModel>
    + addCourse(String): void
    + updateCourseName(int,String): void
    + deleteCourse(int): void
    + addBranch(String): void
    + updateBranchName(int,String): void
    + deleteBranch(int): void
  }
}

package com.project.app.util {
  class DatabaseConnectionUtil {
    + getDatabaseConnection(): Connection
  }
  class InputValidationUtil {
    + readIntInRange(Scanner,int,int,String): int
    + readAnyInt(Scanner): int
    + readPositiveInt(Scanner,String): int
    + readPositiveDouble(Scanner,String): double
    + readNonNegativeDouble(Scanner,String): double
    + readNonBlank(Scanner,String): String
    + readValidName(Scanner,String): String
  }
}

package com.project.app.app {
  class MainApplication {
    + main(String[]): void
    - helper methods: selectCourseId(...), getUniqueStudentIdForAdd(...), getExistingStudentId(...)
  }
}

MainApplication ..> StudentServiceLayer : uses
MainApplication ..> InputValidationUtil : uses
StudentServiceLayer --> StudentDataAccessObject
StudentServiceLayer --> RegistrationDataAccessObject
StudentServiceLayer --> CourseDataAccessObject
StudentServiceLayer --> BranchDataAccessObject
StudentDataAccessObject --> DatabaseConnectionUtil
RegistrationDataAccessObject --> DatabaseConnectionUtil
CourseDataAccessObject --> DatabaseConnectionUtil
BranchDataAccessObject --> DatabaseConnectionUtil

StudentDataAccessObject ..> StudentModel
RegistrationDataAccessObject ..> RegistrationModel
CourseDataAccessObject ..> CourseModel
BranchDataAccessObject ..> BranchModel

@enduml
```

ER Diagram (PlantUML)
---------------------
Paste into PlantUML or save as `er_diagram.puml`.

```plantuml
@startuml
title Registration System - ER Diagram

entity "branch" as BR {
  * branch_id : INT <<PK, AUTO_INCREMENT>>
  --
  branch_name : VARCHAR(50) <<UNIQUE, NOT NULL>>
}

entity "course" as CO {
  * course_id : INT <<PK, AUTO_INCREMENT>>
  --
  course_name : VARCHAR(50) <<UNIQUE, NOT NULL>>
}

entity "student" as ST {
  * id : INT <<PK>>
  --
  name : VARCHAR(50) <<NOT NULL>>
  age : INT <<NOT NULL, CHECK(age > 0)>>
  branch_id : INT <<NOT NULL>>
}

entity "registration" as RG {
  * reg_id : INT <<PK, AUTO_INCREMENT>>
  --
  student_id : INT <<NOT NULL>>
  course_id : INT <<NOT NULL>>
  fees_paid : DOUBLE <<NOT NULL, CHECK(fees_paid > 0)>>
  --
  -- UNIQUE(student_id, course_id)
}

BR ||--o{ ST : "1..* students in branch\n(fk: student.branch_id -> branch.branch_id)"
CO ||--o{ RG : "1..* registrations per course\n(fk: registration.course_id -> course.course_id)"
ST ||--o{ RG : "1..* registrations per student\n(fk: registration.student_id -> student.id)\nON DELETE CASCADE"

note top of RG
  Constraint: UNIQUE(student_id, course_id)
end note

@enduml
```



