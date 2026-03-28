package com.abhi.accessmodifiers;

class Parent {

    private int privateVar = 10;
    int defaultVar = 20;           // default
    protected int protectedVar = 30;
    public int publicVar = 40;

    private void privateMethod() {
        System.out.println("Private Method");
    }

    void defaultMethod() {
        System.out.println("Default Method");
    }

    protected void protectedMethod() {
        System.out.println("Protected Method");
    }

    public void publicMethod() {
        System.out.println("Public Method");
    }

    public void accessInsideClass() {
        System.out.println("Inside Parent Class:");
        System.out.println(privateVar);
        System.out.println(defaultVar);
        System.out.println(protectedVar);
        System.out.println(publicVar);

        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }
}

class Child extends Parent {

    public void accessInChild() {

        System.out.println("\nInside Child Class:");

        // privateVar  not accessible
        // privateMethod()  not accessible

        System.out.println(defaultVar);     // ✔ accessible (same package)
        System.out.println(protectedVar);   // ✔ accessible
        System.out.println(publicVar);      // ✔ accessible

        defaultMethod();
        protectedMethod();
        publicMethod();
    }
}

public class AccessModifierDemo {

    public static void main(String[] args) {

        Parent p = new Parent();
        Child c = new Child();

        System.out.println("From Main Class:");

        // System.out.println(p.privateVar);  not accessible

        System.out.println(p.defaultVar);     // ✔ (same package)
        System.out.println(p.protectedVar);   // ✔
        System.out.println(p.publicVar);      // ✔

        p.defaultMethod();
        p.protectedMethod();
        p.publicMethod();

        p.accessInsideClass();

        c.accessInChild();
    }
}
