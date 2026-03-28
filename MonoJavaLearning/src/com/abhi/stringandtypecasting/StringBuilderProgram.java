package com.abhi.stringandtypecasting;

public class StringBuilderProgram {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("Java");

        appendText(sb);
        insertText(sb);
        reverseText(sb);

        System.out.println("Final Output: " + sb);
    }

    private static void appendText(StringBuilder sb) {
        sb.append(" Programming");
    }

    private static void insertText(StringBuilder sb) {
        sb.insert(4, " Core");
    }

    private static void reverseText(StringBuilder sb) {
        sb.reverse();
    }
}
