package com.abhi.functionalinterface.consumer;

import java.util.*;
import java.util.function.Consumer;

class Invoice {
    String itemName;
    int quantity;
    int price;

    Invoice(String itemName, int quantity, int price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}

public class ConsumerAssignments {
    public static void main(String[] args) {

        // 1. Print String in Uppercase
        Consumer<String> upperCasePrinter = s -> System.out.println(s.toUpperCase());

        System.out.println("Uppercase:");
        upperCasePrinter.accept("hello");
        upperCasePrinter.accept("java");

        // 2. List Printer
        Consumer<List<String>> listPrinter = list -> {
            for (String item : list) {
                System.out.println(item);
            }
        };

        System.out.println("\nList Elements:");
        listPrinter.accept(Arrays.asList("A", "B", "C"));

        // 3. Invoice Formatter
        Consumer<Invoice> invoicePrinter = inv -> {
            int total = inv.quantity * inv.price;
            System.out.println("Item: " + inv.itemName + " | Quantity: " + inv.quantity + " | Total: ₹" + total);
        };

        System.out.println("\nInvoices:");
        invoicePrinter.accept(new Invoice("Pen", 2, 10));
        invoicePrinter.accept(new Invoice("Notebook", 3, 50));
        invoicePrinter.accept(new Invoice("Pencil", 5, 5));
    }
}
