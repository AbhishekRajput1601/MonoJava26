package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class TestNamingTest {
    @ParameterizedTest(name = "shouldReturnUpdatedBalanceWhenDepositIsSuccessful[{0}]")
    @MethodSource("successfulDepositCases")
    void shouldReturnUpdatedBalanceWhenDepositIsSuccessful(double initialBalance, double depositAmount, double expectedBalance) {
        TestNaming testNaming = new TestNaming(initialBalance);

        double updatedBalance = testNaming.deposit(depositAmount);

        assertEquals(expectedBalance, updatedBalance, 0.001);
        assertEquals(expectedBalance, testNaming.getBalance(), 0.001);
    }

    static Stream<Arguments> successfulDepositCases() {
        return Stream.of(
                Arguments.of(100.0, 50.0, 150.0),
                Arguments.of(0.0, 25.0, 25.0)
        );
    }

    @ParameterizedTest(name = "shouldThrowExceptionWhenDepositAmountIsZeroOrNegative[{0}]")
    @MethodSource("invalidDepositCases")
    void shouldThrowExceptionWhenDepositAmountIsZeroOrNegative(double initialBalance, double amount) {
        TestNaming testNaming = new TestNaming(initialBalance);

        assertThrows(IllegalArgumentException.class, () -> testNaming.deposit(amount));
    }

    static Stream<Arguments> invalidDepositCases() {
        return Stream.of(
                Arguments.of(100.0, 0.0),
                Arguments.of(100.0, -10.0)
        );
    }
}

