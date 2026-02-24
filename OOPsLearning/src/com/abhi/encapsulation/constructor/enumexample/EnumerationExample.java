package com.abhi.encapsulation.constructor.enumexample;

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationExample {

    public static void main(String[] args) {

        Vector<String> list = new Vector<>();

        list.add("Java");
        list.add("Python");
        list.add("C++");

        Enumeration<String> e = list.elements();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }

        System.out.println("Iteration is completed");
    }
}
