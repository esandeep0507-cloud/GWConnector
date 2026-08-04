package com.vm.GWConnector.service;

import com.vm.GWConnector.config.RestTemplateConfig;
import com.vm.GWConnector.model.GWPolicySearchRequest;
import com.vm.GWConnector.model.GWPolicySearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService{


   // private final RestTemplateConfig config;

    private final RestTemplate restTemplate;

    @Value("${policy-api.url}")
    private String apiUrl;

    @Value("${policy-api.username}")
    private String username;

    @Value("${policy-api.password}")
    private String password;

    public GWPolicySearchResponse searchPolicies(
            GWPolicySearchRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(username, password);

        HttpEntity<GWPolicySearchRequest> httpEntity =
                new HttpEntity<>(request, headers);
        log.info("Sending request to Policy API: URL {} request {}", apiUrl,request);
        ResponseEntity<GWPolicySearchResponse> response =
                restTemplate.exchange(
                        apiUrl,
                        HttpMethod.POST,
                        httpEntity,
                        GWPolicySearchResponse.class
                );
        log.info("Received response from Policy API: {}", response.getBody());
        return response.getBody();
    }

    public GWPolicySearchRequest buildRequest(String policyNumber) {

        GWPolicySearchRequest.Attributes attributes =
                new GWPolicySearchRequest.Attributes();
        attributes.setPolicyNumber(policyNumber);

        GWPolicySearchRequest.DataNode dataNode =
                new GWPolicySearchRequest.DataNode();
        dataNode.setAttributes(attributes);

        GWPolicySearchRequest request =
                new GWPolicySearchRequest();
        request.setData(dataNode);

        return request;
    }
}
