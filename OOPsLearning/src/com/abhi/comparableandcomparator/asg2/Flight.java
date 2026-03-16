package com.abhi.comparableandcomparator.asg2;

public class Flight {

    String airline;
    double fare;

    Flight(String airline, double fare) {
        this.airline = airline;
        this.fare = fare;
    }

    public String toString() {
        return airline + " - " + fare;
    }
}