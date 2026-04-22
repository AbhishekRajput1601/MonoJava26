package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundaryTest {
    private final Boundary boundary = new Boundary();

    @ParameterizedTest(name = "isValidMarks({0}) -> {1}")
    @CsvSource({
            "0, true",
            "1, true",
            "99, true",
            "100, true",
            "-1, false",
            "101, false"
    })
    void boundaryParameterisedCases(int marks, boolean expected) {
        if (expected) {
            assertTrue(boundary.isValidMarks(marks));
        } else {
            assertFalse(boundary.isValidMarks(marks));
        }
    }
}

