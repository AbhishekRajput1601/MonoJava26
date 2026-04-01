package com.abhi.streamapiassignment.asg2;

import java.util.*;
import java.util.stream.*;

public class StudentService {

    // 1. passed students
    public static void getPassedStudents(List<Student> list, double minMarks) {
        list.stream()
                .filter(s -> s.getMarks() >= minMarks)
                .forEach(System.out::println);
    }

    // 2. top 3 students
    public static void getTop3Students(List<Student> list) {
        list.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .limit(3)
                .forEach(System.out::println);
    }

    // 3. group by section
    public static void groupBySection(List<Student> list) {
        Map<String, List<Student>> map = list.stream()
                .collect(Collectors.groupingBy(Student::getSection));

        map.forEach((k, v) -> {
            System.out.println("\nSection: " + k);
            v.forEach(System.out::println);
        });
    }

    // 4. count section-wise
    public static void countBySection(List<Student> list) {
        Map<String, Long> map = list.stream()
                .collect(Collectors.groupingBy(Student::getSection, Collectors.counting()));

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    // 5. average marks section-wise
    public static void avgMarksBySection(List<Student> list) {
        Map<String, Double> map = list.stream()
                .collect(Collectors.groupingBy(Student::getSection,
                        Collectors.averagingDouble(Student::getMarks)));

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    // 6. uppercase names
    public static void getUppercaseNames(List<Student> list) {
        list.stream()
                .map(s -> s.getName().toUpperCase())
                .forEach(System.out::println);
    }

    // 7. any full marks
    public static void hasFullMarks(List<Student> list) {
        boolean result = list.stream()
                .anyMatch(s -> s.getMarks() == 100);

        System.out.println("Any student scored full marks? " + result);
    }
}
