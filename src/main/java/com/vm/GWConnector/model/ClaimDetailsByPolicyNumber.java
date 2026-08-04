package com.vm.GWConnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimDetailsByPolicyNumber {

    private TypeCode assignmentStatus;

    private String claimNumber;

    private String description;

    private TypeCode flagged;

    private String id;

    private TypeCode lobCode;

    private TypeCode lossCause;

    private String lossDate;

    private LossLocation lossLocation;

    private TypeCode lossType;

    private String policyNumber;

    private String reportedDate;

    private TypeCode state;
}