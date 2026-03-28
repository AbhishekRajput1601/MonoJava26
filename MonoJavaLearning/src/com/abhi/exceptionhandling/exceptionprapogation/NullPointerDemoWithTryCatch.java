package com.abhi.exceptionhandling.exceptionprapogation;

class NullPointerDemoWithTryCatch {

    void displayLength() {
        try {
            String name = null;
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println("String is null");
        }
    }

    public static void main(String[] args) {
        NullPointerDemoWithTryCatch obj = new NullPointerDemoWithTryCatch();
        obj.displayLength();
        System.out.println("code is not broken");
    }
}