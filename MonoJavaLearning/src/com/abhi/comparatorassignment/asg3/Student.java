package com.abhi.comparatorassignment.asg3;

import java.util.*;

public abstract class Student implements Comparable<Student> {

    protected String studentId;
    protected String studentName;
    protected String department;
    protected Map<String, Integer> subjectMarks;

    public Student(String studentId, String studentName, String department) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.subjectMarks = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void addMarks(String subject, int marks) {
        subjectMarks.put(subject, marks);
    }

    public double getAverageMarks() {
        if (subjectMarks.isEmpty()) return 0;
        int sum = 0;
        for (int m : subjectMarks.values()) {
            sum += m;
        }
        return sum / (double) subjectMarks.size();
    }

    @Override
    public int compareTo(Student other) {
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.studentId.equals(other.studentId);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}