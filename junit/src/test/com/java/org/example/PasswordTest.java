package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTest {
    private final Password password = new Password();

    @ParameterizedTest(name = "password {0} -> {1}")
    @CsvSource({
            "Password1, true",
            "Pass1, false",
            "password1, false",
            "Password, false",
            "null, false",
            "'', false"
    })
    void passwordParameterisedCases(String value, boolean expected) {
        if ("null".equals(value)) {
            if (expected) {
                assertTrue(password.isValidPassword(null));
            } else {
                assertFalse(password.isValidPassword(null));
            }
            return;
        }

        if (expected) {
            assertTrue(password.isValidPassword(value));
        } else {
            assertFalse(password.isValidPassword(value));
        }
    }
}

