package com.java.org.example;

public class Password {
    public boolean isValidPassword(String value) {
        if (value == null || value.isEmpty() || value.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasNumber = false;

        for (char ch : value.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            if (Character.isDigit(ch)) {
                hasNumber = true;
            }
        }

        return hasUppercase && hasNumber;
    }
}

