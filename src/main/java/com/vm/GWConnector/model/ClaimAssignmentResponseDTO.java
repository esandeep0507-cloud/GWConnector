package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class ClaimAssignmentResponseDTO {

    private String claimId;
    private String groupId;
    private String userId;
    private int status;
}
