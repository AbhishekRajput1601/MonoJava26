package com.abhi.string.model;

public class FirstNonRepeatingCharacter {

    public static void findCharacter(String str) {

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            boolean unique = true;

            for (int j = 0; j < str.length(); j++) {

                if (i != j && ch == str.charAt(j)) {
                    unique = false;
                    break;
                }
            }

            if (unique) {
                System.out.println("First Non-Repeating Character: " + ch);
                return;
            }
        }

        System.out.println("No unique character found.");
    }
}