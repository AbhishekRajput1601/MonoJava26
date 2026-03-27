package com.abhi.comparatorassignment.asg6;

import java.util.*;

public class InventoryManager {

    List<Product> productList = new ArrayList<>();
    private Set<String> productIds = new HashSet<>();
    private Map<String, List<Product>> categoryMap = new HashMap<>();
    private Queue<Product> returnQueue = new LinkedList<>();

    public void addProduct(Product product) {

        if (productIds.contains(product.getProductId())) {
            System.out.println("Duplicate product not allowed");
            return;
        }

        productList.add(product);
        productIds.add(product.getProductId());

        categoryMap.putIfAbsent(product.getCategory(), new ArrayList<>());
        categoryMap.get(product.getCategory()).add(product);

        System.out.println("Product added");
    }

    public void addReturnRequest(Product product) {
        returnQueue.add(product);
        System.out.println("Return request added");
    }

    public void processReturn() {

        if (returnQueue.isEmpty()) {
            System.out.println("No return requests");
            return;
        }

        Product product = returnQueue.poll();
        product.processReturn();
    }

    public void displayAllProducts() {

        for (Product p : productList) {
            System.out.println(p.getProductId() + " " + p.name + " " + p.price);
        }
    }

    public void displaySortedById() {

        List<Product> list = new ArrayList<>(productList);
        Collections.sort(list);

        for (Product p : list) {
            System.out.println(p.getProductId());
        }
    }

    public void displaySortedByPrice() {

        List<Product> list = new ArrayList<>(productList);
        list.sort(new PriceComparator());

        for (Product p : list) {
            System.out.println(p.getProductId() + " Price: " + p.price);
        }
    }

    public void displaySortedByName() {

        List<Product> list = new ArrayList<>(productList);
        list.sort(new NameComparator());

        for (Product p : list) {
            System.out.println(p.name);
        }
    }

    public void displayByCategory(String category) {

        List<Product> list = categoryMap.get(category);

        if (list == null) {
            System.out.println("No products found");
            return;
        }

        for (Product p : list) {
            System.out.println(p.getProductId());
        }
    }

    public void removeExpensiveProducts(double priceLimit) {

        Iterator<Product> iterator = productList.iterator();

        while (iterator.hasNext()) {
            Product p = iterator.next();

            if (p.price > priceLimit) {
                iterator.remove();
                productIds.remove(p.getProductId());
                returnQueue.remove(p);
            }
        }
    }
}