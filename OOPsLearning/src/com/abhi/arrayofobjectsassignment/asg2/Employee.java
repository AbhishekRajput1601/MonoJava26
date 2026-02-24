package com.abhi.arrayofobjectsassignment.asg2;

public abstract class Employee {

    protected int employeeId;
    protected String employeeName;
    protected static int totalEmployeesCreated = 0;

    public Employee(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        totalEmployeesCreated++;
    }


    public double calculateSalary() {
        return 0;
    }

    public abstract double calculateSalary(double additionalAmount);

    public void displayEmployeeDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
    }

    public static int getTotalEmployeesCreated() {
        return totalEmployeesCreated;
    }
}