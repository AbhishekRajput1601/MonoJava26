package com.abhi.encapsulation.constructorAssignment.asg3;


import java.util.Scanner;

public class ECommerceApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double price;
        do {
            System.out.print("Enter Product Price (>0): ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input! Enter numeric value.");
                sc.next();
            }
            price = sc.nextDouble();

            if (price <= 0) {
                System.out.println("Price must be greater than 0.\n");
            }

        } while (price <= 0);

        int stock;
        do {
            System.out.print("Enter Stock Quantity (>=0): ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter integer value.");
                sc.next();
            }
            stock = sc.nextInt();

            if (stock < 0) {
                System.out.println("Stock cannot be negative.\n");
            }

        } while (stock < 0);

        Product product = new Product(price, stock);


        int type;
        do {
            System.out.println("\nSelect Order Type:");
            System.out.println("1. Normal Order");
            System.out.println("2. Discounted Order");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter 1 or 2.");
                sc.next();
            }

            type = sc.nextInt();

        } while (type != 1 && type != 2);



        int quantity;
        do {
            System.out.print("Enter Quantity (>0): ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter integer value.");
                sc.next();
            }
            quantity = sc.nextInt();

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.\n");
            }

        } while (quantity <= 0);

        Order order;


        if (type == 2) {

            double discount;
            do {
                System.out.print("Enter Discount % (0-100): ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Invalid input! Enter numeric value.");
                    sc.next();
                }
                discount = sc.nextDouble();

                if (discount < 0 || discount > 100) {
                    System.out.println("Discount must be between 0 and 100.\n");
                }

            } while (discount < 0 || discount > 100);

            order = new DiscountedOrder(product, quantity, discount);

        } else {
            order = new Order(product, quantity);
        }


        System.out.println("\nOrder Created Successfully!");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Final Total Amount: ₹" + order.getTotalAmount());

        sc.close();
    }
}
