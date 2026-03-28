package com.abhi.string.model;

public class ReverseWordCharacters {

    public static void reverseWords(String sentence) {

        String[] words = sentence.split(" ");

        for (String word : words) {

            String reversed = "";

            for (int i = word.length() - 1; i >= 0; i--) {
                reversed += word.charAt(i);
            }

            System.out.print(reversed + " ");
        }
    }
}