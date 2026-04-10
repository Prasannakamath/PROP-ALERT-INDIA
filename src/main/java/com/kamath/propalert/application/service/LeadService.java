package com.kamath.propalert.application.service;

import com.kamath.propalert.domain.exception.DuplicateLeadException;
import com.kamath.propalert.domain.model.Lead;
import com.kamath.propalert.infrastructure.mapping.LeadMapper;
import com.kamath.propalert.infrastructure.persistence.repository.LeadRepository;
import com.propalert.infrastructure.web.dto.LeadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadService {
    private final LeadMapper mapper;
    private final LeadRepository repository;

    @Autowired
    public LeadService(LeadMapper mapper, LeadRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public String registerLead(LeadRequest request, String brokerId){
        Lead lead = mapper.toDomainFromRequest(request);
        lead.setBrokerId(brokerId);

        // 3. Fire Pure Business Logic Rules
        if (!lead.isValid()) {
            throw new IllegalArgumentException("Lead payload is missing core business requirements.");
        }
        // 4. Protect Database Integrity (Prevent Hibernate Silent Updates)
        if (repository.existsById(lead.getId())) {
            throw new DuplicateLeadException("A Lead with this mobile number already exists.");
        }

        repository.save(mapper.toEntityFromDomain(lead));
        return lead.getId();
    }
}
