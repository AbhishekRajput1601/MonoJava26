package com.abhi.string.test;

import com.abhi.string.model.EmailAnalyzer;

import java.util.Scanner;

public class EmailAnalyzerTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        EmailAnalyzer.analyzeEmail(email);
    }
}