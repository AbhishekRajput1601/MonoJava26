package com.abhi.comparatorassignment.asg3;


public class PostgraduateStudent extends Student {

    private String specialization;

    public PostgraduateStudent(String id, String name, String dept, String specialization) {
        super(id, name, dept);
        this.specialization = specialization;
    }
}