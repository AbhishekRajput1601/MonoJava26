package com.abhi.exceptionhandling.exceptionprapogation;

class NullPointerDemoWithoutTryCatch {

    void displayLength() {
        String name = null;
        System.out.println(name.length());
    }

    void process() {
        displayLength();
    }

    public static void main(String[] args) {
        NullPointerDemoWithoutTryCatch demo = new NullPointerDemoWithoutTryCatch();
        demo.process();
    }
}