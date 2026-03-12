package com.kamath.propalert.infrastructure.persistence.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "leads")
@Data
// 1. Define the Filter
@FilterDef(name = "brokerFilter", parameters = @ParamDef(name = "brokerId", type = String.class))
// 2. Apply the Filter to this Entity
@Filter(name = "brokerFilter", condition = "broker_id = :brokerId")
public class LeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String customerName;
    private String phoneNumber;
    private String propertyInterest;

    @Column(name = "broker_id", nullable = false)
    private String brokerId;
}
