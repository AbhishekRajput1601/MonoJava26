package com.abhi.functionalinterface.supplier;

import java.util.*;
import java.util.function.Supplier;

public class SupplierAssignments {
    public static void main(String[] args) {

        // 1. Random Number Generator
        Supplier<Double> randomSupplier = () -> Math.random();

        System.out.println("Random Numbers:");
        System.out.println(randomSupplier.get());


        // 2. Default City Provider
        Supplier<String> defaultCity = () -> "Pune";

        String userInput = "Indore";


        String city = (userInput == null || userInput.trim().isEmpty()) ? defaultCity.get() : userInput;


        System.out.println("\nCity: " + city);

        // 3. Product List Generator
        Supplier<List<String>> productSupplier = () -> Arrays.asList("Pen", "Notebook", "Pencil", "Eraser", "Marker");

        System.out.println("\nProducts:");
        for (String product : productSupplier.get()) {
            System.out.println(product);
        }
    }
}
