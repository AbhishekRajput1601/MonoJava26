package com.abhi.streamapiassignment.asg1;

import java.io.Serializable;

public class Employee implements Serializable {
    private int employeeId;
    private String name;
    private String department;
    private double salary;
    private int experience;
    private boolean activeStatus;

    public Employee(int employeeId, String name, String department, double salary, int experience, boolean activeStatus) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
        this.activeStatus = activeStatus;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getExperience() { return experience; }
    public boolean isActiveStatus() { return activeStatus; }

    @Override
    public String toString() {
        return "ID: " + employeeId + ", Name: " + name + ", Dept: " + department +
                ", Salary: " + salary + ", Exp: " + experience + ", Active: " + activeStatus;
    }
}
