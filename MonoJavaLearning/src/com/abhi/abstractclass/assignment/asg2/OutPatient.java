package com.abhi.abstractclass.assignment.asg2;

public class OutPatient extends Patient{

    private final double consultationFee;

    public OutPatient(int patientId, String name, double consultationFee) {
        super(patientId, name);
        this.consultationFee = consultationFee;
    }

    @Override
    protected void calculateCharges() {
        charges = consultationFee;
    }
}
