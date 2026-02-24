package com.abhi.arrayofobjectsassignment.asg2;


public class Intern extends Employee {

    private final double monthlyStipend;
    private final double taxDeduction;

    public Intern(int employeeId, String employeeName, double monthlyStipend, double taxDeduction) {

        super(employeeId, employeeName);

        this.monthlyStipend = monthlyStipend;
        this.taxDeduction = taxDeduction;
    }

    @Override
    public double calculateSalary(double additionalAmount) {
        return (monthlyStipend - taxDeduction )+ additionalAmount;
    }
}