package com.vm.GWConnector.service;

import com.vm.GWConnector.exception.ClaimServiceException;
import com.vm.GWConnector.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class FnolProcessServiceImpl implements FnolProcessService {

    private final ClaimService claimService;
    private final ClaimAdjustorService claimAdjustorService;

    @Override
    public GWClaimSubmitResponse processFnol(DraftClaimRequestDTO request) {
        log.info("Processing FNOL for policy={} claim={}", request.getPolicyNumber(), request.getClaimNumber());

        DraftClaimResponseDTO draftClaim = claimService.createDraftClaim(request);
        if (draftClaim == null || !org.springframework.util.StringUtils.hasText(draftClaim.getClaimId())) {
            throw new ClaimServiceException("Draft-claim response did not contain a claim ID");
        }

        String assignedGroupId;
        if (request.hasClaimReference()) {
            GWClaimResponse existingClaim = claimService.getClaimById(draftClaim.getClaimId());
            assignedGroupId = existingClaim != null && existingClaim.getAssignedGroup() != null
                    ? existingClaim.getAssignedGroup().getId() : null;
        } else {
            ClaimSubmissionRequestDTO submissionRequest = new ClaimSubmissionRequestDTO();
            submissionRequest.setClaimId(draftClaim.getClaimId());
            ClaimSubmissionResponseDTO submittedClaim = claimService.submitClaim(submissionRequest);
            assignedGroupId = submittedClaim != null && submittedClaim.getAssignedGroup() != null
                    ? submittedClaim.getAssignedGroup().getId() : null;
        }
        if (!org.springframework.util.StringUtils.hasText(assignedGroupId)) {
            throw new ClaimServiceException("Claim details did not contain an assigned group ID");
        }

        GWClaimAdjustorUsersResponse usersResponse = claimAdjustorService.getClaimAdjustorUsers(assignedGroupId, 100);
        String userId = selectRandomUserId(usersResponse);

        ClaimAssignmentRequestDTO assignmentRequest = new ClaimAssignmentRequestDTO();
        assignmentRequest.setClaimId(draftClaim.getClaimId());
        assignmentRequest.setGroupId(assignedGroupId);
        assignmentRequest.setUserId(userId);
        claimService.assignClaim(assignmentRequest);

        return claimService.getClaimDetailsById(draftClaim.getClaimId());
    }

    private String selectRandomUserId(GWClaimAdjustorUsersResponse usersResponse) {
        List<String> userIds = usersResponse == null || usersResponse.getData() == null ? List.of()
                : usersResponse.getData().stream()
                .map(GWClaimAdjustorUsersResponse.UserData::getAttributes)
                .filter(java.util.Objects::nonNull)
                .map(GWClaimAdjustorUsersResponse.UserAttributes::getUser)
                .filter(java.util.Objects::nonNull)
                .map(GWClaimAdjustorUsersResponse.UserReference::getId)
                .filter(org.springframework.util.StringUtils::hasText)
                .toList();
        if (userIds.isEmpty()) {
            throw new ClaimServiceException("No assignable users found for group");
        }
        return userIds.get(ThreadLocalRandom.current().nextInt(userIds.size()));
    }
}
