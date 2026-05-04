package com.code;

public class User {
    private int id;
    private String name;
    private int age;
    private String branch;
    private int marks;

    public User(int id, String name, int age, String branch, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.marks = marks;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getBranch() {
        return branch;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", branch='" + branch + '\'' +
                ", marks=" + marks +
                '}';
    }
}
