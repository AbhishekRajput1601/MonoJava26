package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;

public class ArrayTest {
    private final ArrayExample arrayExample = new ArrayExample();

    @ParameterizedTest(name = "reverseArray({0})")
    @MethodSource("reverseArrayCases")
    void reverseArrayParameterisedCases(int[] input, int[] expected) {
        if (input == null) {
            assertNull(arrayExample.reverseArray(input));
            return;
        }

        assertArrayEquals(expected, arrayExample.reverseArray(input));
    }

    static Stream<Arguments> reverseArrayCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}),
                Arguments.of(new int[]{7}, new int[]{7}),
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(null, null)
        );
    }
}

