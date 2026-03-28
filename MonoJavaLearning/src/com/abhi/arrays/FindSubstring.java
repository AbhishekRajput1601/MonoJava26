package com.abhi.arrays;

import java.util.Scanner;

public class FindSubstring {

    private static String readNonEmptyString(Scanner sc, String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String mainStr = readNonEmptyString(sc, "Enter main string: ");
        String sub = readNonEmptyString(sc, "Enter substring: ");

        if (mainStr.contains(sub)) {
            System.out.println("Substring found at index: " + mainStr.indexOf(sub));
        } else {
            System.out.println("Substring not found.");
        }

        sc.close();
    }
}
