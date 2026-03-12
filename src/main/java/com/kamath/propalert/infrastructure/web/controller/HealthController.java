package com.kamath.propalert.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.propalert.infrastructure.web.api.HealthApi;

@RestController
public class HealthController implements HealthApi {
    @Override
    public ResponseEntity<Void> healthGet() {
        // This is a simple 200 OK response to prove the plumbing works
        return ResponseEntity.ok().build();
    }

}
