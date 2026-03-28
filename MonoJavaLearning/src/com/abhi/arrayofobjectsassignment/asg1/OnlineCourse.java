package com.abhi.arrayofobjectsassignment.asg1;

public class OnlineCourse extends Course{

    private final double platformFee;

    public OnlineCourse(int courseId, String courseName, double baseFee, double platformFee) {
        super(courseId, courseName, baseFee);
        this.platformFee = platformFee;
    }

    @Override
    public double calculateFee(double extra){
        return baseFee + platformFee + extra;
    }
}
