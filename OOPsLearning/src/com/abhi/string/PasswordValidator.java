package com.abhi.string;

import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        password = password.replace(" ", "");

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch))
                hasUpper = true;

            if (Character.isLowerCase(ch))
                hasLower = true;

            if (Character.isDigit(ch))
                hasDigit = true;
        }

        boolean lengthValid = password.length() >= 8;

        System.out.println("Password: " + password);
        System.out.println("Length valid: " + (lengthValid ? "Yes" : "No"));
        System.out.println("Contains uppercase: " + (hasUpper ? "Yes" : "No"));
        System.out.println("Contains lowercase: " + (hasLower ? "Yes" : "No"));
        System.out.println("Contains digit: " + (hasDigit ? "Yes" : "No"));

        if (lengthValid && hasUpper && hasLower && hasDigit)
            System.out.println("Password is VALID");
        else
            System.out.println("Password is INVALID");
    }
}
