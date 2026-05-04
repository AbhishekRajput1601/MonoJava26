- Database setup script for Student Course Registration Portal

-- Create database
CREATE DATABASE IF NOT EXISTS student_registration;

-- Use the database
USE student_registration;

-- Create registrations table
CREATE TABLE IF NOT EXISTS registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course_name VARCHAR(50) NOT NULL,
    batch_time VARCHAR(50) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create an index on email for faster queries
CREATE INDEX idx_email ON registrations(email);

-- Create an index on registration_date for sorting
CREATE INDEX idx_registration_date ON registrations(registration_date);

-- Sample query to view all registrations
-- SELECT * FROM registrations ORDER BY registration_date DESC;

