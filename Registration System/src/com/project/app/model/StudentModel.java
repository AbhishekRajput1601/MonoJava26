package com.project.app.model;

public class StudentModel {
    private final int studentId;
    private String studentName;
    private final int studentAge;
    private String studentBranch;

    public StudentModel(int studentId, String studentName, int studentAge, String studentBranch) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentBranch = studentBranch;
    }

    public int getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public int getStudentAge() {
        return studentAge;
    }
    public String getStudentBranch() {
        return studentBranch;
    }

}