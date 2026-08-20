package com.vm.GWConnector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Maps everything under "data.attributes" in the GW claim submit response.
 * "claim_number" / "policy_number" are duplicates of claimNumber/policyNumber
 * and are intentionally not mapped separately.
 */
@Data
public class GWClaimAttributes {

    private String adjuster;
    private List<GWCodeName> allValidationLevelsReached;
    private GWRef assignedByUser;
    private GWRef assignedGroup;
    private GWRef assignedUser;
    private GWCodeName assignmentStatus;
    private List<GWHistoryEvent> claimHistory;
    private String claimNumber;

    @JsonProperty("claim_status")
    private String claimStatus;

    private String description;
    private GWCodeName faultRating;
    private GWCodeName flagged;
    private String id;
    private GWRef insured;
    private GWCodeName lobCode;
    private GWCodeName lossCause;
    private String lossDate;
    private GWLossLocation lossLocation;
    private GWCodeName lossType;
    private List<GWPolicyAddress> policyAddresses;
    private String policyNumber;
    private GWCodeName reportedByType;
    private String reportedDate;
    private GWRef reporter;
    private GWCodeName segment;
    private GWCodeName state;
    private GWCodeName strategy;
    private GWCodeName validationLevel;
}