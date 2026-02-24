package com.abhi.abstractclass.assignment.asg3;

public abstract class Transport {

    protected String routeId;
    protected double baseFare;

    public Transport(String routeId, double baseFare) {
        this.routeId = routeId;
        this.baseFare = baseFare;
    }

    public final void printTicket(double finalFare) {
        System.out.println("----- Ticket for Route: " + routeId + " -----");
        System.out.println("Transport Type: " + this.getClass().getSimpleName());
        System.out.println("Base Fare: " + baseFare);
        System.out.println("Final Fare: " + finalFare);
        System.out.println("-----------------------------------------");
    }

    public abstract double calculateFare();
}