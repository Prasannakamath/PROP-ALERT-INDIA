package com.kamath.propalert.application.service;

import com.kamath.propalert.domain.exception.DuplicateBrokerException;
import com.kamath.propalert.domain.model.Broker;
import com.kamath.propalert.domain.service.BrokerIdentityService;
import com.kamath.propalert.infrastructure.mapping.BrokerMapper;
import com.kamath.propalert.infrastructure.persistence.entity.BrokerEntity;
import com.kamath.propalert.infrastructure.persistence.repository.BrokerRepository;
import com.propalert.infrastructure.web.dto.BrokerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrokerServiceTest {

    @Mock
    private BrokerRepository brokerRepository;

    @Mock
    private BrokerMapper brokerMapper;

    @Mock
    private BrokerIdentityService identityService;

    @InjectMocks
    private BrokerService brokerService;

    @Test
    @DisplayName("Should throw DuplicateBrokerException when generated ID already exists")
    void onboardBroker_DuplicateId_ThrowsException() {
        // Arrange
        BrokerRequest request = new BrokerRequest();
        Broker validDomain = Broker.builder()
                .organizationName("Kamath")
                .contactEmail("test@test.com")
                .mobileNumber("1234567890")
                .state("Maharashtra").city("Pune")
                .build();
                
        String generatedId = "MH-PUN-KAM-7890";

        when(brokerMapper.toDomainFromRequest(request)).thenReturn(validDomain);
        when(identityService.generateBrokerId(any(), any(), any(), any())).thenReturn(generatedId);
        
        // Simulate that the database already has this ID
        when(brokerRepository.existsById(generatedId)).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> brokerService.onboardBroker(request))
                .isInstanceOf(DuplicateBrokerException.class)
                .hasMessageContaining("Broker already exists");
                
        // Verify save was NEVER called
        verify(brokerRepository, never()).save(any(BrokerEntity.class));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when Domain validation fails")
    void onboardBroker_InvalidDomain_ThrowsException() {
        // Arrange
        BrokerRequest request = new BrokerRequest();
        Broker invalidDomain = Broker.builder().build(); // Missing all required fields
        
        when(brokerMapper.toDomainFromRequest(request)).thenReturn(invalidDomain);

        // Act & Assert
        assertThatThrownBy(() -> brokerService.onboardBroker(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}