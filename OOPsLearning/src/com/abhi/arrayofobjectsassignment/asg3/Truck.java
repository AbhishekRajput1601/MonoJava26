package com.abhi.arrayofobjectsassignment.asg3;

public class Truck extends Vehicle {

    private final double baseTollAmount;
    private final double loadCharge;

    public Truck(int vehicleId, String vehicleNumber, double baseTollAmount, double loadCharge) {
        super(vehicleId, vehicleNumber);
        this.baseTollAmount = baseTollAmount;
        this.loadCharge = loadCharge;
    }

    @Override
    public double calculateToll(double extraCharge) {
        return baseTollAmount + loadCharge + extraCharge;
    }
}