package com.abhi.arrayofobjectsassignment.asg1;

public abstract class Course {

    protected int courseId;
    protected String courseName;
    protected double baseFee;
    protected static int totalCourses = 0;

    public Course(int courseId, String courseName, double baseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.baseFee = baseFee;
        totalCourses++;
    }

    public double calculateFee(){
        return baseFee;
    }

    public abstract double calculateFee(double extra);

    public void displayCourse(){
        System.out.println("Course ID : " +  courseId);
        System.out.println("Course Name : " + courseName);
    }

    public static int getTotalCourses(){
        return totalCourses;
    }
}
