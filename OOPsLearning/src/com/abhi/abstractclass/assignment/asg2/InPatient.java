package com.abhi.abstractclass.assignment.asg2;

public class InPatient extends Patient{

    private final double roomCharge;

    public InPatient(int patientId, String name, double roomCharge) {
        super(patientId, name);
        this.roomCharge = roomCharge;
    }

    @Override
    protected void calculateCharges() {
        charges = roomCharge;
    }
}
