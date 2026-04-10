package com.kamath.propalert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.OffsetDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FilterDef(name = "brokerFilter", parameters = {@ParamDef(name = "brokerId", type = String.class)})
@Filter(name = "brokerFilter", condition = "broker_id = :brokerId")
public class LeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Deterministic ID
    @Column(name = "broker_id", nullable = false, updatable = false)
    private String brokerId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;
    @Column(name = "property_interest")
    private String propertyInterest;
    @Column(name = "appointment_date")
    private OffsetDateTime appointmentDate;
}
