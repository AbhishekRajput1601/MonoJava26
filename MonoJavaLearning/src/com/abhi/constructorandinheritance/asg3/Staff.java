package com.abhi.constructorandinheritance.asg3;

class Staff {

    private int id;
    private String name;
    private String department;

    public Staff() {
        this(0, "Unknown", "None");
    }

    public Staff(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void displayDetails() {
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
    }
}