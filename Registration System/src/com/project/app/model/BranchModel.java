package com.project.app.model;

public class BranchModel {
    private final int branchId;
    private final String branchName;

    public BranchModel(int branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }
}

