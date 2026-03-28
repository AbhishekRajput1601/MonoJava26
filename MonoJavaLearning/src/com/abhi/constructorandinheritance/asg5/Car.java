package com.abhi.constructorandinheritance.asg5;

class Car extends Vehicle {

    private int numberOfSeats;
    private String fuelType;

    public Car(String registrationNumber, String ownerName, double baseUsageCharge,
               int numberOfSeats, String fuelType) {

        super(registrationNumber, ownerName, baseUsageCharge);
        this.numberOfSeats = numberOfSeats;
        this.fuelType = fuelType;
    }

    public double calculateTotalFee() {
        return getBaseUsageCharge() + (numberOfSeats * 50);
    }

    public void displayDetails() {

        System.out.println("\nCar Record");
        System.out.println("Vehicle Number : " + getRegistrationNumber());
        System.out.println("Owner Name     : " + getOwnerName());
        System.out.println("Seats          : " + numberOfSeats);
        System.out.println("Fuel Type      : " + fuelType);
        System.out.println("Total Fee      : " + calculateTotalFee());
    }
}