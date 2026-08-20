package com.vm.GWConnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GWDraftClaimResponse {
    private DataNode data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataNode {
        private Attributes attributes;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attributes {
        private GWCodeName assignmentStatus;
        private String claimNumber;
        @JsonProperty("claim_status") private String claimStatus;
        private String description;
        private String id;
        private String policyNumber;
        private String lossDate;
        private GWCodeName state;
        private GWDraftClaimRequest.LossLocation lossLocation;
    }
}
