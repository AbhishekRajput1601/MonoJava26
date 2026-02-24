package com.abhi.abstractclass.assignment.asg3;

public class Bus extends Transport {

    private double distance;

    public Bus(String routeId, double baseFare, double distance) {
        super(routeId, baseFare);
        this.distance = distance;
    }

    @Override
    public double calculateFare() {
        double fare = baseFare + (distance * 5); // Example: 5 per km
        printTicket(fare);
        return fare;
    }
}