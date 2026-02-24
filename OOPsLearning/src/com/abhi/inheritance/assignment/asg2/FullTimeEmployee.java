package com.abhi.inheritance.assignment.asg2;

public class FullTimeEmployee extends Employee {

    private double basic;
    private double hra;
    private double da;

    public FullTimeEmployee(String name, double basic, double hra, double da) {
        super(name);
        this.basic = basic;
        this.hra = hra;
        this.da = da;
    }

    @Override
    public double calculateSalary() {
        return basic + hra + da;
    }
}
