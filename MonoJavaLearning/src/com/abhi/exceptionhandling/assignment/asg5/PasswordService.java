package com.abhi.exceptionhandling.assignment.asg5;

import java.util.Scanner;

public class PasswordService {

    public void checkPassword() throws InvalidPasswordException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (password.length() < 8 || !password.matches(".*\\d.*")) {
            throw new InvalidPasswordException(
                    "Password must be at least 8 characters and contain a digit."
            );
        }

        System.out.println("Password is valid.");
    }
}