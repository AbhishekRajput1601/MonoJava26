package com.abhi.streamapiassignment.asg4;

import java.util.*;

public class MainBookApp {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();

        System.out.println("=== Library Book Inventory System ===");

        int n = InputValidator.getValidInt("Enter number of books: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Book " + (i + 1));

            int id = InputValidator.getValidInt("Book ID: ");
            String title = InputValidator.getValidString("Title: ");
            String author = InputValidator.getValidString("Author: ");
            String genre = InputValidator.getValidString("Genre: ");
            boolean available = InputValidator.getValidBoolean("Available (true/false): ");
            double price = InputValidator.getValidDouble("Price: ");

            books.add(new Book(id, title, author, genre, available, price));
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Available Books");
            System.out.println("2. Group by Genre");
            System.out.println("3. Count by Genre");
            System.out.println("4. Most Expensive Book");
            System.out.println("5. Titles Sorted by Price");
            System.out.println("6. Check Unavailable Books");
            System.out.println("7. Unique Authors");
            System.out.println("8. Exit");

            int choice = InputValidator.getValidInt("Enter choice: ");

            switch (choice) {
                case 1:
                    BookService.getAvailableBooks(books);
                    break;

                case 2:
                    BookService.groupByGenre(books);
                    break;

                case 3:
                    BookService.countByGenre(books);
                    break;

                case 4:
                    BookService.getMostExpensiveBook(books);
                    break;

                case 5:
                    BookService.getTitlesSortedByPrice(books);
                    break;

                case 6:
                    BookService.hasUnavailableBooks(books);
                    break;

                case 7:
                    BookService.getUniqueAuthors(books);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
