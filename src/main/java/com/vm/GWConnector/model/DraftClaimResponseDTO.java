package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class DraftClaimResponseDTO {
    private String claimId;
    private String claimNumber;
    private String policyNumber;
    private String claimStatus;
    private String assignmentStatus;
    private String description;
    private String lossDate;
    private GWDraftClaimRequest.LossLocation lossLocation;
}
