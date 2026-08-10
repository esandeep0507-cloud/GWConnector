package com.vm.GWConnector.controller;

import com.vm.GWConnector.model.ClaimResponse;
import com.vm.GWConnector.model.FnolProcessRequest;
import com.vm.GWConnector.service.FnolProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/fnol")
@RequiredArgsConstructor
@Tag(name = "FNOL", description = "First notice of loss processing")
public class FnolProcessController {

    private final FnolProcessService fnolProcessService;

    @PostMapping("/process")
    @Operation(summary = "Process an FNOL", description = "Returns an existing claim when supplied, or creates a claim from policy information.")
    public ResponseEntity<ClaimResponse> processFnol(@Valid @RequestBody FnolProcessRequest request) {
        log.info("Received FNOL process request for policy={} claim={}", request.getPolicyNumber(), request.getClaimNumber());
        ClaimResponse response = fnolProcessService.processFnol(request);
        return ResponseEntity.ok(response);
    }
}
