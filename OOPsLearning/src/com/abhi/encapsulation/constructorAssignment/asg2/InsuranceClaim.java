package com.abhi.encapsulation.constructorAssignment.asg2;

public class InsuranceClaim {

    private static int idCounter = 1000;

    private final int claimId;
    private String policyNumber;
    private double claimAmount;
    private String status;
    private double approvedAmount;


    private InsuranceClaim() {
        claimId = 0;
    }


    public InsuranceClaim(String policyNumber, double claimAmount) {

        if (policyNumber == null || policyNumber.isBlank()) {
            System.out.println("Invalid policy number. Setting default.");
            policyNumber = "UNKNOWN";
        }

        if (claimAmount < 0) {
            System.out.println("Claim amount cannot be negative. Setting to 0.");
            claimAmount = 0;
        }

        this.claimId = ++idCounter;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.status = "FILED";
        this.approvedAmount = 0;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }
}
