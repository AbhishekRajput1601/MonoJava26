package com.abhi.arrayofobjectsassignment.asg1;

public class RegularCourse extends Course{

    private final double labFee;

    public RegularCourse(int courseId, String courseName, double baseFee, double labFee) {
        super(courseId, courseName, baseFee);
        this.labFee = labFee;
    }

    @Override
    public double calculateFee(double extra){
        return baseFee + labFee + extra;
    }
}
