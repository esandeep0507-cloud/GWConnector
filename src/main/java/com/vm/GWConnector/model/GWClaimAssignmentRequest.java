package com.vm.GWConnector.model;

import lombok.Data;

/** Request body required by Guidewire's claim assignment API. */
@Data
public class GWClaimAssignmentRequest {

    private DataNode data;

    @Data
    public static class DataNode {
        private Attributes attributes;
    }

    @Data
    public static class Attributes {
        private String groupId;
        private String userId;
    }
}
