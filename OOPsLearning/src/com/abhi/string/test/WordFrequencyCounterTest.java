package com.abhi.string.test;

import com.abhi.string.model.WordFrequencyCounter;

import java.util.Scanner;

public class WordFrequencyCounterTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        WordFrequencyCounter.countFrequency(sentence);
    }
}