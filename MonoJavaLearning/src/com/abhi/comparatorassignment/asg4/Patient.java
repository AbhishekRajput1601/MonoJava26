package com.abhi.comparatorassignment.asg4;

public abstract class Patient implements Comparable<Patient> {

    protected String patientId;
    protected String name;
    protected int age;
    protected String department;

    public Patient(String patientId, String name, int age, String department) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDepartment() {
        return department;
    }

    public abstract void process();

    @Override
    public int compareTo(Patient other) {
        return this.patientId.compareTo(other.patientId);
    }
}