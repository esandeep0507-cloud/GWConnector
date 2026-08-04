package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class GWClaimResponse {

    private GWCodeName assignmentStatus;
    private String claimNumber;
    private String description;
    private GWCodeName flagged;
    private String id;
    private GWCodeName lobCode;
    private GWCodeName lossCause;
    private String lossDate;
    private GWLossLocation lossLocation;
    private GWCodeName lossType;
    private String policyNumber;
    private String reportedDate;
    private GWCodeName state;
}