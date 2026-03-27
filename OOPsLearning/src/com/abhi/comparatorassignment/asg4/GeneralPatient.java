package com.abhi.comparatorassignment.asg4;

public class GeneralPatient extends Patient {

    private String problem;

    public GeneralPatient(String patientId, String name, int age, String department, String problem) {
        super(patientId, name, age, department);
        this.problem = problem;
    }

    @Override
    public void process() {
        System.out.println("General patient treated: " + patientId);
    }
}