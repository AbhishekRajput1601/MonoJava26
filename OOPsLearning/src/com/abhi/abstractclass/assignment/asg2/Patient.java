package com.abhi.abstractclass.assignment.asg2;

public abstract class Patient {
    private int patientId;
    private String name;
    protected double charges;

    public Patient(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.charges = 0;
    }

    public final void generateBill(){

        calculateCharges();
        double tax = calculateTax();
        double finalAmount = charges + tax;

        System.out.println("----- Bill for Patient ID: " + patientId + " -----");
        System.out.println("Patient Name: " + name);
        System.out.println("Base Charges: " + charges);
        System.out.println("Tax (10%): " + tax);
        System.out.println("Final Amount: " + finalAmount);
        System.out.println("---------------------------------------");
    }

    public double calculateTax(){
        return charges * 0.10;
    }

    protected abstract void calculateCharges();
}
