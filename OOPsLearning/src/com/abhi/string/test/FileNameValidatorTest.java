package com.abhi.string.test;

import com.abhi.string.model.FileNameValidator;

import java.util.Scanner;

public class FileNameValidatorTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String file = sc.nextLine();

        FileNameValidator.validateFile(file);
    }
}