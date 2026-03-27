package com.abhi.functionalinterface.predicate;

import java.util.*;
import java.util.function.Predicate;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class PredicateAssignments {
    public static void main(String[] args) {

        // 1. Odd Number Checker
        Predicate<Integer> isOdd = x -> x % 2 != 0;
        System.out.println(isOdd.test(10));


        // 2. Non-empty String
        Predicate<String> validString = s -> s != null && !s.isEmpty();

        List<String> strings = Arrays.asList("", "Java", null, " ");

        System.out.println("\nValid Strings:");
        for (String s : strings) {
            if (validString.test(s)) {
                System.out.println(s);
            }
        }

        // 3. Student Pass Filter
        Predicate<Student> isPass = s -> s.marks >= 40;

        List<Student> students = Arrays.asList(
                new Student("Unknown", 35),
                new Student("Abhi", 60),
                new Student("Jay", 55),
                new Student("Gurpreet", 45),
                new Student("Milendra", 80)
        );

        System.out.println("\nPassing Students:");
        for (Student s : students) {
            if (isPass.test(s)) {
                System.out.println(s.name + " - " + s.marks);
            }
        }
    }
}