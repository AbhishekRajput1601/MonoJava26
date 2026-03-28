package com.abhi.string.test;

import com.abhi.string.model.BalancedBracketChecker;

import java.util.Scanner;

public class BalancedBracketCheckerTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string with brackets: ");
        String str = sc.nextLine();

        BalancedBracketChecker.checkBrackets(str);
    }
}