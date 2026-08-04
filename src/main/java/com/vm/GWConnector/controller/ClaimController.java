package com.vm.GWConnector.controller;

import com.vm.GWConnector.mapper.ClaimMapper;
import com.vm.GWConnector.model.ClaimDetailsByPolicyNumber;
import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.ClaimResponseDTO;
import com.vm.GWConnector.model.CreateClaimRequest;
import com.vm.GWConnector.model.CreateClaimResponse;
import com.vm.GWConnector.service.ClaimService;
import com.vm.GWConnector.model.GWClaimResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;
    private final ClaimMapper claimMapper;

    @GetMapping("/{claimNumber}")
    public ResponseEntity<ClaimResponse> getClaim(
            @PathVariable String claimNumber) {

        log.info("Received request to get claim: {}", claimNumber);
        GWClaimResponse gwResponse =
                claimService.getClaim(claimNumber);

        ClaimResponse response =
                claimMapper.mapToClaimResponse(gwResponse);

        log.info("Returning claim response for claimNumber={}: {}", claimNumber, response);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/policy/{policyNumber}")
    public ResponseEntity<List<ClaimResponseDTO>> getClaimsByPolicyNumber(
            @PathVariable String policyNumber) {

        log.info("Received request to get claims by policy number: {}", policyNumber);
        List<ClaimResponseDTO> claims = claimService.getClaimsByPolicyNumber(policyNumber);
        log.info("Returning {} claims for policyNumber={}", claims.size(), policyNumber);
        return ResponseEntity.ok(claims);

    }

    @PostMapping
    public ResponseEntity<CreateClaimResponse> createClaim(
            @Valid @RequestBody CreateClaimRequest request) {

        log.info("Received request to create claim: {}", request);
        CreateClaimResponse response = claimService.createClaim(request);
        log.info("Returning created claim response: {}", response);
        return ResponseEntity.status(201).body(response);
    }
}