package com.java.org.example;

public class GroupedAssertions {
    private final String name;
    private final int age;
    private final String status;

    public GroupedAssertions(String name, int age, String status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }
}

