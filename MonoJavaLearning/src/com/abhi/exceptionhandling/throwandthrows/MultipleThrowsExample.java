package com.abhi.exceptionhandling.throwandthrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MultipleThrowsExample {

    static void processData(int number, String text)
            throws IOException, SQLException {

        if (number < 0) {
            throw new SQLException("Number cannot be negative.");
        }

        if (text.isEmpty()) {
            throw new IOException("Text cannot be empty.");
        }

        System.out.println("Processing successful.");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter number: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter text: ");
            String text = scanner.nextLine();

            processData(num, text);

        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        scanner.close();
    }
}
