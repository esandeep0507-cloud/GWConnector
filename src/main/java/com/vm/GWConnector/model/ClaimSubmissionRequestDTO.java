package com.vm.GWConnector.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClaimSubmissionRequestDTO {

    @NotBlank
    private String claimId;
}
