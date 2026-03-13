package com.abhi.abstactinterfaceexception.asg2;

public abstract class HospitalService implements ServiceValidation {

    protected String serviceId;
    protected String patientName;
    protected double consultationFee;

    static {
        System.out.println("Hospital System Configuration Loaded");
    }

    public HospitalService(String serviceId, String patientName, double consultationFee) throws InvalidServiceException {

        if (consultationFee <= 0)
            throw new InvalidServiceException("Fee must be positive");

        this.serviceId = serviceId;
        this.patientName = patientName;
        this.consultationFee = consultationFee;

        System.out.println("HospitalService constructor called");
    }

    abstract double calculateTotalCost();

    public void displayService() {
        System.out.println("Service ID: " + serviceId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Consultation Fee: " + consultationFee);
    }
}