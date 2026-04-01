package com.abhi.streamapiassignment.asg1;

import java.util.*;
import java.util.stream.*;

public class EmployeeService {

    public static void getActiveEmployees(List<Employee> employees) {
        employees.stream()
                .filter(Employee::isActiveStatus)
                .forEach(System.out::println);
    }

    public static void getEmployeesAboveSalary(List<Employee> employees, double threshold) {
        employees.stream()
                .filter(e -> e.getSalary() > threshold)
                .forEach(System.out::println);
    }

    public static void countByDepartment(List<Employee> employees) {
        Map<String, Long> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public static void getHighestPaid(List<Employee> employees) {
        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    public static void getNamesSortedBySalary(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    public static void groupByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        map.forEach((k, v) -> {
            System.out.println("\nDepartment: " + k);
            v.forEach(System.out::println);
        });
    }

    public static void averageSalaryByDepartment(List<Employee> employees) {
        Map<String, Double> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}

