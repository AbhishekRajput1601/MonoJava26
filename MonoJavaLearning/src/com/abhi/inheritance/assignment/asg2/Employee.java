package com.abhi.inheritance.assignment.asg2;


public abstract class Employee {

    private static int baseEmployeeId = 1000;

    protected int employeeId;
    protected String name;

    public Employee(String name) {
        this.employeeId = baseEmployeeId++;
        this.name = name;
    }

    public abstract double calculateSalary();

    public void displayEmployee() {
        System.out.println("\n-------- PAYSLIP --------");
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Salary      : " + calculateSalary());
        System.out.println("--------------------------");
    }
}
