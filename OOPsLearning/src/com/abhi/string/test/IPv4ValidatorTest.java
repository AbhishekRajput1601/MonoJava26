package com.abhi.string.test;

import com.abhi.string.model.IPv4Validator;

import java.util.Scanner;

public class IPv4ValidatorTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter IP Address: ");
        String ip = sc.nextLine();

        IPv4Validator.validateIP(ip);
    }
}
