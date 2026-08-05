package com.vm.GWConnector.service;

import com.vm.GWConnector.model.GWClaimAdjustorResponse;
import com.vm.GWConnector.model.GWClaimAdjustorSingleResponse;
import com.vm.GWConnector.model.GWClaimAdjustorUsersResponse;

public interface ClaimAdjustorService {

    GWClaimAdjustorResponse getClaimAdjustors(int pageSize);

    GWClaimAdjustorSingleResponse getClaimAdjustorById(String groupId);

    GWClaimAdjustorUsersResponse getClaimAdjustorUsers(String groupId, int pageSize);

}
