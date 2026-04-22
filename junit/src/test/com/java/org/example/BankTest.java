package com.java.org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @AfterEach
    void tearDown() {
        bank = null;
    }

    @ParameterizedTest(name = "valid operation {0}")
    @MethodSource("validOperationCases")
    void validOperationsUpdateBalance(Consumer<Bank> action, double expectedBalance) {
        action.accept(bank);
        assertEquals(expectedBalance, bank.getBalance(), 0.001);
    }

    static Stream<Arguments> validOperationCases() {
        return Stream.of(
                Arguments.of((Consumer<Bank>) b -> b.deposit(100.0), 100.0),
                Arguments.of((Consumer<Bank>) b -> {
                    b.deposit(150.0);
                    b.withdraw(40.0);
                }, 110.0),
                Arguments.of((Consumer<Bank>) b -> {
                    b.deposit(200.0);
                    b.withdraw(60.0);
                    b.deposit(10.0);
                }, 150.0)
        );
    }

    @ParameterizedTest(name = "invalid operation {0}")
    @MethodSource("invalidOperationCases")
    void invalidOperationsDoNotChangeBalance(Consumer<Bank> action, double startingBalance) {
        bank.deposit(startingBalance);
        assertThrows(IllegalArgumentException.class, () -> action.accept(bank));
        assertEquals(startingBalance, bank.getBalance(), 0.001);
    }

    static Stream<Arguments> invalidOperationCases() {
        return Stream.of(
                Arguments.of((Consumer<Bank>) b -> b.deposit(0.0), 0.0),
                Arguments.of((Consumer<Bank>) b -> b.withdraw(25.0), 20.0),
                Arguments.of((Consumer<Bank>) b -> b.withdraw(-5.0), 30.0)
        );
    }
}

