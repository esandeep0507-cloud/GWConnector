package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWClaimActivityAssigneesResponse {

    private int count;
    private List<AssigneeData> data;
    private ResponseLinks links;

    @Data
    public static class AssigneeData {
        private AssigneeAttributes attributes;
        private String checksum;
        private EntityLinks links;
    }

    @Data
    public static class AssigneeAttributes {
        private String assigneeId;
        private Boolean claimOwner;
        private Boolean autoAssign;
        private String id;
        private String name;
        private String queueId;
        private String groupId;
        private String userId;
    }

    @Data
    public static class EntityLinks {
        private Link self;
    }

    @Data
    public static class ResponseLinks {
        private Link first;
        private Link self;
    }

    @Data
    public static class Link {
        private String href;
        private List<String> methods;
    }
}
