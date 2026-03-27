import java.util.Scanner;

abstract class Animal {
    abstract void sound();
//    void makeSound() {
//        System.out.println("Animal");
//    }
}

class Lion extends Animal {
    public void sound(){
        System.out.println("make sound");
    }

    void makeSound() {
        System.out.println("Lion");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal obj = new Lion();// Parent reference, Child object
//        obj.makeSound();          // Calls Dog's version

        ((Lion) obj).makeSound();

    }
}