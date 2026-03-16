package com.abhi.comparableandcomparator.asg4;

public class Product {

    String category;
    String name;
    double price;

    Product(String category, String name, double price) {

        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return category + " | " + name + " | " + price;
    }
}