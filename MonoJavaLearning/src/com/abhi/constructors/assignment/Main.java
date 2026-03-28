package com.abhi.constructors.assignment;

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

public class Main{
    public static void main(String[] args) {
        Child c = new Child();
    }
}


//class Parent{
//	public void supermsg() {
//		System.out.println("Parent class");
//	}
//}
//
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





//1️⃣ Child() runs
//2️⃣ It calls this(10)
//3️⃣ Child(int x) runs
//4️⃣ super() runs → Parent constructor
//5️⃣ Back to Child(int x)
//6️⃣ Back to Child()



