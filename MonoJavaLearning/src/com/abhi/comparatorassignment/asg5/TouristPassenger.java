package com.abhi.comparatorassignment.asg5;

public class TouristPassenger extends Passenger {

    private int numberOfDays;

    public TouristPassenger(String passengerId, String name, int routeNumber, int numberOfDays) {
        super(passengerId, name, routeNumber);
        this.numberOfDays = numberOfDays;
    }

    @Override
    public void travel() {
        System.out.println("Tourist passenger travelling: " + passengerId);
    }
}
