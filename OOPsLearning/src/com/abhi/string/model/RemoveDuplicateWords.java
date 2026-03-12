package com.abhi.string.model;

public class RemoveDuplicateWords {

    public static void removeDuplicates(String sentence) {

        String[] words = sentence.split(" ");

        System.out.print("Output: ");

        System.out.print(words[0]);

        for (int i = 1; i < words.length; i++) {

            if (!words[i].equals(words[i - 1])) {
                System.out.print(" " + words[i]);
            }
        }
    }
}
