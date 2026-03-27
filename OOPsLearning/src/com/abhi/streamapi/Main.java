package com.abhi.streamapi;

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return name + " : " + marks;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " : ₹" + price;
    }
}

class Employee {
    String name;
    double salary;
    LocalDate joiningDate;
    String gender;

    Employee(String name, double salary, LocalDate joiningDate, String gender) {
        this.name = name;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.gender = gender;
    }

    public String toString() {
        return name + " : ₹" + salary + " : " + joiningDate + " : " + gender;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 35);
        List<String> names = Arrays.asList("Aman", "Ravi", "Ankit", "Abhishek", "", "  ");
        List<Student> students = Arrays.asList(
                new Student("Aman", 70),
                new Student("Ravi", 30),
                new Student("Ankit", 50),
                new Student("Abhishek", 20)
        );
        List<Product> products = Arrays.asList(
                new Product("Laptop", 60000),
                new Product("Mouse", 300),
                new Product("Keyboard", 700),
                new Product("Phone", 15000)
        );

        System.out.println("----- FILTER -----");

        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        names.stream().filter(n -> n.startsWith("A")).forEach(System.out::println);

        students.stream().filter(s -> s.marks >= 60).forEach(System.out::println);

        names.stream().filter(s -> !s.trim().isEmpty()).forEach(System.out::println);

        products.stream().filter(p -> p.price > 500).forEach(System.out::println);

        students.stream().filter(s -> s.marks >= 40).forEach(System.out::println);


        System.out.println("----- MAP -----");

        names.stream().map(String::toUpperCase).forEach(System.out::println);

        numbers.stream().map(n -> n * n).toList().forEach(System.out::println);

        names.stream().map(n -> "Mr./Ms. " + n).forEach(System.out::println);

        products.stream().map(p -> p.price * 0.9).forEach(System.out::println);

        numbers.stream().map(n -> n * 10).forEach(System.out::println);

        students.stream()
                .map(s -> s.marks < 35 ? new Student(s.name, s.marks + 5) : s)
                .forEach(System.out::println);


        System.out.println("----- COUNT -----");

        System.out.println(students.stream().filter(s -> s.marks >= 40).count());

        System.out.println("Even: " + numbers.stream().filter(n -> n % 2 == 0).count());
        System.out.println("Odd: " + numbers.stream().filter(n -> n % 2 != 0).count());

        System.out.println(numbers.stream().filter(n -> n % 2 == 0).count());

        System.out.println(students.stream().filter(s -> s.marks >= 40).count());

        System.out.println(
                names.stream()
                        .map(String::toUpperCase)
                        .filter(n -> n.startsWith("A"))
                        .count()
        );

        System.out.println(
                products.stream()
                        .map(p -> p.price * 0.8)
                        .filter(p -> p > 500)
                        .count()
        );


        System.out.println("----- SORTED -----");

        numbers.stream().sorted().forEach(System.out::println);

        names.stream().sorted().forEach(System.out::println);

        names.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        names.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);

        names.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);

        students.stream().sorted(Comparator.comparing(s -> s.marks)).forEach(System.out::println);


        System.out.println("----- MIN / MAX -----");

        System.out.println(numbers.stream().min(Integer::compare).get());

        System.out.println(names.stream().max(Comparator.comparing(String::length)).get());

        System.out.println(students.stream().max(Comparator.comparing(s -> s.marks)).get());

        System.out.println(
                numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .max(Integer::compare)
                        .get()
        );


        System.out.println("----- toArray -----");

        String[] arr = names.toArray(String[]::new);
        Arrays.stream(arr).forEach(System.out::println);

        int sum = Arrays.stream(new int[]{1,2,3,4}).sum();
        System.out.println(sum);

        Arrays.stream(arr).map(String::toUpperCase).forEach(System.out::println);

        List<String> list = Arrays.stream(arr).collect(Collectors.toList());
        System.out.println(list);


        System.out.println("----- SKIP -----");

        names.stream().skip(3).forEach(System.out::println);

        products.stream()
                .sorted((a,b)->Double.compare(b.price,a.price))
                .skip(2)
                .forEach(System.out::println);

        numbers.stream().filter(n->n%2==0).skip(2).forEach(System.out::println);


        System.out.println("----- LIMIT -----");

        names.stream().limit(3).forEach(System.out::println);

        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .forEach(System.out::println);

        System.out.println(
                numbers.stream()
                        .filter(n->n%2==0)
                        .limit(4)
                        .mapToInt(Integer::intValue)
                        .sum()
        );


        System.out.println("----- findFirst -----");

        System.out.println(names.stream().findFirst().get());

        System.out.println(numbers.stream().filter(n->n%2==0).findFirst().get());

        System.out.println(products.stream().filter(p->p.price>500).findFirst().get());


        System.out.println("----- anyMatch -----");

        System.out.println(numbers.stream().anyMatch(n->n%2==0));

        System.out.println(names.stream().anyMatch(n->n.startsWith("A")));

        System.out.println(students.stream().anyMatch(s->s.marks<40));


        System.out.println("----- EMPLOYEE -----");

        List<Employee> employees = Arrays.asList(
                new Employee("Aman", 50000, LocalDate.of(2020,1,1), "Male"),
                new Employee("Ravi", 70000, LocalDate.of(2018,5,10), "Male"),
                new Employee("Ankit", 60000, LocalDate.of(2019,3,15), "Male"),
                new Employee("Neha", 80000, LocalDate.of(2021,7,20), "Female")
        );

        System.out.println(
                employees.stream().max(Comparator.comparing(e->e.salary)).get()
        );

        System.out.println(
                employees.stream()
                        .sorted((a,b)->Double.compare(b.salary,a.salary))
                        .skip(1)
                        .findFirst()
                        .get()
        );

        System.out.println(
                employees.stream().min(Comparator.comparing(e->e.joiningDate)).get()
        );

        System.out.println(
                employees.stream()
                        .collect(Collectors.groupingBy(e->e.gender, Collectors.counting()))
        );
    }
}