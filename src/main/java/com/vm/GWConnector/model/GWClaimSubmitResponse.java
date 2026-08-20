package com.vm.GWConnector.model;

import lombok.Data;

/**
 * Top-level wrapper for the Guidewire claim submit response:
 * { "data": { "attributes": {...}, "checksum": "...", "links": {...} } }
 */
@Data
public class GWClaimSubmitResponse {

    private GWClaimSubmitData data;
}