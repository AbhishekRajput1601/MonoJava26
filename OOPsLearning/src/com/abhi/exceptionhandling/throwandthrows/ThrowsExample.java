package com.abhi.exceptionhandling.throwandthrows;

import java.io.IOException;
import java.util.Scanner;

public class ThrowsExample {

    static void checkInput(String text) throws IOException {

        if (text.length() < 5) {
            throw new IOException("Input must be at least 5 characters.");
        }

        System.out.println("Valid input: " + text);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter text: ");
            String input = scanner.nextLine();

            checkInput(input);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}