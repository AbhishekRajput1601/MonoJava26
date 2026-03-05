package com.abhi.string;

import java.util.Scanner;

public class EmailAnalyzer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        email = email.trim();

        if (!email.contains("@")) {
            System.out.println("Invalid Email");
            return;
        }

        int atIndex = email.indexOf("@");
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        boolean isGmail = domain.equalsIgnoreCase("gmail.com");

        email = email.toLowerCase();

        int length = username.length();

        boolean hasDigit = false;
        for (int i = 0; i < username.length(); i++) {
            if (Character.isDigit(username.charAt(i))) {
                hasDigit = true;
                break;
            }
        }

        String modifiedUsername = username.replace(".", "_");

        System.out.println("Username: " + username);
        System.out.println("Domain: " + domain);
        System.out.println("Total characters in username: " + length);
        System.out.println("Contains digits: " + (hasDigit ? "Yes" : "No"));
        System.out.println("Modified username: " + modifiedUsername);
    }
}
