package com.kamath.propalert.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kamath.propalert.domain.service.BrokerIdentityService;

class BrokerIdentityServiceTest {
    private BrokerIdentityService brokerIdentityService;

    @BeforeEach
    void setUp() {
        // We manually instantiate the class.
        // NO @SpringBootTest -> purely testing business logic at lightning speed.
        brokerIdentityService = new BrokerIdentityService();
    }

    @Test
    @DisplayName("Should generate exact deterministic Broker ID for standard inputs")
    void shouldGenerateId_HappyPath() {
        // Arrange
        String state = "Maharashtra"; // substring(0,2) -> MA
        String city = "Pune"; // substring(0,3) -> PUN
        String orgName = "Kamath"; // substring(0,3) -> KAM
        String mobile = "9876543210"; // last 4 -> 3210
        // Act
        String brokerId = brokerIdentityService.generateBrokerId(state, city, orgName, mobile);
        // Assert
        assertThat(brokerId).isEqualTo("MA-PUN-KAM-3210");
    }

}