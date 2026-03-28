package com.abhi.arrayofobjectsassignment.asg3;


public class Car extends Vehicle {

    private final double baseTollAmount;

    public Car(int vehicleId, String vehicleNumber, double baseTollAmount) {
        super(vehicleId, vehicleNumber);
        this.baseTollAmount = baseTollAmount;
    }

    @Override
    public double calculateToll(double extraCharge) {
        return baseTollAmount + extraCharge;
    }
}