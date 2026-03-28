package com.abhi.comparableandcomparator.asg4;

import java.util.*;

public class ProductMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Product> list = new ArrayList<>();

        System.out.print("Enter number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            double price;

            while (true) {

                System.out.print("Enter price: ");

                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price >= 0) break;
                }

                System.out.println("Invalid price!");
                scanner.nextLine();
            }

            scanner.nextLine();

            list.add(new Product(category, name, price));
        }

        list.sort((p1, p2) -> {

            int catCompare = p1.category.compareTo(p2.category);

            if (catCompare == 0)
                return Double.compare(p1.price, p2.price);

            return catCompare;
        });

        System.out.println("\nSorted Products:");

        for (Product p : list)
            System.out.println(p);
    }
}