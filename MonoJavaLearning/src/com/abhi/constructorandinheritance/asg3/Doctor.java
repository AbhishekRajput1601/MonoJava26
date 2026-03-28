package com.abhi.constructorandinheritance.asg3;

class Doctor extends Staff {

    private String specialization;
    private double consultationFee;

    public Doctor() {
        this(0, "Unknown", "None", "None", 0);
    }

    public Doctor(int id, String name, String department, String specialization, double consultationFee) {
        super(id, name, department);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public void treatPatient() {
        System.out.println("Doctor treating patient");
    }

    public void displayDetails() {
        System.out.println("\nDoctor Record");
        super.displayDetails();
        System.out.println("Specialization  : " + specialization);
        System.out.println("ConsultationFee : " + consultationFee);
    }
}
