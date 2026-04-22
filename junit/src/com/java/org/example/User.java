package com.java.org.example;

public class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be null or blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("age must be non-negative");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

