package com.abhi.exceptionhandling.throwandthrows;

public class FinalBlock {

    public static void main(String[] args) {
        System.out.println(magicOfFinally());
    }

    public static int magicOfFinally(){
        try {
           System.out.println("try block is executed");
            return 777;
        } catch(ArithmeticException e) {
            System.out.println("catch block is executed");
            return 888;
        } finally{
           System.out.println("Finally block is executed");
            return 999;
        }
    }
}
