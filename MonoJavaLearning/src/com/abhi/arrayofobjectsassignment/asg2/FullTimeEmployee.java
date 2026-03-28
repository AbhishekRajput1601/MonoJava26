package com.abhi.arrayofobjectsassignment.asg2;

public class FullTimeEmployee extends Employee {

    private final double basicSalary;
    private final double houseRentAllowance;
    private final double dearnessAllowance;

    public FullTimeEmployee(int employeeId, String employeeName, double basicSalary,
                            double houseRentAllowance, double dearnessAllowance) {

        super(employeeId, employeeName);

        this.basicSalary = basicSalary;
        this.houseRentAllowance = houseRentAllowance;
        this.dearnessAllowance = dearnessAllowance;
    }

    @Override
    public double calculateSalary(double additionalAmount) {
        return basicSalary + houseRentAllowance + dearnessAllowance + additionalAmount;
    }
}