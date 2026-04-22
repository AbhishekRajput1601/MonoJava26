package com.java.org.example;

public class StringExample {
	public boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public String toUpperCase(String value) {
		return value == null ? null : value.toUpperCase();
	}

	public int getLength(String value) {
		return value == null ? 0 : value.length();
	}
}
