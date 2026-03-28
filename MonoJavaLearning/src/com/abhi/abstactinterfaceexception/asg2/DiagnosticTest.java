package com.abhi.abstactinterfaceexception.asg2;


public class DiagnosticTest extends HospitalService {

    public DiagnosticTest(String serviceId, String patientName, double consultationFee) throws InvalidServiceException {
        super(serviceId, patientName, consultationFee);
        System.out.println("DiagnosticTest constructor called");
    }

    double calculateTotalCost() {
        return consultationFee + 500;
    }

    public boolean validateService() {
        return consultationFee <= 3000;
    }
}
