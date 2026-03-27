package com.abhi.functionalinterface.function;

import java.util.*;
import java.util.function.Function;

public class FunctionAssignments {
    public static void main(String[] args) {

        // 1. String Length Calculator
        Function<String, Integer> lengthFunc = s -> s.length();

        System.out.println("String Lengths:");
        List<String> names = Arrays.asList("John", "Alice", "Bob");

        for (String name : names) {
            System.out.println(name + " -> " + lengthFunc.apply(name));
        }

        // 2. Temperature Converter
        Function<Double, Double> cToF = c -> (c * 9 / 5) + 32;

        System.out.println("\nTemperature Conversion:");
        double[] temps = {0, 20, 37};

        for (double t : temps) {
            System.out.println(t + "C -> " + cToF.apply(t) + "F");
        }

        // 3. Student Grade Generator
        Function<Integer, String> gradeFunc = marks -> {
            if (marks >= 75) return "A";
            else if (marks >= 50) return "B";
            else return "Fail";
        };

        System.out.println("\nGrades:");
        List<Integer> marksList = Arrays.asList(80, 65, 45, 90, 30);

        for (int marks : marksList) {
            System.out.println(marks + " -> " + gradeFunc.apply(marks));
        }
    }
}