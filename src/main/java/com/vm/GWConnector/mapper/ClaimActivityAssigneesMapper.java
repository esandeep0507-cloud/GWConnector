package com.vm.GWConnector.mapper;

import com.vm.GWConnector.model.GWClaimActivityAssigneesResponse;
import org.springframework.stereotype.Component;

@Component
public class ClaimActivityAssigneesMapper {

    public GWClaimActivityAssigneesResponse mapToResponse(GWClaimActivityAssigneesResponse apiResponse) {
        return apiResponse;
    }
}
