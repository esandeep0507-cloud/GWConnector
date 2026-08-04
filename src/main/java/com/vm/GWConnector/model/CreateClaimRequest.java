package com.vm.GWConnector.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClaimRequest {

    @NotBlank
    private String policyNumber;

    @NotBlank
    private String effectiveDate;

    @NotBlank
    private String expirationDate;

    @NotBlank
    private String lossDate;

    @NotBlank
    private String policyTypeCode;

    @NotBlank
    private String currencyCode;

    @NotBlank
    private String lossCauseCode;

    @NotBlank
    private String description;

    @NotNull
    @Valid
    private LossLocation lossLocation;

    @Data
    public static class LossLocation {

        @NotBlank
        private String addressLine1;

        @NotBlank
        private String city;

        @NotBlank
        private String stateCode;

        @NotBlank
        private String postalCode;

        @NotBlank
        private String country;
    }
}