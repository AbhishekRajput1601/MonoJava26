package com.abhi.constructorandinheritance.asg2;

class ScholarshipStudent extends Student {

    private double scholarshipAmount;

    public ScholarshipStudent() {
        this(0, "Unknown", "None", 0);
    }

    public ScholarshipStudent(int studentId, String studentName, String enrolledCourse, double scholarshipAmount) {
        super(studentId, studentName, enrolledCourse);
        this.scholarshipAmount = scholarshipAmount;
    }

    public void applyScholarship() {
        System.out.println("Scholarship Applied : " + scholarshipAmount);
    }

    public void displayDetails() {
        System.out.println("\nScholarship Student");
        super.displayDetails();
        System.out.println("Scholarship Amount : " + scholarshipAmount);
    }
}