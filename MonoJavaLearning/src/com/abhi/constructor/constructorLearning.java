package com.abhi.constructor;

class Parent {

    Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {

    Child() {
        this(10);  // calls second constructor
        System.out.println("Default Child Constructor");
    }

    Child(int x) {
        super();   // calls parent constructor
        System.out.println("Parameterized Child Constructor");
    }
}

//class Parent{
//	public void supermsg() {
//		System.out.println("Parent class");
//	}
//}

//class Child extends Parent {
//
//	public void thismsg() {
//		System.out.println("Child class");
//	}
//
//	Child(){
//		super.supermsg();  // parent method
//		this.thismsg();    // current method
//	}
//}
//
//public class Main{
//	public static void main(String[] args) {
//		Child obj = new Child();
//	}
//}


public class constructorLearning{
    public static void main(String[] args) {
        Child c = new Child();
    }
}

