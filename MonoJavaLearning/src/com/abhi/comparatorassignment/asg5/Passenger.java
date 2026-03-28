package com.abhi.comparatorassignment.asg5;


public abstract class Passenger implements Comparable<Passenger> {

    protected String passengerId;
    protected String name;
    protected int routeNumber;

    public Passenger(String passengerId, String name, int routeNumber) {
        this.passengerId = passengerId;
        this.name = name;
        this.routeNumber = routeNumber;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public abstract void travel();

    @Override
    public int compareTo(Passenger other) {
        return this.passengerId.compareTo(other.passengerId);
    }
}