package com.abhi.interfaceLearning.assignment.asg2;

class Car implements Vehicle {

    @Override
    public void start() {
        System.out.println("Car starts with key ignition.");
    }

    @Override
    public void stop() {
        System.out.println("Car stops using brakes.");
    }

    @Override
    public String fuelType() {
        return "Diesel or Petrol";
    }
}