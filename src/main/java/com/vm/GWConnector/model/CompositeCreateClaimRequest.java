package com.vm.GWConnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompositeCreateClaimRequest {

    private List<CompositeRequest> requests;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeRequest {
        private String method;
        private String uri;
        private CompositeRequestBody body;
        private List<CompositeVar> vars;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeRequestBody {
        private CompositeRequestData data;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeRequestData {
        private CompositeRequestAttributes attributes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeRequestAttributes {
        private String policyNumber;
        private GWCodeName policyType;
        private String effectiveDate;
        private String expirationDate;
        private GWCodeName currency;
        private String lossDate;
        private GWCodeName lossCause;
        private CompositeLossLocation lossLocation;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeLossLocation {
        private String addressLine1;
        private String city;
        private GWState state;
        private String postalCode;
        private String country;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompositeVar {
        private String name;
        private String path;
    }
}
