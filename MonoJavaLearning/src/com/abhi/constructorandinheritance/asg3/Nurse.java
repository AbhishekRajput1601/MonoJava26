package com.abhi.constructorandinheritance.asg3;

class Nurse extends Staff {

    private String shiftType;
    private String wardAssigned;

    public Nurse() {
        this(0, "Unknown", "None", "None", "None");
    }

    public Nurse(int id, String name, String department, String shiftType, String wardAssigned) {
        super(id, name, department);
        this.shiftType = shiftType;
        this.wardAssigned = wardAssigned;
    }

    public void assistDoctor() {
        System.out.println("Nurse assisting doctor");
    }

    public void displayDetails() {
        System.out.println("\nNurse Record");
        super.displayDetails();
        System.out.println("Shift Type    : " + shiftType);
        System.out.println("Ward Assigned : " + wardAssigned);
    }
}