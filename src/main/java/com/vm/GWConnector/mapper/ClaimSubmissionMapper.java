package com.vm.GWConnector.mapper;

import com.vm.GWConnector.model.ClaimSubmissionRequestDTO;
import com.vm.GWConnector.model.ClaimSubmissionResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class ClaimSubmissionMapper {

    public ClaimSubmissionResponseDTO mapToResponse(ClaimSubmissionRequestDTO request, HttpStatusCode status) {
        ClaimSubmissionResponseDTO response = new ClaimSubmissionResponseDTO();
        response.setClaimId(request.getClaimId());
        response.setStatus(status.value());
        return response;
    }
}
