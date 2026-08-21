package com.vm.GWConnector.service;

import com.vm.GWConnector.model.ClaimDetailsByPolicyNumber;
import com.vm.GWConnector.model.ClaimAssignmentRequestDTO;
import com.vm.GWConnector.model.ClaimAssignmentResponseDTO;
import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.ClaimResponseDTO;
import com.vm.GWConnector.model.CreateClaimRequest;
import com.vm.GWConnector.model.CreateClaimResponse;
import com.vm.GWConnector.model.DraftClaimRequestDTO;
import com.vm.GWConnector.model.DraftClaimResponseDTO;
import com.vm.GWConnector.model.ClaimSubmissionRequestDTO;
import com.vm.GWConnector.model.ClaimSubmissionResponseDTO;
import com.vm.GWConnector.model.GWClaimActivityAssigneesResponse;
import com.vm.GWConnector.model.GWClaimResponse;
import com.vm.GWConnector.model.GWClaimSubmitResponse;

import java.util.List;

public interface ClaimService {

    GWClaimResponse getClaim(String claimNumber);

    GWClaimResponse getClaimById(String claimId);

    GWClaimSubmitResponse getClaimDetailsById(String claimId);

    List<ClaimResponseDTO> getClaimsByPolicyNumber(String policyNumber);

    CreateClaimResponse createClaim(CreateClaimRequest request);

    GWClaimActivityAssigneesResponse getClaimActivityAssignees(String claimId);

    ClaimAssignmentResponseDTO assignClaim(ClaimAssignmentRequestDTO request);

    DraftClaimResponseDTO createDraftClaim(DraftClaimRequestDTO request);

    ClaimSubmissionResponseDTO submitClaim(ClaimSubmissionRequestDTO request);
}
