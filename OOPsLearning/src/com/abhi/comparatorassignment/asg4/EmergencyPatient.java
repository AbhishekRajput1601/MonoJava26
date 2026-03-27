package com.abhi.comparatorassignment.asg4;

public class EmergencyPatient extends Patient {

    private int severityLevel;

    public EmergencyPatient(String patientId, String name, int age, String department, int severityLevel) {
        super(patientId, name, age, department);
        this.severityLevel = severityLevel;
    }

    @Override
    public void process() {
        System.out.println("Emergency patient treated: " + patientId);
    }
}