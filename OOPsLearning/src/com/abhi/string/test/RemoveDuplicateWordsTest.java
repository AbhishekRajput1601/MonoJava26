package com.abhi.string.test;

import com.abhi.string.model.RemoveDuplicateWords;

import java.util.Scanner;

public class RemoveDuplicateWordsTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        RemoveDuplicateWords.removeDuplicates(sentence);
    }
}