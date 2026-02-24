package com.abhi.encapsulation.constructorAssignment.asg2;

public class CorporateClaim extends InsuranceClaim {

    private static final double PROCESSING_FEE = 500;

    public CorporateClaim(String policyNumber, double claimAmount) {
        super(policyNumber, claimAmount + PROCESSING_FEE);
    }
}
