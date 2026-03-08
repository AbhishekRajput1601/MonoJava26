package com.abhi.string.model;

public class ProductCodeParser {

    public static void parseCode(String code) {

        String[] parts = code.split("-");

        String category = parts[0];
        String product = parts[1];
        String year = parts[2];

        category = category.toUpperCase();

        boolean startsTV = product.startsWith("TV");
        boolean ends2023 = code.endsWith("2023");

        String modified = code.replace("-", " ");
        int firstHyphen = code.indexOf("-");

        System.out.println("Category: " + category);
        System.out.println("Product: " + product);
        System.out.println("Year: " + year);
        System.out.println("Starts with TV: " + (startsTV ? "Yes" : "No"));
        System.out.println("Ends with 2023: " + (ends2023 ? "Yes" : "No"));
        System.out.println("Modified Code: " + modified);
        System.out.println("First hyphen position: " + firstHyphen);
    }
}