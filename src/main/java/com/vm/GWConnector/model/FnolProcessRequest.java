package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FnolProcessRequest {

    private String policyNumber;
    private String claimNumber;
    private String policyType;
    private String policyEffectiveDate;
    private String policyExpirationDate;
    private String lineOfBusiness;
    private String claimScenario;
    private String dateOfLoss;
    private String timeOfLoss;
    private String reportedDate;

    private InsuredDto insured;

    private String claimsStatus;
    private String claimComplexity;
    private String initialReservedAmount;
    private String claimCostFirstParty;
    private String claimCostThirdParty;
    private String coverageLimits;
    private String catastropheFlag;
    private String claimsIn90Days;
    private String claimsIn1Year;
    private String claimsIn2Years;
    private String claimsIn3Years;
    private String weather;
    private String environmentalConditions;

    private LossLocationDto lossLocation;

    private String lossDescription;
    private String lossCause;
    private String faultRating;
    private String insuredLiabilityPercentage;

    private DriverDto driver;

    private InsuredVehicleDto insuredVehicle;

    private String collisionInvolved;

    private FirstPartyInjuryDto firstPartyInjury;

    private GeneralInjuryDto generalInjury;

    private DamageDto damage;

    private ThirdPartyDto thirdParty;

    private List<PassengerDto> passengers;

    private List<PedestrianDto> pedestrians;

    private WitnessDto witness;

    private PoliceDto police;

    private ExposureDto exposure;

    private AdjusterDto adjuster;

    private PaymentDto payment;

    private FinancialsDto financials;

    private InvestigationDto investigation;

    private LitigationDto litigation;

    private ClaimLifecycleDto claimLifecycle;

    private List<DocumentDto> documents;

    private EmailMetaDataDto emailMetaData;

    private String notes;
}