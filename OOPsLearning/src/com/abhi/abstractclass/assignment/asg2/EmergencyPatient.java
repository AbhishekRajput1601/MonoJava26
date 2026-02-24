package com.abhi.abstractclass.assignment.asg2;

public class EmergencyPatient extends Patient{

    private final double emergencySurcharge;

    public EmergencyPatient(int patientId, String name, double emergencySurcharge) {
        super(patientId, name);
        this.emergencySurcharge = emergencySurcharge;
    }

    @Override
    protected void calculateCharges() {
        charges = emergencySurcharge;
    }
}
