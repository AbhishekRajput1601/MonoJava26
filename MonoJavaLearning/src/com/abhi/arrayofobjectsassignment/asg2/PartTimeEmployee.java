package com.abhi.arrayofobjectsassignment.asg2;

public class PartTimeEmployee extends Employee {

    private final int totalHoursWorked;
    private final double hourlyPaymentRate;

    public PartTimeEmployee(int employeeId, String employeeName, int totalHoursWorked, double hourlyPaymentRate) {

        super(employeeId, employeeName);

        this.totalHoursWorked = totalHoursWorked;
        this.hourlyPaymentRate = hourlyPaymentRate;
    }

    @Override
    public double calculateSalary(double additionalAmount) {
        return (totalHoursWorked * hourlyPaymentRate) + additionalAmount;
    }
}