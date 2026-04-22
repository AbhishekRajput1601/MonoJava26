package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvSource({
            "2, 3, 5",
            "-3, -4, -7",
            "-6, -4, -10",
            "8, 0, 8",
            "-3, 3, 0"
    })
    void addParameterisedCases(int left, int right, int expected) {
        int result = calculator.add(left, right);
        assertEquals(expected, result);
        assertNotEquals(expected + 1, result);
    }

    @ParameterizedTest(name = "subtract({0}, {1}) = {2}")
    @CsvSource({
            "5, 3, 2",
            "-10, -3, -7",
            "-8, -2, -6",
            "9, 0, 9",
            "4, 4, 0"
    })
    void subtractParameterisedCases(int left, int right, int expected) {
        int result = calculator.subtract(left, right);
        assertEquals(expected, result);
        assertNotEquals(expected + 1, result);
    }

    @ParameterizedTest(name = "multiply({0}, {1}) = {2}")
    @CsvSource({
            "3, 4, 12",
            "-3, -4, 12",
            "-2, 5, -10",
            "7, 0, 0",
            "0, 0, 0"
    })
    void multiplyParameterisedCases(int left, int right, int expected) {
        int result = calculator.multiply(left, right);
        assertEquals(expected, result);
        assertNotEquals(expected == 0 ? 1 : -expected, result);
    }

    @ParameterizedTest(name = "divide({0}, {1}) = {2}")
    @CsvSource({
            "8, 2, 4",
            "-9, -3, 3",
            "-12, 3, -4",
            "0, 5, 0",
            "1, 2, 0"
    })
    void divideParameterisedCases(int left, int right, int expected) {
        int result = calculator.divide(left, right);
        assertEquals(expected, result);
        assertNotEquals(expected + 1, result);
    }
}