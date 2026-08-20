package com.vm.GWConnector.service;

import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.DraftClaimRequestDTO;

public interface FnolProcessService {

    ClaimResponse processFnol(DraftClaimRequestDTO request);

}
