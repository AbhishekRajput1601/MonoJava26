package com.abhi.stringandtypecasting;

public class StringImmutabilityProgram {

    public static void main(String[] args) {

        String original = "Java";

        printReference(original);

        original = modifyString(original);

        printReference(original);
    }

    private static String modifyString(String str) {

        System.out.println("Before concat: " + str);

        str = str.concat(" Programming");

        System.out.println("After concat: " + str);

        return str;
    }

    private static void printReference(String str) {
        System.out.println("Value: " + str);
        System.out.println("HashCode: " + str.hashCode());
        System.out.println("----------------------");
    }
}
