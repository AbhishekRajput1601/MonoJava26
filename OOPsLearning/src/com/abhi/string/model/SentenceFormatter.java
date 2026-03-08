package com.abhi.string.model;

public class SentenceFormatter {

    public static void formatSentence(String sentence) {

        sentence = sentence.trim();
        sentence = sentence.toLowerCase();

        sentence = Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);

        sentence = sentence.replace("fun", "interesting");

        String[] words = sentence.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        System.out.println("Formatted Sentence: " + sentence);
        System.out.println("Total words: " + words.length);
        System.out.println("First word: " + firstWord);
        System.out.println("Last word: " + lastWord);
    }
}