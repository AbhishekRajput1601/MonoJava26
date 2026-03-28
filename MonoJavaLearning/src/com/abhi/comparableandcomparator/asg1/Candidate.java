package com.abhi.comparableandcomparator.asg1;

public class Candidate {
    String name;
    int age;

    Candidate(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return name + "-" + age;
    }
}
