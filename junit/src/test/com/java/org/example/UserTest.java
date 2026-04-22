package com.java.org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Abhi", 25);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @ParameterizedTest(name = "valid user {0}")
    @MethodSource("validUserCases")
    void validUser(String name, int age) {
        user = new User(name, age);

        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
    }

    static Stream<Arguments> validUserCases() {
        return Stream.of(
                Arguments.of("Abhi", 25),
                Arguments.of("Rajput", 30)
        );
    }

    @ParameterizedTest(name = "invalid user {0}")
    @MethodSource("invalidUserCases")
    void invalidUser(String name, int age) {
        assertThrows(IllegalArgumentException.class, () -> new User(name, age));
    }

    static Stream<Arguments> invalidUserCases() {
        return Stream.of(
                Arguments.of(null, 20),
                Arguments.of("", 20),
                Arguments.of("   ", 20),
                Arguments.of("Rajput", -1)
        );
    }
}

