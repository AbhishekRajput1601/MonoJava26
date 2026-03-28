package com.abhi.stringandtypecasting;

public class AutoboxingProgram {

    public static void main(String[] args) {

        int primitive = 50;

        Integer wrapper = primitive;   // Autoboxing
        System.out.println("Wrapper: " + wrapper);

        int unboxed = wrapper;         // Unboxing
        System.out.println("Primitive: " + unboxed);

        checkCaching();
    }

    private static void checkCaching() {

        Integer a = 100;
        Integer b = 100;

        System.out.println("Cached (==): " + (a == b));

        Integer x = 200;
        Integer y = 200;

        System.out.println("Not Cached (==): " + (x == y));
    }
}
