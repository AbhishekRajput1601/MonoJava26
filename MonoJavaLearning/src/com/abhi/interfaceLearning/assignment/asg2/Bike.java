package com.abhi.interfaceLearning.assignment.asg2;

class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Bike starts with self or kick.");
    }

    @Override
    public void stop() {
        System.out.println("Bike stops using hand brakes.");
    }

    @Override
    public String fuelType() {
        return "Petrol";
    }
}