package com.kamath.propalert.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kamath.propalert.application.service.BrokerService;
import com.propalert.infrastructure.web.dto.BrokerRequest;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/brokers")
@RequiredArgsConstructor
public class BrokerController {
    private final BrokerService brokerService;

    @PostMapping
    public ResponseEntity<Map<String, String>> onboardBroker(@RequestBody BrokerRequest request) {
        // Trigger the business logic
        String brokerId = brokerService.onboardBroker(request);

        // Return 201 Created with the new Business ID
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("brokerId", brokerId));
    }
}
