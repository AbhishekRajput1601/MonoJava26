package com.project.app.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidationUtil {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]+(?:\\s+[A-Za-z]+)*$");

    public static int readIntInRange(Scanner sc, int min, int max, String msg) {
        while (true) {
            System.out.print(msg);
            int val = readAnyInt(sc);
            if (val >= min && val <= max) return val;
            System.out.println("Invalid range.");
        }
    }

    public static int readAnyInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    public static int readPositiveInt(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            int val = readAnyInt(sc);
            if (val > 0) return val;
            System.out.println(field + " must be > 0");
        }
    }

    public static double readPositiveDouble(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            while (!sc.hasNextDouble()) {
                System.out.print("Enter valid number: ");
                sc.next();
            }
            double val = sc.nextDouble();
            sc.nextLine();
            if (val > 0) return val;
            System.out.println(field + " must be > 0");
        }
    }

    public static double readNonNegativeDouble(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            while (!sc.hasNextDouble()) {
                System.out.print("Enter valid number: ");
                sc.next();
            }
            double val = sc.nextDouble();
            sc.nextLine();
            if (val >= 0) return val;
            System.out.println(field + " cannot be negative");
        }
    }

    public static String readNonBlank(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println(field + " cannot be empty");
        }
    }

    public static String readValidName(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            String name = sc.nextLine().trim();
            if (NAME_PATTERN.matcher(name).matches()) return name;
            System.out.println("Only letters and spaces allowed");
        }
    }
}

