package com.vm.GWConnector.service;

import com.vm.GWConnector.model.DraftClaimRequestDTO;
import com.vm.GWConnector.model.GWClaimSubmitResponse;

public interface FnolProcessService {

    GWClaimSubmitResponse processFnol(DraftClaimRequestDTO request);

}
