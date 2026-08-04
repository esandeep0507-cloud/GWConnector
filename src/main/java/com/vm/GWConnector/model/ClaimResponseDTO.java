package com.vm.GWConnector.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimResponseDTO {

    private String claimNumber;
    private String policyNumber;
    private String description;
    private String lossDate;
    private String reportedDate;

    private String assignmentStatus;
    private String claimState;
    private String lob;
    private String lossCause;
    private String lossType;
}