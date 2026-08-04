package com.vm.GWConnector.service;

import com.vm.GWConnector.model.GWClaimAdjustorResponse;
import com.vm.GWConnector.model.GWClaimAdjustorSingleResponse;

public interface ClaimAdjustorService {

    GWClaimAdjustorResponse getClaimAdjustors(int pageSize);

    GWClaimAdjustorSingleResponse getClaimAdjustorById(String groupId);

}
