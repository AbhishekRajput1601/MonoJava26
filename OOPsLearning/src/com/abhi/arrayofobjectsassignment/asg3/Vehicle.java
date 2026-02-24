package com.abhi.arrayofobjectsassignment.asg3;

public abstract class Vehicle {

    protected int vehicleId;
    protected String vehicleNumber;

    protected static int totalVehiclesProcessed = 0;
    protected static double totalTollCollected = 0;

    public Vehicle(int vehicleId, String vehicleNumber) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        totalVehiclesProcessed++;
    }


    public double calculateToll() {
        return 0;
    }

    public abstract double calculateToll(double extraCharge);

    public void updateTotalToll(double tollAmount) {
        totalTollCollected += tollAmount;
    }

    public void displayVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Vehicle Number: " + vehicleNumber);
    }

    public static int getTotalVehiclesProcessed() {
        return totalVehiclesProcessed;
    }

    public static double getTotalTollCollected() {
        return totalTollCollected;
    }
}