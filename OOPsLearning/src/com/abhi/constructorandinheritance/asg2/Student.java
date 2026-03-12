package com.abhi.constructorandinheritance.asg2;

class Student {

    private int studentId;
    private String studentName;
    private String enrolledCourse;

    public Student() {
        this(0, "Unknown", "None");
    }

    public Student(int studentId, String studentName, String enrolledCourse) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrolledCourse = enrolledCourse;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEnrolledCourse() {
        return enrolledCourse;
    }

    public void setEnrolledCourse(String enrolledCourse) {
        this.enrolledCourse = enrolledCourse;
    }

    public void displayDetails() {
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + studentName);
        System.out.println("Course     : " + enrolledCourse);
    }
}