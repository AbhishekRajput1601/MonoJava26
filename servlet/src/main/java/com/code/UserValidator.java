package com.code;

public class UserValidator {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_BRANCH_LENGTH = 100;

    public static String validateUserInput(String name, String ageStr, String branch, String marksStr) {
        if (name == null || name.isEmpty()) {
            return "Name is required";
        }
        if (name.length() > MAX_NAME_LENGTH) {
            return "Name exceeds maximum length of " + MAX_NAME_LENGTH;
        }
        if (ageStr == null || ageStr.isEmpty()) {
            return "Age is required";
        }
        if (branch == null || branch.isEmpty()) {
            return "Branch is required";
        }
        if (branch.length() > MAX_BRANCH_LENGTH) {
            return "Branch exceeds maximum length of " + MAX_BRANCH_LENGTH;
        }
        if (marksStr == null || marksStr.isEmpty()) {
            return "Marks is required";
        }
        try {
            int age = Integer.parseInt(ageStr.trim());
            if (age < 0 || age > 120) {
                return "Age must be between 0 and 120";
            }
            int marks = Integer.parseInt(marksStr.trim());
            if (marks < 0 || marks > 100) {
                return "Marks must be between 0 and 100";
            }
        } catch (NumberFormatException e) {
            return "Age and Marks must be valid numbers";
        }
        return null; // No validation errors
    }

    public static String validateFieldLengths(String name, String branch) {
        if (name != null && name.length() > MAX_NAME_LENGTH) {
            return "Name exceeds maximum length of " + MAX_NAME_LENGTH;
        }
        if (branch != null && branch.length() > MAX_BRANCH_LENGTH) {
            return "Branch exceeds maximum length of " + MAX_BRANCH_LENGTH;
        }
        return null;
    }


    public static int parseAge(String ageStr) {
        if (ageStr == null || ageStr.trim().isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(ageStr.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int parseMarks(String marksStr) {
        if (marksStr == null || marksStr.trim().isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(marksStr.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

