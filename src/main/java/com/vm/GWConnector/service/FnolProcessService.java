package com.vm.GWConnector.service;

import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.FnolProcessRequest;

public interface FnolProcessService {

    ClaimResponse processFnol(FnolProcessRequest request);

}
