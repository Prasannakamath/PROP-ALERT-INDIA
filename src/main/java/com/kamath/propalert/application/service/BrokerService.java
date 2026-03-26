package com.kamath.propalert.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamath.propalert.domain.model.Broker;
import com.kamath.propalert.domain.service.BrokerIdentityService;
import com.kamath.propalert.infrastructure.mapping.BrokerMapper;
import com.kamath.propalert.infrastructure.persistence.entity.BrokerEntity;
import com.kamath.propalert.infrastructure.persistence.repository.BrokerRepository;
import com.propalert.infrastructure.web.dto.BrokerRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BrokerService {
    private BrokerRepository brokerRepository;
    private BrokerMapper brokerMapper;
    private BrokerIdentityService identityService;

    @Autowired
    public BrokerService(BrokerRepository repository, BrokerMapper mapper, BrokerIdentityService idGenerator) {
        this.brokerRepository = repository;
        this.brokerMapper = mapper;
        this.identityService = idGenerator;
    }

    @Transactional
    public String onboardBroker(BrokerRequest request) {
        // 1. Transform Request to Domain for business logic
        Broker domain = brokerMapper.toDomainFromRequest(request);

        // 2. Validate using your Domain logic (10-digit mobile, etc.)
        if (!domain.isValid()) {
            throw new IllegalArgumentException("Onboarding failed: Invalid organization details or mobile number.");
        }

        // 3. Generate the deterministic Business ID
        String generatedId = identityService.generateBrokerId(
                domain.getState(),
                domain.getCity(),
                domain.getOrganizationName(),
                domain.getMobileNumber());

        // 4. Map Domain to Entity and inject the Identity
        BrokerEntity entity = brokerMapper.toEntityFromDomain(domain);
        entity.setId(generatedId);

        // 5. Final Save
        brokerRepository.save(entity);

        return generatedId;
    }
}
