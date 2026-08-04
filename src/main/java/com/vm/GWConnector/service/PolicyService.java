package com.vm.GWConnector.service;

import com.vm.GWConnector.model.GWPolicySearchRequest;
import com.vm.GWConnector.model.GWPolicySearchResponse;

public interface PolicyService {

    GWPolicySearchResponse searchPolicies(
            GWPolicySearchRequest request);

    GWPolicySearchRequest buildRequest(String policyNumber);
}
