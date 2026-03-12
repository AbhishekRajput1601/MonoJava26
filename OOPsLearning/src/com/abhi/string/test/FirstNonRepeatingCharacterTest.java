package com.abhi.string.test;

import com.abhi.string.model.FirstNonRepeatingCharacter;

import java.util.Scanner;

public class FirstNonRepeatingCharacterTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        FirstNonRepeatingCharacter.findCharacter(str);
    }
}