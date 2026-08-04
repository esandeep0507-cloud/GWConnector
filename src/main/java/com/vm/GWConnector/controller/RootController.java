package com.vm.GWConnector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping
    public ResponseEntity<Map<String, String>> root() {
        log.info("Received request to root endpoint");
        return ResponseEntity.ok(Map.of(
                "status", "GWConnector is running",
                "version", "1.0",
                "message", "Use /api/claims, /api/policies, /api/fnol, /api/claim-adjustors"
        ));
    }
}
