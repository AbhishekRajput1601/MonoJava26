package com.abhi.abstractclass.assignment.asg3;

public class Metro extends Transport {

    private int numberOfStations;

    public Metro(String routeId, double baseFare, int numberOfStations) {
        super(routeId, baseFare);
        this.numberOfStations = numberOfStations;
    }

    @Override
    public double calculateFare() {
        double fare = baseFare + (numberOfStations * 10); // Example: 10 per station
        printTicket(fare);
        return fare;
    }
}