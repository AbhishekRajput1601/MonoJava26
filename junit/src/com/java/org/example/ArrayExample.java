package com.java.org.example;

public class ArrayExample {
    public int[] reverseArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }
}

