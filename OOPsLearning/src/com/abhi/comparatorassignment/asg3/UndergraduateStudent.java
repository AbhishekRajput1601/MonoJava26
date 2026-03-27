package com.abhi.comparatorassignment.asg3;


public class UndergraduateStudent extends Student {
    private int year;

    public UndergraduateStudent(String id, String name, String dept, int year) {
        super(id, name, dept);
        this.year = year;
    }
}