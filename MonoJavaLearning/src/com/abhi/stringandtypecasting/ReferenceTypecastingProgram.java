package com.abhi.stringandtypecasting;

class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barking");
    }
}

public class ReferenceTypecastingProgram {

    public static void main(String[] args) {

        Animal a = new Dog();   // Upcasting
        a.sound();

        if (a instanceof Dog) {
            Dog d = (Dog) a;   // Downcasting
            d.bark();
        }
    }
}
