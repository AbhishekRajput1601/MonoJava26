package com.abhi.string.model;


public class IPv4Validator {

    public static void validateIP(String ip) {

        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            System.out.println("Invalid IPv4 Address");
            return;
        }

        for (String part : parts) {

            int num = Integer.parseInt(part);

            if (num < 0 || num > 255) {
                System.out.println("Invalid IPv4 Address");
                return;
            }
        }

        System.out.println("Valid IPv4 Address");
    }
}
