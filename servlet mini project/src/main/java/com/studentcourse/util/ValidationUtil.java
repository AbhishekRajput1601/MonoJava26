package com.studentcourse.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValidStudentName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        // Strip dashes and spaces
        String cleaned = phone.replaceAll("[\\s-]", "");
        return cleaned.matches("^\\d{10}$");
    }

    public static boolean isValidAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDuration(String duration) {
        if (duration == null || duration.trim().isEmpty()) {
            return false;
        }
        try {
            double dur = Double.parseDouble(duration);
            return dur > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidFees(String feesStr) {
        try {
            double fees = Double.parseDouble(feesStr);
            return fees > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidTrainerName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean isValidRegistrationDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }
}

