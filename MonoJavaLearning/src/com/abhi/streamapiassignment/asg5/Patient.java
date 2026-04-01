package com.abhi.streamapiassignment.asg5;

import java.io.Serializable;

public class Patient implements Serializable {
    private int patientId;
    private String name;
    private int age;
    private String disease;
    private boolean admitted;
    private double billAmount;

    public Patient(int patientId, String name, int age, String disease, boolean admitted, double billAmount) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.admitted = admitted;
        this.billAmount = billAmount;
    }

    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }
    public boolean isAdmitted() { return admitted; }
    public double getBillAmount() { return billAmount; }

    @Override
    public String toString() {
        return "ID: " + patientId + ", Name: " + name + ", Age: " + age +
                ", Disease: " + disease + ", Admitted: " + admitted +
                ", Bill: " + billAmount;
    }
}
