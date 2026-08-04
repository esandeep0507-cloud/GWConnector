package com.vm.GWConnector.mapper;


import com.vm.GWConnector.model.GWPolicySearchResponse;
import com.vm.GWConnector.model.PolicyResponse;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    public PolicyResponse mapToPolicyResponse(
            GWPolicySearchResponse gwResponse) {

        if (gwResponse == null
                || gwResponse.getData() == null
                || gwResponse.getData().isEmpty()) {
            return null;
        }

        GWPolicySearchResponse.PolicyData policyData =
                gwResponse.getData().get(0);

        GWPolicySearchResponse.PolicyAttributes attributes =
                policyData.getAttributes();

        PolicyResponse response = new PolicyResponse();

        response.setPolicyNumber(attributes.getPolicyNumber());
        response.setPolicyId(attributes.getPolicyId());
        response.setAccountNumber(attributes.getAccountNumber());
        response.setInsuredName(attributes.getInsuredName());
        response.setEffectiveDate(attributes.getEffectiveDate());
        response.setExpirationDate(attributes.getExpirationDate());
        response.setPolicyAddress(attributes.getPolicyAddress());

        if (attributes.getProduct() != null) {
            response.setProductId(attributes.getProduct().getId());
            response.setProductName(attributes.getProduct().getDisplayName());
        }

        return response;
    }
}