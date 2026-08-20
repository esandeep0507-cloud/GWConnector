package com.vm.GWConnector.mapper;

import com.vm.GWConnector.model.ClaimSubmissionRequestDTO;
import com.vm.GWConnector.model.ClaimSubmissionResponseDTO;
import com.vm.GWConnector.model.GWClaimAttributes;
import com.vm.GWConnector.model.GWClaimSubmitResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class ClaimSubmissionMapper {

    public ClaimSubmissionResponseDTO mapToResponse(
            ClaimSubmissionRequestDTO request, GWClaimSubmitResponse gwResponse, HttpStatusCode status) {

        ClaimSubmissionResponseDTO response = new ClaimSubmissionResponseDTO();
        response.setClaimId(request.getClaimId());
        response.setStatus(status.value());

        GWClaimAttributes attributes = extractAttributes(gwResponse);
        if (attributes == null) {
            return response;
        }

        response.setAdjuster(attributes.getAdjuster());
        response.setAllValidationLevelsReached(attributes.getAllValidationLevelsReached());
        response.setAssignedByUser(attributes.getAssignedByUser());
        response.setAssignedGroup(attributes.getAssignedGroup());
        response.setAssignedUser(attributes.getAssignedUser());
        response.setAssignmentStatus(attributes.getAssignmentStatus());
        response.setClaimHistory(attributes.getClaimHistory());
        response.setClaimNumber(attributes.getClaimNumber());
        response.setClaimStatus(attributes.getClaimStatus());
        response.setDescription(attributes.getDescription());
        response.setFaultRating(attributes.getFaultRating());
        response.setFlagged(attributes.getFlagged());
        response.setInsured(attributes.getInsured());
        response.setLobCode(attributes.getLobCode());
        response.setLossCause(attributes.getLossCause());
        response.setLossDate(attributes.getLossDate());
        response.setLossLocation(attributes.getLossLocation());
        response.setLossType(attributes.getLossType());
        response.setPolicyAddresses(attributes.getPolicyAddresses());
        response.setPolicyNumber(attributes.getPolicyNumber());
        response.setReportedByType(attributes.getReportedByType());
        response.setReportedDate(attributes.getReportedDate());
        response.setReporter(attributes.getReporter());
        response.setSegment(attributes.getSegment());
        response.setState(attributes.getState());
        response.setStrategy(attributes.getStrategy());
        response.setValidationLevel(attributes.getValidationLevel());

        return response;
    }

    private GWClaimAttributes extractAttributes(GWClaimSubmitResponse gwResponse) {
        if (gwResponse == null || gwResponse.getData() == null) {
            return null;
        }
        return gwResponse.getData().getAttributes();
    }
}