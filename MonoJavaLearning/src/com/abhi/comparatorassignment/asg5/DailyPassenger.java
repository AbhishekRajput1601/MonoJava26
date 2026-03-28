package com.abhi.comparatorassignment.asg5;

public class DailyPassenger extends Passenger {

    private String passType;

    public DailyPassenger(String passengerId, String name, int routeNumber, String passType) {
        super(passengerId, name, routeNumber);
        this.passType = passType;
    }

    @Override
    public void travel() {
        System.out.println("Daily passenger travelling: " + passengerId);
    }
}