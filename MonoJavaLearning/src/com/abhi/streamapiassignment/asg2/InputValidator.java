package com.abhi.streamapiassignment.asg2;

import java.util.Scanner;

public class InputValidator {
    private static final Scanner sc = new Scanner(System.in);

    public static int getValidInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                int val = Integer.parseInt(sc.nextLine());
                if (val < 0) throw new Exception();
                return val;
            } catch (Exception e) {
                System.out.println("Invalid integer!");
            }
        }
    }

    public static double getValidDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                double val = Double.parseDouble(sc.nextLine());
                if (val < 0) throw new Exception();
                return val;
            } catch (Exception e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static String getValidString(String msg) {
        while (true) {
            System.out.print(msg);
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Cannot be empty!");
        }
    }
}