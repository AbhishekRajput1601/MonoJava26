package com.abhi.constructorandinheritance.asg2;

class RegularStudent extends Student {

    private double attendancePercentage;

    public RegularStudent() {
        this(0, "Unknown", "None", 0);
    }

    public RegularStudent(int studentId, String studentName, String enrolledCourse, double attendancePercentage) {
        super(studentId, studentName, enrolledCourse);
        this.attendancePercentage = attendancePercentage;
    }

    public void checkAttendance() {
        if (attendancePercentage < 75) {
            System.out.println("Attendance below required level");
        }
    }

    public void displayDetails() {
        System.out.println("\nRegular Student");
        super.displayDetails();
        System.out.println("Attendance : " + attendancePercentage + "%");
    }
}
