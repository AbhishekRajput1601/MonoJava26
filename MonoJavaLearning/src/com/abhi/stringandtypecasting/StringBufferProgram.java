package com.abhi.stringandtypecasting;

public class StringBufferProgram {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer("Hello");

        modifyBuffer(sb);

        System.out.println("Result: " + sb);
    }

    private static void modifyBuffer(StringBuffer sb) {

        sb.append(" World");
        System.out.println(sb);
        sb.replace(0, 5, "Hi");
        System.out.println(sb);
        sb.delete(2, 4);
    }
}

