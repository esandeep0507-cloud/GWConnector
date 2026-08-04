package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class CreateClaimResponse {

    private String claimId;

    private String claimNumber;

    private String policyNumber;

    private String assignmentStatus;

    private String status;

}