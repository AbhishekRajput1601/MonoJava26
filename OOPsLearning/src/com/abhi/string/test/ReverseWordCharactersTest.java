package com.abhi.string.test;


import com.abhi.string.model.ReverseWordCharacters;

import java.util.Scanner;

public class ReverseWordCharactersTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        ReverseWordCharacters.reverseWords(sentence);
    }
}
