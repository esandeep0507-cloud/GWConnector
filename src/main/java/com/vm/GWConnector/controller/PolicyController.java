package com.vm.GWConnector.controller;

import com.vm.GWConnector.mapper.PolicyMapper;
import com.vm.GWConnector.model.GWPolicySearchRequest;
import com.vm.GWConnector.model.GWPolicySearchResponse;
import com.vm.GWConnector.model.PolicyResponse;
import com.vm.GWConnector.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    //@Autowired
    private final PolicyService policyService;
    private final PolicyMapper policyMapper;

    @GetMapping("/{policyNumber}")
    public ResponseEntity<PolicyResponse> getPolicy(
            @PathVariable String policyNumber) {

        // Build API Request
        GWPolicySearchRequest request = policyService.buildRequest(policyNumber);

        // Call Service
        GWPolicySearchResponse response =
                policyService.searchPolicies(request);

        return ResponseEntity.ok(policyMapper.mapToPolicyResponse(response));
    }
}