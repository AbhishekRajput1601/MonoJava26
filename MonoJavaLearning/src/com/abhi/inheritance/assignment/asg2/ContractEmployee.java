package com.abhi.inheritance.assignment.asg2;

public class ContractEmployee extends Employee {

    private double fixedAmount;
    private double tax;

    public ContractEmployee(String name, double fixedAmount, double tax) {
        super(name);
        this.fixedAmount = fixedAmount;
        this.tax = tax;
    }

    @Override
    public double calculateSalary() {
        return fixedAmount - tax;
    }
}
