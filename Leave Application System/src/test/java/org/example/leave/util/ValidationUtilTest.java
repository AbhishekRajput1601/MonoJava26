package org.example.leave.util;

public final class ValidationUtilTest {

    public static void main(String[] args) {
        shouldReturnErrorWhenEmployeeNameIsBlank();
        shouldReturnErrorWhenLeaveDaysAreOutOfRange();
        shouldReturnErrorWhenReasonIsTooShort();
        shouldReturnMinusOneForInvalidLeaveDays();
        shouldReturnNullForValidInput();
        System.out.println("ValidationUtilTest passed");
    }

    private static void shouldReturnErrorWhenEmployeeNameIsBlank() {
        assertEquals("Employee Name not empty",
                ValidationUtil.validate("", "E001", "HR", "Sick Leave", 2, "Need leave for medical reasons"));
    }

    private static void shouldReturnErrorWhenLeaveDaysAreOutOfRange() {
        assertEquals("Leave Days must be between 1 and 10",
                ValidationUtil.validate("John", "E001", "HR", "Sick Leave", 11, "Need leave for medical reasons"));
    }

    private static void shouldReturnErrorWhenReasonIsTooShort() {
        assertEquals("Reason must have at least 10 characters",
                ValidationUtil.validate("John", "E001", "HR", "Sick Leave", 2, "short"));
    }

    private static void shouldReturnMinusOneForInvalidLeaveDays() {
        assertEquals(-1, ValidationUtil.parseLeaveDays("abc"));
    }

    private static void shouldReturnNullForValidInput() {
        assertNull(ValidationUtil.validate("John", "E001", "HR", "Sick Leave", 2, "Need leave for medical reasons"));
    }

    private static void assertEquals(Object expected, Object actual) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertNull(Object actual) {
        if (actual != null) {
            throw new AssertionError("Expected null but was: " + actual);
        }
    }
}


