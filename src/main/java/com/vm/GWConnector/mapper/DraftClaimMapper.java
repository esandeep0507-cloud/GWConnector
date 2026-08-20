package com.vm.GWConnector.mapper;

import com.vm.GWConnector.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DraftClaimMapper {
    private static final String REPORTER_REF_ID = "reporterId";

    public GWDraftClaimRequest mapToGWRequest(DraftClaimRequestDTO request, PolicyResponse policy) {
        GWDraftClaimRequest.Attributes attributes = new GWDraftClaimRequest.Attributes();
        attributes.setPolicyNumber(policy.getPolicyNumber());
        attributes.setDescription(request.getDescription() != null ? request.getDescription() : "Draft claim for policy " + policy.getPolicyNumber());
        attributes.setLossDate(request.getLossDate());
        attributes.setLossCause(code(request.getLossCause().getCode(), request.getLossCause().getName()));
        attributes.setFaultRating(code(request.getFaultRatingCode()));
        attributes.setReportedByType(code(request.getReportedByTypeCode()));
        attributes.setLossLocation(mapLossLocation(request.getLossLocation()));
        GWDraftClaimRequest.Reference reporter = new GWDraftClaimRequest.Reference();
        reporter.setRefid(REPORTER_REF_ID);
        attributes.setReporter(reporter);

        GWDraftClaimRequest.DataNode data = new GWDraftClaimRequest.DataNode();
        data.setAttributes(attributes);
        GWDraftClaimRequest result = new GWDraftClaimRequest();
        result.setData(data);
        result.setIncluded(included(policy.getInsuredName()));
        return result;
    }

    public DraftClaimResponseDTO mapToResponse(GWDraftClaimResponse response) {
        if (response == null || response.getData() == null || response.getData().getAttributes() == null) return null;
        GWDraftClaimResponse.Attributes attributes = response.getData().getAttributes();
        DraftClaimResponseDTO result = new DraftClaimResponseDTO();
        result.setClaimId(attributes.getId());
        result.setClaimNumber(attributes.getClaimNumber());
        result.setPolicyNumber(attributes.getPolicyNumber());
        result.setClaimStatus(attributes.getClaimStatus() != null ? attributes.getClaimStatus() : attributes.getState() != null ? attributes.getState().getName() : null);
        result.setAssignmentStatus(attributes.getAssignmentStatus() != null ? attributes.getAssignmentStatus().getName() : null);
        result.setDescription(attributes.getDescription());
        result.setLossDate(attributes.getLossDate());
        result.setLossLocation(attributes.getLossLocation());
        return result;
    }

    public DraftClaimResponseDTO mapToResponse(GWClaimResponse response) {
        if (response == null) return null;
        DraftClaimResponseDTO result = new DraftClaimResponseDTO();
        result.setClaimId(response.getId());
        result.setClaimNumber(response.getClaimNumber());
        result.setPolicyNumber(response.getPolicyNumber());
        result.setClaimStatus(response.getState() != null ? response.getState().getName() : null);
        result.setAssignmentStatus(response.getAssignmentStatus() != null ? response.getAssignmentStatus().getName() : null);
        result.setDescription(response.getDescription());
        result.setLossDate(response.getLossDate());
        result.setLossLocation(mapLossLocation(response.getLossLocation()));
        return result;
    }

    private GWDraftClaimRequest.LossLocation mapLossLocation(DraftClaimRequestDTO.LossLocationDTO source) {
        GWDraftClaimRequest.LossLocation result = new GWDraftClaimRequest.LossLocation();
        result.setAddressLine1(source.getAddressLine1()); result.setCity(source.getCity()); result.setCountry(source.getCountry());
        result.setPostalCode(source.getPostalCode()); result.setPolicyLabel(source.getPolicyLabel());
        GWCodeName state = code(source.getStateCode()); state.setName(source.getStateName()); result.setState(state);
        return result;
    }

    private GWDraftClaimRequest.LossLocation mapLossLocation(GWLossLocation source) {
        if (source == null) return null;
        GWDraftClaimRequest.LossLocation result = new GWDraftClaimRequest.LossLocation();
        result.setAddressLine1(source.getAddressLine1()); result.setCity(source.getCity()); result.setCountry(source.getCountry());
        result.setPostalCode(source.getPostalCode());
        if (source.getState() != null) result.setState(code(source.getState().getCode(), source.getState().getName()));
        return result;
    }

    private GWDraftClaimRequest.Included included(String insuredName) {
        String[] names = insuredName == null || insuredName.isBlank() ? new String[]{"System", "User"} : insuredName.trim().split("\\s+", 2);
        GWDraftClaimRequest.ContactAttributes attributes = new GWDraftClaimRequest.ContactAttributes();
        attributes.setFirstName(names[0]); attributes.setLastName(names.length > 1 ? names[1] : "User"); attributes.setContactSubtype("Person");
        GWDraftClaimRequest.ClaimContact contact = new GWDraftClaimRequest.ClaimContact();
        contact.setAttributes(attributes); contact.setMethod("post"); contact.setRefid(REPORTER_REF_ID); contact.setUri("/claim/v1/claims/this/contacts");
        GWDraftClaimRequest.Included included = new GWDraftClaimRequest.Included(); included.setClaimContact(List.of(contact));
        return included;
    }

    private GWCodeName code(String value) { return code(value, null); }

    private GWCodeName code(String value, String name) {
        GWCodeName code = new GWCodeName();
        code.setCode(value);
        code.setName(name);
        return code;
    }
}
