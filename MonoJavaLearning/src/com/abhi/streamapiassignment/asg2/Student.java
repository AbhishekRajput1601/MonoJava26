package com.abhi.streamapiassignment.asg2;

import java.io.Serializable;

public class Student implements Serializable {
    private int rollNo;
    private String name;
    private String standard;
    private double marks;
    private String section;

    public Student(int rollNo, String name, String standard, double marks, String section) {
        this.rollNo = rollNo;
        this.name = name;
        this.standard = standard;
        this.marks = marks;
        this.section = section;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getStandard() { return standard; }
    public double getMarks() { return marks; }
    public String getSection() { return section; }

    @Override
    public String toString() {
        return "RollNo: " + rollNo + ", Name: " + name +
                ", Class: " + standard + ", Marks: " + marks +
                ", Section: " + section;
    }
}
