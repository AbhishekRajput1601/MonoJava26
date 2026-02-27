package com.abhi.exceptionhandling.assignment.asg5;

public class Test {
    public static void main(String[] args) {

        PasswordService passwordService = new PasswordService();

        try {
            passwordService.checkPassword();
        } catch (InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }
    }
}
