package com.project.app.model;

public class RegistrationModel {
    private final int registrationId;
    private final int studentId;
    private final int courseId;
    private final String courseName;
    private final double feesPaid;

    public RegistrationModel(int registrationId, int studentId, int courseId, String courseName, double feesPaid) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.feesPaid = feesPaid;
    }

    public int getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public double getFeesPaid() {
        return feesPaid;
    }
}
