package com.vm.GWConnector.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClaimAssignmentRequestDTO {

    @NotBlank
    private String claimId;

    @NotBlank
    private String groupId;

    @NotBlank
    private String userId;
}
