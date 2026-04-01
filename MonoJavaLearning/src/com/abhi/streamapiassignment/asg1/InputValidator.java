package com.abhi.streamapiassignment.asg1;

import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) throw new Exception();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a valid positive integer.");
            }
        }
    }

    public static double getValidDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) throw new Exception();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a valid positive number.");
            }
        }
    }

    public static String getValidString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Invalid input! Cannot be empty.");
        }
    }

    public static boolean getValidBoolean(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Invalid input! Enter true or false.");
        }
    }
}
