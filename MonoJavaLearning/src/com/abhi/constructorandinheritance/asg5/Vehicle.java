package com.abhi.constructorandinheritance.asg5;

abstract class Vehicle {

    private String registrationNumber;
    private String ownerName;
    private double baseUsageCharge;

    public Vehicle(String registrationNumber, String ownerName, double baseUsageCharge) {

        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;

        if (baseUsageCharge >= 0) {
            this.baseUsageCharge = baseUsageCharge;
        } else {
            this.baseUsageCharge = 0;
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBaseUsageCharge() {
        return baseUsageCharge;
    }

    public abstract double calculateTotalFee();

    public abstract void displayDetails();
}