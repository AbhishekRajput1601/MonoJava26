package com.abhi.string;

import java.util.Scanner;

public class FileNameValidator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String file = sc.nextLine();

        boolean isPDF = file.endsWith(".pdf");

        int dotIndex = file.lastIndexOf(".");
        String extension = file.substring(dotIndex + 1);

        String name = file.substring(0, dotIndex);

        name = name.replace("_", " ");

        String upperName = name.toUpperCase();

        boolean containsFinal = name.toLowerCase().contains("final");

        System.out.println("File Name: " + name);
        System.out.println("Extension: " + extension);
        System.out.println("Is PDF file: " + (isPDF ? "Yes" : "No"));
        System.out.println("Contains 'final': " + (containsFinal ? "Yes" : "No"));
    }
}
