package com.abhi.string.model;

public class WordFrequencyCounter {

    public static void countFrequency(String sentence) {

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