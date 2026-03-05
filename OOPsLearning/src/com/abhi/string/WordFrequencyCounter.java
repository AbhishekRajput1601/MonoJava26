package com.abhi.string;

import java.util.Scanner;

public class WordFrequencyCounter {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        sentence = sentence.toLowerCase();

        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {

            int count = 1;

            if (words[i].equals("seen"))
                continue;

            for (int j = i + 1; j < words.length; j++) {

                if (words[i].equals(words[j])) {
                    count++;
                    words[j] = "seen";
                }
            }

            System.out.println(words[i] + " : " + count);
        }
    }
}
