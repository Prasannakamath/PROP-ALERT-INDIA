package com.kamath.propalert.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BrokerTest {

    @Test
    @DisplayName("isValid should return true for valid broker details")
    void isValid_ValidBroker_ReturnsTrue() {
        Broker broker = Broker.builder()
                .organizationName("Kamath Properties")
                .contactEmail("contact@kamathprop.com")
                .mobileNumber("9876543210")
                .build();

        assertThat(broker.isValid()).isTrue();
    }

    @Test
    @DisplayName("isValid should return false if organization name is blank")
    void isValid_BlankOrganization_ReturnsFalse() {
        Broker broker = Broker.builder()
                .organizationName("   ")
                .contactEmail("contact@kamathprop.com")
                .mobileNumber("9876543210")
                .build();

        assertThat(broker.isValid()).isFalse();
    }

    @Test
    @DisplayName("isValid should return false if email lacks '@' symbol")
    void isValid_InvalidEmail_ReturnsFalse() {
        Broker broker = Broker.builder()
                .organizationName("Kamath Properties")
                .contactEmail("invalid-email.com")
                .mobileNumber("9876543210")
                .build();

        assertThat(broker.isValid()).isFalse();
    }

    @Test
    @DisplayName("isValid should return false if mobile number is not exactly 10 digits")
    void isValid_InvalidMobile_ReturnsFalse() {
        Broker broker = Broker.builder()
                .organizationName("Kamath Properties")
                .contactEmail("contact@kamathprop.com")
                .mobileNumber("98765") // Only 5 digits
                .build();

        assertThat(broker.isValid()).isFalse();
    }
}