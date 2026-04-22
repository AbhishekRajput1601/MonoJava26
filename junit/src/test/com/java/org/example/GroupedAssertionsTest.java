package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class GroupedAssertionsTest {
    @ParameterizedTest(name = "user({0}, {1}, {2})")
    @MethodSource("userCases")
    void shouldValidateAllUserPropertiesWhenUserIsCreated(String name, int age, String status) {
        GroupedAssertions user = new GroupedAssertions(name, age, status);

        assertAll("user",
                () -> assertEquals(name, user.getName()),
                () -> assertEquals(age, user.getAge()),
                () -> assertEquals(status, user.getStatus())
        );
    }

    static Stream<Arguments> userCases() {
        return Stream.of(
                Arguments.of("Alice", 24, "ACTIVE"),
                Arguments.of("Bob", 30, "INACTIVE")
        );
    }
}

