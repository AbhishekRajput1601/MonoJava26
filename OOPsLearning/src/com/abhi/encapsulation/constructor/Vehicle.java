package com.abhi.encapsulation.constructor;

public class Vehicle {

    String brand;

    Vehicle() {
        System.out.println("Vehicle Default Constructor Called");
    }

    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle Parameterized Constructor Called");
    }
}
