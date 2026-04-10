package com.kamath.propalert.infrastructure.web.controller;

import com.kamath.propalert.application.service.LeadService;
import com.kamath.propalert.domain.context.BrokerContext;
import com.propalert.infrastructure.web.api.LeadsApi;
import com.propalert.infrastructure.web.dto.LeadRequest;
import com.propalert.infrastructure.web.dto.RegisterLead201Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LeadController implements LeadsApi {

    private final LeadService leadService;

    @Autowired
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @Override
    public ResponseEntity<RegisterLead201Response> registerLead(LeadRequest leadRequest) {
        String brokerId = BrokerContext.getBrokerId();
        if (brokerId == null) {
            // Our RFC 7807 GlobalExceptionHandler will catch this automatically
            throw new SecurityException("Unauthorized: Missing Broker Context (X-Broker-ID header required)");
        }
        // 2. Hand off to the Orchestrator
        String generatedLeadId = leadService.registerLead(leadRequest, brokerId);
        // 3. Construct the exact OpenAPI 201 Response requested by the YAML
        RegisterLead201Response response = new RegisterLead201Response();
        response.setLeadId(generatedLeadId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
