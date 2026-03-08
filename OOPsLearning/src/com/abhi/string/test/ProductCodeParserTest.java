package com.abhi.string.test;

import com.abhi.string.model.ProductCodeParser;

import java.util.Scanner;

public class ProductCodeParserTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product code: ");
        String code = sc.nextLine();

        ProductCodeParser.parseCode(code);
    }
}