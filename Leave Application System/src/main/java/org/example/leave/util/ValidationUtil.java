package org.example.leave.util;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static String validate(String employeeName, String employeeId, String department,
                                  String leaveType, int leaveDays, String reason) {
        if (isBlank(employeeName)) {
            return "Employee Name not empty";
        }
        if (isBlank(employeeId)) {
            return "Employee ID not empty";
        }
        if (isBlank(department)) {
            return "Department not empty";
        }
        if (isBlank(leaveType)) {
            return "Leave Type must be selected";
        }
        if (leaveDays < 1 || leaveDays > 10) {
            return "Leave Days must be between 1 and 10";
        }
        if (isBlank(reason) || reason.trim().length() < 10) {
            return "Reason must have at least 10 characters";
        }
        return null;
    }

    public static int parseLeaveDays(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception exception) {
            return -1;
        }
    }

    public static String approvalMessage(int leaveDays) {
        if (leaveDays > 5) {
            return "This leave request requires manager approval";
        }
        return "This leave request can be processed normally";
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}


