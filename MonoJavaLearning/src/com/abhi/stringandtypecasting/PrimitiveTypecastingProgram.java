package com.abhi.stringandtypecasting;

public class PrimitiveTypecastingProgram {

    public static void main(String[] args) {

        int intValue = 100;
        double doubleValue = intValue;   // Widening

        System.out.println("Widening: " + doubleValue);

        double decimal = 99.99;
        int narrowed = (int) decimal;   // Narrowing

        System.out.println("Narrowing: " + narrowed);
    }
}
