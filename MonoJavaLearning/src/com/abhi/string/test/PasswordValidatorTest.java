package com.abhi.string.test;

import com.abhi.string.model.PasswordValidator;

import java.util.Scanner;

public class PasswordValidatorTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        PasswordValidator.validatePassword(password);
    }
}