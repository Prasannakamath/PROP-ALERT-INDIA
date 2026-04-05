package com.kamath.propalert.infrastructure.web.controller;

import com.kamath.propalert.infrastructure.persistence.entity.BrokerEntity;
import com.kamath.propalert.infrastructure.persistence.repository.BrokerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
public class BrokerIntegrationTest {
    // Mentorship Note: @ServiceConnection automatically injects the JDBC URL, Username, and Password
    // into the Spring Context. No more messy application-test.yml properties!
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrokerRepository brokerRepository;

    @AfterEach
    void tearDown() {
        brokerRepository.deleteAll();
    }


    @Test
    @WithMockUser
    @DisplayName("Should successfully onboard a new Broker and return 201 Created with Identity")
    void onboardBroker_ValidPayload_Returns201() throws Exception {
        // Arrange: Build the raw JSON matching our OpenAPI BrokerRequest contract
        String validPayload = """
                {
                  "organizationName": "Kamath Properties",
                  "contactEmail": "contact@kamathprop.com",
                  "mobileNumber": "9876543210",
                  "city": "Pune",
                  "state": "Maharashtra"
                }
                """;
        // Act & Assert
        // We simulate an HTTP POST request to our Web Controller boundary.
        mockMvc.perform(post("/api/v1/brokers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brokerId").value("MA-PUN-KAM-3210"))
                .andReturn();

        //verify the entity was correctly persisted to DB
        BrokerEntity persistedBroker = brokerRepository.findById("MA-PUN-KAM-3210").orElseThrow();
        Assertions.assertEquals("Kamath Properties", persistedBroker.getOrganizationName());
        Assertions.assertEquals("contact@kamathprop.com", persistedBroker.getContactEmail());
        Assertions.assertEquals("9876543210", persistedBroker.getMobileNumber());
        Assertions.assertEquals("Pune", persistedBroker.getCity());
        Assertions.assertEquals("Maharashtra", persistedBroker.getState());

    }

    @Test
    @WithMockUser
    @DisplayName("Should return 409 Conflict when onboarding a duplicate Broker (same email/mobile)")
    void onboardBroker_DuplicateBroker_Returns409() throws Exception {
        // Arrange: Build a valid payload
        String validPayload = """
                {
                  "organizationName": "Kamath Properties",
                  "contactEmail": "contact@kamathprop.com",
                  "mobileNumber": "9876543210",
                  "city": "Pune",
                  "state": "Maharashtra"
                }
                """;

        // Act 1: First request succeeds
        mockMvc.perform(post("/api/v1/brokers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPayload))
                .andExpect(status().isCreated());

        // Act 2 & Assert: Second request with the same payload fails with 409 Conflict
        mockMvc.perform(post("/api/v1/brokers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPayload))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.title").exists()); // Validating RFC 7807 structure
    }

    @Test
    @WithMockUser
    @DisplayName("Should return 400 Bad Request when payload fails Bean Validation")
    void onboardBroker_InvalidPayload_Returns400() throws Exception {
        // Arrange: Missing organizationName and invalid email/mobile format
        String invalidPayload = """
                    {
                      "organizationName": "",
                      "contactEmail": "invalid-email",
                      "mobileNumber": "123",
                      "city": "Pune",
                      "state": "Maharashtra"
                    }
                    """;

        // Act & Assert
        mockMvc.perform(post("/api/v1/brokers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Bad Request")); // Validating RFC 7807 structure
    }
}
