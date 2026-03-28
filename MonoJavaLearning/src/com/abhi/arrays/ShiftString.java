package com.abhi.arrays;

import java.util.Scanner;

public class ShiftString {

    private static int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                sc.next();
            }
        }
    }

    private static String readNonEmptyString(Scanner sc, String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("String cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = readNonEmptyString(sc, "Enter string: ");

        int shift = readInteger(sc, "Enter shift value: ");

        StringBuilder result = new StringBuilder();

        for (char ch : str.toCharArray()) {
            result.append((char) (ch + shift));
        }

        System.out.println("Shifted String: " + result);

        sc.close();
    }
}
