package com.abhi.streamapiassignment.asg4;

import java.util.*;
import java.util.stream.*;

public class BookService {

    // 1. available books
    public static void getAvailableBooks(List<Book> books) {
        books.stream()
                .filter(Book::isAvailable)
                .forEach(System.out::println);
    }

    // 2. group by genre
    public static void groupByGenre(List<Book> books) {
        Map<String, List<Book>> map = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));

        map.forEach((k, v) -> {
            System.out.println("\nGenre: " + k);
            v.forEach(System.out::println);
        });
    }

    // 3. count genre-wise
    public static void countByGenre(List<Book> books) {
        Map<String, Long> map = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    // 4. most expensive book
    public static void getMostExpensiveBook(List<Book> books) {
        books.stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .ifPresent(System.out::println);
    }

    // 5. titles sorted by price ascending
    public static void getTitlesSortedByPrice(List<Book> books) {
        books.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice))
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    // 6. any unavailable book
    public static void hasUnavailableBooks(List<Book> books) {
        boolean result = books.stream()
                .anyMatch(b -> !b.isAvailable());

        System.out.println("Any unavailable book? " + result);
    }

    // 7. unique authors set
    public static void getUniqueAuthors(List<Book> books) {
        Set<String> authors = books.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());

        authors.forEach(System.out::println);
    }
}
