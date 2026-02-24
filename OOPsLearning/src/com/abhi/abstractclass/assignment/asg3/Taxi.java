package com.abhi.abstractclass.assignment.asg3;

public class Taxi extends Transport {

    private double distance;
    private double time; // in hours

    public Taxi(String routeId, double baseFare, double distance, double time) {
        super(routeId, baseFare);
        this.distance = distance;
        this.time = time;
    }

    @Override
    public double calculateFare() {
        double fare = baseFare + (distance * 5) + (time * 10); // Example: 5 per km + 10 per hour
        printTicket(fare);
        return fare;
    }
}