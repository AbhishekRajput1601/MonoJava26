package com.java.org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

public class StringTest {
	private final StringExample stringExample = new StringExample();

	@ParameterizedTest(name = "isEmpty({0}) -> {1}")
	@MethodSource("isEmptyCases")
	void isEmptyParameterisedCases(String input, boolean expected) {
		assertEquals(expected, stringExample.isEmpty(input));
	}

	static Stream<Arguments> isEmptyCases() {
		return Stream.of(
				arguments(null, true),
				arguments("", true),
				arguments("   ", false),
				arguments("hello", false)
		);
	}

	@ParameterizedTest(name = "toUpperCase({0}) -> {1}")
	@MethodSource("toUpperCaseCases")
	void toUpperCaseParameterisedCases(String input, String expected) {
		String result = stringExample.toUpperCase(input);
		if (expected == null) {
			assertNull(result);
		} else {
			assertNotNull(result);
			assertEquals(expected, result);
		}
	}

	static Stream<Arguments> toUpperCaseCases() {
		return Stream.of(
				arguments(null, null),
				arguments("", ""),
				arguments("   ", "   "),
				arguments("hello", "HELLO")
		);
	}

	@ParameterizedTest(name = "getLength({0}) -> {1}")
	@MethodSource("getLengthCases")
	void getLengthParameterisedCases(String input, int expected) {
		assertEquals(expected, stringExample.getLength(input));
	}

	static Stream<Arguments> getLengthCases() {
		return Stream.of(
				arguments(null, 0),
				arguments("", 0),
				arguments("   ", 3),
				arguments("hello", 5)
		);
	}
}
