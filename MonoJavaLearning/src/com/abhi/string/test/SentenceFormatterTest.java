package com.abhi.string.test;


import com.abhi.string.model.SentenceFormatter;

import java.util.Scanner;

public class SentenceFormatterTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        SentenceFormatter.formatSentence(sentence);
    }
}