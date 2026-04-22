package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {
    private final ExceptionExample example = new ExceptionExample();

    @ParameterizedTest(name = "withdraw({0}, {1}) throws '{2}'")
    @CsvSource({
            "100.0, 150.0, Amount exceeds balance",
            "100.0, 0.0, Amount must be greater than zero",
            "100.0, -10.0, Amount must be greater than zero"
    })
    void shouldThrowExceptionForInvalidWithdrawals(double balance, double amount, String message) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> example.withdraw(balance, amount));

        assertEquals(message, exception.getMessage());
    }
}

