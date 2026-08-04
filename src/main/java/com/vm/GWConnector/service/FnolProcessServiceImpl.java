package com.vm.GWConnector.service;

import com.vm.GWConnector.exception.ClaimServiceException;
import com.vm.GWConnector.mapper.ClaimMapper;
import com.vm.GWConnector.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FnolProcessServiceImpl implements FnolProcessService {

    private final ClaimService claimService;
    private final PolicyService policyService;
    private final ClaimMapper claimMapper;

    @Override
    public ClaimResponse processFnol(FnolProcessRequest request) {
        log.info("Processing FNOL for policy={} claim={}", request.getPolicyNumber(), request.getClaimNumber());

        if (StringUtils.hasText(request.getClaimNumber())) {
            GWClaimResponse gwClaim = claimService.getClaim(request.getClaimNumber());
            return claimMapper.mapToClaimResponse(gwClaim);
        }

        // Fetch policy details
        GWPolicySearchRequest policyReq = policyService.buildRequest(request.getPolicyNumber());
        GWPolicySearchResponse policyResp = policyService.searchPolicies(policyReq);

        if (policyResp == null || policyResp.getData() == null || policyResp.getData().isEmpty()) {
            log.warn("No policy data found for policyNumber={}", request.getPolicyNumber());
            throw new ClaimServiceException("Policy not found: " + request.getPolicyNumber());
        }

        // Build create claim request (use provided payload if present)
        CreateClaimRequest createReq = request.getCreateClaimRequest();
        if (createReq == null) {
            GWPolicySearchResponse.PolicyAttributes attrs = policyResp.getData().get(0).getAttributes();
            createReq = new CreateClaimRequest();
            createReq.setPolicyNumber(request.getPolicyNumber());
            createReq.setEffectiveDate(attrs != null && attrs.getEffectiveDate() != null ? attrs.getEffectiveDate() : LocalDate.now().toString());
            createReq.setExpirationDate(attrs != null && attrs.getExpirationDate() != null ? attrs.getExpirationDate() : LocalDate.now().plusYears(1).toString());
            // choose lossDate within policy coverage window to satisfy GW validation
            String lossDateStr = LocalDate.now().toString();
            try {
                LocalDate eff = attrs != null && attrs.getEffectiveDate() != null ? LocalDate.parse(attrs.getEffectiveDate().substring(0, 10)) : null;
                LocalDate exp = attrs != null && attrs.getExpirationDate() != null ? LocalDate.parse(attrs.getExpirationDate().substring(0, 10)) : null;
                LocalDate today = LocalDate.now();
                if (eff != null && exp != null) {
                    if ((!today.isBefore(eff)) && (!today.isAfter(exp))) {
                        lossDateStr = today.toString();
                    } else if (today.isBefore(eff)) {
                        lossDateStr = eff.toString();
                    } else {
                        lossDateStr = exp.minusDays(1).toString();
                    }
                }
            } catch (DateTimeParseException ex) {
                // fallback to today
                log.warn("Failed to parse policy dates, using today as lossDate", ex);
            }
            createReq.setLossDate(lossDateStr);
            createReq.setPolicyTypeCode("BusinessAuto");
            createReq.setCurrencyCode("usd");
            createReq.setLossCauseCode("otherobjcoll");
            createReq.setDescription("FNOL created via API");

            CreateClaimRequest.LossLocation loc = new CreateClaimRequest.LossLocation();
            // populate loss location from policy address when available
            String policyAddr = attrs != null ? attrs.getPolicyAddress() : null;
            String insuredName = attrs != null ? attrs.getInsuredName() : null;
            String addrLine1 = "Unknown";
            String city = "Unknown";
            String state = "CA";
            String postal = "00000";

            if (policyAddr != null && !policyAddr.isBlank()) {
                String[] lines = policyAddr.split("\\r?\\n");
                addrLine1 = lines.length > 0 ? lines[0].trim() : addrLine1;
                if (lines.length > 1) {
                    String second = lines[1].trim();
                    String[] parts = second.split(",");
                    if (parts.length >= 1) {
                        city = parts[0].trim();
                    }
                    if (parts.length >= 2) {
                        String rest = parts[1].trim();
                        String[] restParts = rest.split("\\s+");
                        if (restParts.length >= 1) state = restParts[0];
                        if (restParts.length >= 2) postal = restParts[1];
                    }
                } else {
                    // try parse single-line address with commas
                    String[] parts = addrLine1.split(",");
                    if (parts.length >= 3) {
                        city = parts[1].trim();
                        String last = parts[2].trim();
                        String[] lastParts = last.split("\\s+");
                        if (lastParts.length >= 1) state = lastParts[0];
                        if (lastParts.length >= 2) postal = lastParts[1];
                    }
                }
            }

            loc.setAddressLine1(addrLine1);
            loc.setCity(city);
            loc.setStateCode(state);
            loc.setPostalCode(postal);
            loc.setCountry("US");

            // include insured name in description when available
            if (insuredName != null && !insuredName.isBlank()) {
                createReq.setDescription("FNOL for " + insuredName + " created via API");
            }
            createReq.setLossLocation(loc);
        }

        // Create claim
        CreateClaimResponse createResp = claimService.createClaim(createReq);

        if (createResp == null || createResp.getClaimNumber() == null) {
            log.error("Claim creation failed for policy={}", request.getPolicyNumber());
            throw new ClaimServiceException("Failed to create claim for policy: " + request.getPolicyNumber());
        }

        // Fetch created claim
        GWClaimResponse createdGw = claimService.getClaim(createResp.getClaimNumber());
        return claimMapper.mapToClaimResponse(createdGw);
    }
}
