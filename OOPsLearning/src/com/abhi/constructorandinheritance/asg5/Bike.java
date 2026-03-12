package com.abhi.constructorandinheritance.asg5;

class Bike extends Vehicle {

    private int engineCapacity;
    private String bikeType;

    public Bike(String registrationNumber, String ownerName, double baseUsageCharge,
                int engineCapacity, String bikeType) {

        super(registrationNumber, ownerName, baseUsageCharge);
        this.engineCapacity = engineCapacity;
        this.bikeType = bikeType;
    }

    public double calculateTotalFee() {
        return getBaseUsageCharge() + (engineCapacity * 0.5);
    }

    public void displayDetails() {

        System.out.println("\nBike Record");
        System.out.println("Vehicle Number : " + getRegistrationNumber());
        System.out.println("Owner Name     : " + getOwnerName());
        System.out.println("Engine CC      : " + engineCapacity);
        System.out.println("Bike Type      : " + bikeType);
        System.out.println("Total Fee      : " + calculateTotalFee());
    }
}