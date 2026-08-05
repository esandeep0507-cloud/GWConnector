package com.vm.GWConnector.service;

import com.vm.GWConnector.mapper.ClaimMapper;
import com.vm.GWConnector.mapper.ClaimActivityAssigneesMapper;
import com.vm.GWConnector.model.*;
import com.vm.GWConnector.exception.ClaimServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final RestTemplate restTemplate;
    private final ClaimActivityAssigneesMapper claimActivityAssigneesMapper;

    @Value("${claim-api.url}")
    private String baseUrl;

    @Value("${policy-api.username}")
    private String username;

    @Value("${policy-api.password}")
    private String password;

    @Override
    public GWClaimResponse getClaim(String claimNumber) {

        String url =
                baseUrl +
                "/rest/claim/v1/claims/claimNumber/" +
                claimNumber;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            log.info("Fetching claim from GW: claimNumber={}", claimNumber);
            ResponseEntity<GWClaimResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            GWClaimResponse.class);
            log.info("Received claim response: claimNumber={} status={} body={}",
                    claimNumber,
                    response.getStatusCode(),
                    response.getBody());
            return response.getBody();
        } catch (RestClientException e) {
            log.error("Error fetching claim from GW: claimNumber={}", claimNumber, e);
            throw new ClaimServiceException("Failed to fetch claim from GW", e);
        }
    }

    public List<ClaimResponseDTO> getClaimsByPolicyNumber(String policyNumber) {

        String url = String.format(
                "%s/rest/claim/v1/claims/policyNumber/%s",
                baseUrl,
                policyNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            log.info("Fetching claims by policy number from GW: policyNumber={}", policyNumber);
            ResponseEntity<ClaimDetailsByPolicyNumber[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ClaimDetailsByPolicyNumber[].class);
            log.info("Received claims by policy response: policyNumber={} status={} count={}",
                    policyNumber,
                    response.getStatusCode(),
                    response.getBody() != null ? response.getBody().length : 0);
            return Arrays.stream(response.getBody())
                    .map(ClaimMapper::toResponse)
                    .toList();
        } catch (RestClientException e) {
            log.error("Error fetching claims by policy number from GW: policyNumber={}", policyNumber, e);
            throw new ClaimServiceException("Failed to fetch claims by policy number from GW", e);
        }
    }

    @Override
    public GWClaimActivityAssigneesResponse getClaimActivityAssignees(String claimId) {
        String url = String.format("%s/rest/claim/v1/claims/%s/activity-assignees", baseUrl, claimId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            log.info("Fetching claim activity assignees from GW: claimId={}", claimId);
            ResponseEntity<GWClaimActivityAssigneesResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    GWClaimActivityAssigneesResponse.class);
            log.info("Received claim activity assignees response: claimId={} status={} bodyPresent={}",
                    claimId,
                    response.getStatusCode(),
                    response.getBody() != null);
            return claimActivityAssigneesMapper.mapToResponse(response.getBody());
        } catch (RestClientException e) {
            log.error("Error fetching claim activity assignees from GW: claimId={}", claimId, e);
            throw new ClaimServiceException("Failed to fetch claim activity assignees from GW", e);
        }
    }

    @Override
    public CreateClaimResponse createClaim(CreateClaimRequest request) {

        String url = baseUrl + "/rest/composite/v1/composite";


        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        CompositeCreateClaimRequest compositeRequest = buildCompositeRequest(request);

        HttpEntity<CompositeCreateClaimRequest> entity =
                new HttpEntity<>(compositeRequest, headers);

        try {
            log.info("Creating claim in GW composite API: request={}", compositeRequest);
            ResponseEntity<CompositeCreateClaimResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    CompositeCreateClaimResponse.class);
            log.info("Received composite create response: status={} body={}",
                    response.getStatusCode(),
                    response.getBody());
            return mapToCreateClaimResponse(response.getBody());
        } catch (RestClientException e) {
            log.error("Error creating claim in GW composite API", e);
            throw new ClaimServiceException("Failed to create claim in GW", e);
        }
    }

    private CompositeCreateClaimRequest buildCompositeRequest(CreateClaimRequest request) {
        CompositeCreateClaimRequest.CompositeRequest policyRequest = new CompositeCreateClaimRequest.CompositeRequest();
        policyRequest.setMethod("post");
        policyRequest.setUri("/claim/v1/unverified-policies");

        CompositeCreateClaimRequest.CompositeRequestAttributes policyAttributes = new CompositeCreateClaimRequest.CompositeRequestAttributes();
        policyAttributes.setPolicyNumber(request.getPolicyNumber());
        GWCodeName policyType = new GWCodeName();
        policyType.setCode(request.getPolicyTypeCode() != null ? request.getPolicyTypeCode() : "BusinessAuto");
        policyAttributes.setPolicyType(policyType);
        policyAttributes.setEffectiveDate(request.getEffectiveDate());
        policyAttributes.setExpirationDate(request.getExpirationDate());
        GWCodeName currency = new GWCodeName();
        currency.setCode(request.getCurrencyCode() != null ? request.getCurrencyCode() : "usd");
        policyAttributes.setCurrency(currency);

        CompositeCreateClaimRequest.CompositeRequestBody policyBody = new CompositeCreateClaimRequest.CompositeRequestBody();
        policyBody.setData(new CompositeCreateClaimRequest.CompositeRequestData(policyAttributes));
        policyRequest.setBody(policyBody);
        policyRequest.setVars(List.of(new CompositeCreateClaimRequest.CompositeVar("uvNum", "$.data.attributes.policyNumber")));

        CompositeCreateClaimRequest.CompositeRequest claimRequest = new CompositeCreateClaimRequest.CompositeRequest();
        claimRequest.setMethod("post");
        claimRequest.setUri("/claim/v1/claims");

        CompositeCreateClaimRequest.CompositeRequestAttributes claimAttributes = new CompositeCreateClaimRequest.CompositeRequestAttributes();
        claimAttributes.setPolicyNumber("${uvNum}");
        claimAttributes.setLossDate(request.getLossDate());
        GWCodeName lossCause = new GWCodeName();
        lossCause.setCode(request.getLossCauseCode() != null ? request.getLossCauseCode() : "otherobjcoll");
        claimAttributes.setLossCause(lossCause);

        if (request.getLossLocation() != null) {
            CompositeCreateClaimRequest.CompositeLossLocation lossLocation = new CompositeCreateClaimRequest.CompositeLossLocation();
            lossLocation.setAddressLine1(request.getLossLocation().getAddressLine1());
            lossLocation.setCity(request.getLossLocation().getCity());
            GWState state = new GWState();
            state.setCode(request.getLossLocation().getStateCode());
            lossLocation.setState(state);
            lossLocation.setPostalCode(request.getLossLocation().getPostalCode());
            lossLocation.setCountry(request.getLossLocation().getCountry());
            claimAttributes.setLossLocation(lossLocation);
        }

        claimAttributes.setDescription(request.getDescription());

        CompositeCreateClaimRequest.CompositeRequestBody claimBody = new CompositeCreateClaimRequest.CompositeRequestBody();
        claimBody.setData(new CompositeCreateClaimRequest.CompositeRequestData(claimAttributes));
        claimRequest.setBody(claimBody);
        claimRequest.setVars(List.of(
                new CompositeCreateClaimRequest.CompositeVar("claimId", "$.data.attributes.id"),
                new CompositeCreateClaimRequest.CompositeVar("claimNumber", "$.data.attributes.claimNumber")
        ));

        return new CompositeCreateClaimRequest(List.of(policyRequest, claimRequest));
    }

    private CreateClaimResponse mapToCreateClaimResponse(CompositeCreateClaimResponse compositeResponse) {
        if (compositeResponse == null || compositeResponse.getResponses() == null) {
            log.error("Composite create claim response is empty or invalid");
            throw new ClaimServiceException("Invalid response returned from GW composite create claim API");
        }

        CreateClaimResponse result = new CreateClaimResponse();
        boolean found = compositeResponse.getResponses().stream()
                .filter(resp -> resp.getBody() != null && resp.getBody().getData() != null)
                .map(CompositeCreateClaimResponse.CompositeResponse::getBody)
                .map(CompositeCreateClaimResponse.CompositeResponseBody::getData)
                .peek(data -> log.debug("Composite response data node={}", data))
                .map(data -> data.getAttributes())
                .reduce((first, second) -> second)
                .map(attributes -> {
                    result.setClaimId(attributes.getId());
                    result.setClaimNumber(attributes.getClaimNumber());
                    result.setPolicyNumber(attributes.getPolicyNumber());
                    result.setStatus("CREATED");
                    return true;
                })
                .orElse(false);

        if (!found) {
            log.error("No claim attributes were found in composite create claim response");
            throw new ClaimServiceException("GW composite create claim response did not contain claim details");
        }

        return result;
    }
}