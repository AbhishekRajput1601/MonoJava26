package com.abhi.abstactinterfaceexception.asg2;


public class Surgery extends HospitalService {

    public Surgery(String serviceId, String patientName, double consultationFee) throws InvalidServiceException {
        super(serviceId, patientName, consultationFee);
        System.out.println("Surgery constructor called");
    }

    double calculateTotalCost() {
        return consultationFee + 5000;
    }

    public boolean validateService() {
        return consultationFee >= 5000;
    }
}
