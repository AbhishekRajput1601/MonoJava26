package com.abhi.encapsulation.module;

public class InsuranceClaim {

    private static int claimCounter = 1000;

    private final int claimId;
    private final String policyNumber;
    private final double claimAmount;
    private double approvedAmount;

    private ClaimStatus claimStatus;

    private enum ClaimStatus {
        FILED,
        APPROVED,
        REJECTED,
        SETTLED
    }

    public InsuranceClaim(String policyNumber, double claimAmount) {

        if (policyNumber == null || policyNumber.isBlank()) {
            System.out.println("Policy number cannot be empty.");
            policyNumber = "UNKNOWN";
        }

        if (claimAmount < 0) {
            System.out.println("Claim amount cannot be negative. Setting to 0.");
            claimAmount = 0;
        }

        this.claimId = ++claimCounter;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimStatus = ClaimStatus.FILED;
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

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public String getClaimStatus() {
        return claimStatus.name();
    }

    public void approveClaim(double approvedAmount) {

        if (claimStatus == ClaimStatus.SETTLED) {
            System.out.println("Settled claims cannot be modified.");
            return;
        }

        if (claimStatus != ClaimStatus.FILED) {
            System.out.println("Only FILED claims can be approved.");
            return;
        }

        if (approvedAmount < 0 || approvedAmount > claimAmount) {
            System.out.println("Approved amount must be between 0 and claim amount.");
            return;
        }

        this.approvedAmount = approvedAmount;
        this.claimStatus = ClaimStatus.APPROVED;
        System.out.println("Claim Approved Successfully!");
    }

    public void rejectClaim() {

        if (claimStatus == ClaimStatus.SETTLED) {
            System.out.println("Settled claims cannot be modified.");
            return;
        }

        if (claimStatus != ClaimStatus.FILED) {
            System.out.println("Only FILED claims can be rejected.");
            return;
        }

        this.claimStatus = ClaimStatus.REJECTED;
        System.out.println("Claim Rejected Successfully!");
    }

    public void settleClaim() {

        if (claimStatus == ClaimStatus.SETTLED) {
            System.out.println("Settled claims cannot be modified.");
            return;
        }

        if (claimStatus != ClaimStatus.APPROVED) {
            System.out.println("Only APPROVED claims can be settled.");
            return;
        }

        this.claimStatus = ClaimStatus.SETTLED;
        System.out.println("Claim Settled Successfully!");
    }
}
