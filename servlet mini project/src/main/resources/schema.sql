CREATE DATABASE IF NOT EXISTS student_course_db;
USE student_course_db;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS registrations;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS admin;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    age INT NOT NULL,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    duration VARCHAR(50) NOT NULL,
    fees DOUBLE NOT NULL,
    trainer_name VARCHAR(100) NOT NULL
);

CREATE TABLE registrations (
    registration_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    registration_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_registrations_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT fk_registrations_course
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT chk_registration_status
        CHECK (status IN ('Active', 'Completed', 'Cancelled'))
);

DELIMITER $$

CREATE TRIGGER trg_registrations_prevent_duplicate_active_insert
BEFORE INSERT ON registrations
FOR EACH ROW
BEGIN
    IF NEW.status = 'Active' AND EXISTS (
        SELECT 1
        FROM registrations
        WHERE student_id = NEW.student_id
          AND course_id = NEW.course_id
          AND status = 'Active'
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Duplicate active registration is not allowed.';
    END IF;
END$$

CREATE TRIGGER trg_registrations_prevent_duplicate_active_update
BEFORE UPDATE ON registrations
FOR EACH ROW
BEGIN
    IF NEW.status = 'Active' AND EXISTS (
        SELECT 1
        FROM registrations
        WHERE student_id = NEW.student_id
          AND course_id = NEW.course_id
          AND status = 'Active'
          AND registration_id <> OLD.registration_id
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Duplicate active registration is not allowed.';
    END IF;
END$$

DELIMITER ;

INSERT INTO admin (username, password)
VALUES ('admin', 'admin123');

