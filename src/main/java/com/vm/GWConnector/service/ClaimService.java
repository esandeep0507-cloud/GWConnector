package com.vm.GWConnector.service;

import com.vm.GWConnector.model.ClaimDetailsByPolicyNumber;
import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.ClaimResponseDTO;
import com.vm.GWConnector.model.CreateClaimRequest;
import com.vm.GWConnector.model.CreateClaimResponse;
import com.vm.GWConnector.model.GWClaimResponse;

import java.util.List;

public interface ClaimService {

    GWClaimResponse getClaim(String claimNumber);

    List<ClaimResponseDTO> getClaimsByPolicyNumber(String policyNumber);

    CreateClaimResponse createClaim(CreateClaimRequest request);
}