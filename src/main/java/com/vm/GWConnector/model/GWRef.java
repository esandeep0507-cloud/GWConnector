package com.vm.GWConnector.model;

import lombok.Data;

/**
 * Generic Guidewire entity reference, used for assignedByUser, assignedGroup,
 * assignedUser, insured and reporter. {@code policySystemId} is only populated
 * on the "insured" reference; it will be null everywhere else.
 */
@Data
public class GWRef {

    private String displayName;
    private String id;
    private String type;
    private String uri;
    private String policySystemId;
}