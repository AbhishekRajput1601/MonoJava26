import java.util.Scanner;

class Animal {
    void makeSound() {
        System.out.println("Animal");
    }
}

class Lion extends Animal {
    void makeSound() {
        System.out.println("Lion");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal obj = new Lion();   // Parent reference, Child object
        obj.makeSound();          // Calls Dog's version

    }
}