package com.abhi.encapsulation.constructor;

public class Car {

    String model;
    String des;
    int year;

    Car() {
        this("Default Model", "Default Description", 2000);
//        System.out.println("Default Constructor Called");
    }

    Car(String model, String des) {
        this.model = model;
        this.des = des;
        this.year = 0;
//        System.out.println("Two Parameter Constructor Called");
    }

    Car(String model, String des, int year) {
        this(model, des);
        this.year = year;
//        System.out.println("Three Parameter Constructor Called");
    }

    public void getCarDetails() {
        System.out.println("Model : " + model);
        System.out.println("Description : " + des);
        System.out.println("Year : " + year);
    }
}








//Car()
//   ↓
//Car(model, des, year)
//   ↓
//Car(model, des)
//
//Print 2-param
//Return ↑
//Print 3-param
//Return ↑
//Print Default