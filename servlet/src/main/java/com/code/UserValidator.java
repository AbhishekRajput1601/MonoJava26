package com.code;

/**
 * Utility class for validating user input data.
 * Handles validation of user fields like name, age, branch, and marks.
 */
public class UserValidator {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_BRANCH_LENGTH = 100;

    /**
     * Validates all user input fields for creation or update.
     * @param name the user's name
     * @param ageStr the age as a string
     * @param branch the user's branch
     * @param marksStr the marks as a string
     * @return error message if validation fails, null if valid
     */
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

    /**
     * Validates field lengths for update operations.
     * @param name the user's name
     * @param branch the user's branch
     * @return error message if validation fails, null if valid
     */
    public static String validateFieldLengths(String name, String branch) {
        if (name != null && name.length() > MAX_NAME_LENGTH) {
            return "Name exceeds maximum length of " + MAX_NAME_LENGTH;
        }
        if (branch != null && branch.length() > MAX_BRANCH_LENGTH) {
            return "Branch exceeds maximum length of " + MAX_BRANCH_LENGTH;
        }
        return null;
    }

    /**
     * Tries to parse age as an integer.
     * @param ageStr the age as a string
     * @return the parsed age, or -1 if parsing fails
     */
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

    /**
     * Tries to parse marks as an integer.
     * @param marksStr the marks as a string
     * @return the parsed marks, or -1 if parsing fails
     */
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

