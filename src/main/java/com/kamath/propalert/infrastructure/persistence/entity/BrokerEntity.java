package com.kamath.propalert.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brokers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrokerEntity {

    @Id
    private String id; // Holds the generated 'MH-PUN-KMT-9876'

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "contact_email", nullable = false, unique = true)
    private String contactEmail;

    @Column(name = "mobile_number", nullable = false, unique = true, length = 10)
    private String mobileNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}