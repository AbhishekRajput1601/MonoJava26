package com.abhi.constructorandinheritance.asg2;

class InstituteSystem {

    private Student[] students;
    private int totalStudents;

    public InstituteSystem(int capacity) {
        students = new Student[capacity];
        totalStudents = 0;
    }

    public void addStudent(Student student) {

        if (totalStudents < students.length) {
            students[totalStudents] = student;
            totalStudents++;
        } else {
            System.out.println("Student storage full");
        }
    }

    public void displayAllStudents() {

        if (totalStudents == 0) {
            System.out.println("No student records");
            return;
        }

        for (int index = 0; index < totalStudents; index++) {
            students[index].displayDetails();
            System.out.println("---------------------------");
        }
    }

    public void processStudents() {

        for (int index = 0; index < totalStudents; index++) {

            if (students[index] instanceof RegularStudent) {
                ((RegularStudent) students[index]).checkAttendance();
            }

            if (students[index] instanceof ScholarshipStudent) {
                ((ScholarshipStudent) students[index]).applyScholarship();
            }
        }
    }
}