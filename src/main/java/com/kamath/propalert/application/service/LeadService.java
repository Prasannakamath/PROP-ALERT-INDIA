package com.kamath.propalert.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamath.propalert.domain.context.BrokerContext;
import com.kamath.propalert.domain.model.Lead;
import com.kamath.propalert.infrastructure.mapping.LeadMapper;
import com.kamath.propalert.infrastructure.persistence.entity.LeadEntity;
import com.kamath.propalert.infrastructure.persistence.repository.LeadRepository;
import com.propalert.infrastructure.web.dto.LeadRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    @Transactional
    public void processAndSave(LeadRequest request) {
        // 1. Map DTO to Entity
        Lead leadDomain = leadMapper.toDomainFromRequest(request);
        LeadEntity leadEntity = leadMapper.toEntityFromDomain(leadDomain);

        // 2. Stamp with current Broker ID from ThreadLocal
        leadEntity.setBrokerId(BrokerContext.getBrokerId());

        // 3. Persist
        leadRepository.save(leadEntity);
    }

    public List<LeadEntity> findAllLeads() {
        return leadRepository.findAll();
    }
}
