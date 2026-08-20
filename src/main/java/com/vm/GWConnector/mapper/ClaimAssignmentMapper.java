package com.vm.GWConnector.mapper;

import com.vm.GWConnector.model.ClaimAssignmentRequestDTO;
import com.vm.GWConnector.model.ClaimAssignmentResponseDTO;
import com.vm.GWConnector.model.GWClaimAssignmentRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class ClaimAssignmentMapper {

    public GWClaimAssignmentRequest mapToGWRequest(ClaimAssignmentRequestDTO request) {
        GWClaimAssignmentRequest.Attributes attributes = new GWClaimAssignmentRequest.Attributes();
        attributes.setGroupId(request.getGroupId());
        attributes.setUserId(request.getUserId());

        GWClaimAssignmentRequest.DataNode data = new GWClaimAssignmentRequest.DataNode();
        data.setAttributes(attributes);

        GWClaimAssignmentRequest gwRequest = new GWClaimAssignmentRequest();
        gwRequest.setData(data);
        return gwRequest;
    }

    public ClaimAssignmentResponseDTO mapToResponse(ClaimAssignmentRequestDTO request, HttpStatusCode status) {
        ClaimAssignmentResponseDTO response = new ClaimAssignmentResponseDTO();
        response.setClaimId(request.getClaimId());
        response.setGroupId(request.getGroupId());
        response.setUserId(request.getUserId());
        response.setStatus(status.value());
        return response;
    }
}
