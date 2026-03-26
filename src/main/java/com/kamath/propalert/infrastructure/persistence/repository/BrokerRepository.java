package com.kamath.propalert.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamath.propalert.infrastructure.persistence.entity.BrokerEntity;

@Repository
public interface BrokerRepository extends JpaRepository<BrokerEntity, String> {

}
