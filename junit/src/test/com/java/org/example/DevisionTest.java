package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DevisionTest {
    private final Devision devision = new Devision();

    @ParameterizedTest(name = "divide({0}, {1}) throws ArithmeticException")
    @CsvSource({
            "10, 0, Cannot divide by zero",
            "0, 0, Cannot divide by zero"
    })
    void shouldThrowArithmeticExceptionWhenDividingByZero(int left, int right, String message) {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> devision.divide(left, right));

        assertEquals(message, exception.getMessage());
    }
}

