package com.vm.GWConnector.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FnolProcessRequest {

    @NotBlank
    private String policyNumber;

    private String claimNumber;

    @Valid
    private CreateClaimRequest createClaimRequest;
}
