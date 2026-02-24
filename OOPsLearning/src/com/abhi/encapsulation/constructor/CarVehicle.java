package com.abhi.encapsulation.constructor;

public class CarVehicle extends Vehicle{

        String model;

        CarVehicle() {
            super();
            System.out.println("Car Default Constructor Called");
        }

        CarVehicle(String brand, String model) {
            super(brand);
            this.model = model;
            System.out.println("Car Parameterized Constructor Called");
        }

        public void getDetails() {
            System.out.println("Brand : " + brand);
            System.out.println("Model : " + model);
        }
}


