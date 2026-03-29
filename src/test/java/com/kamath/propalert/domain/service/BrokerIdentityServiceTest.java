package com.kamath.propalert.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Should use full org name if length is less than 3 characters")
    void shouldGenerateId_ShortOrgName() {
        // Arrange
        String state = "Goa"; // -> GO
        String city = "Panaji"; // -> PAN
        String orgName = "OM"; // Less than 3 -> OM
        String mobile = "9988776655"; // -> 6655

        // Act
        String brokerId = brokerIdentityService.generateBrokerId(state, city, orgName, mobile);

        // Assert
        assertThat(brokerId).isEqualTo("GO-PAN-OM-6655");
    }
    
    @Test
    @DisplayName("Should throw exception if mobile number is ridiculously short")
    void shouldThrowException_WhenMobileIsTooShort() {
        // Arrange
        String state = "Maharashtra";
        String city = "Pune";
        String orgName = "Kamath";
        String shortMobile = "123"; // Invalid!
        
        // Act & Assert
        // Mentorship Note: AssetJ lets us test expected exceptions fluently!
        assertThatThrownBy(() -> {
            brokerIdentityService.generateBrokerId(state, city, orgName, shortMobile);
        }).isInstanceOf(StringIndexOutOfBoundsException.class);
    }

}