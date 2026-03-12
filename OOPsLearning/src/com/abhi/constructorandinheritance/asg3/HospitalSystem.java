package com.abhi.constructorandinheritance.asg3;

class HospitalSystem {

    private Staff[] staffMembers;
    private int totalStaff;

    public HospitalSystem(int capacity) {
        staffMembers = new Staff[capacity];
        totalStaff = 0;
    }

    public void addStaff(Staff staff) {

        if (totalStaff < staffMembers.length) {
            staffMembers[totalStaff] = staff;
            totalStaff++;
        } else {
            System.out.println("Staff storage full");
        }
    }

    public void displayAllStaff() {

        if (totalStaff == 0) {
            System.out.println("No staff records");
            return;
        }

        for (int index = 0; index < totalStaff; index++) {
            staffMembers[index].displayDetails();
            System.out.println("-----------------------------");
        }
    }

    public void processStaff() {

        for (int index = 0; index < totalStaff; index++) {

            if (staffMembers[index] instanceof Doctor) {
                ((Doctor) staffMembers[index]).treatPatient();
            }

            if (staffMembers[index] instanceof Nurse) {
                ((Nurse) staffMembers[index]).assistDoctor();
            }
        }
    }
}
