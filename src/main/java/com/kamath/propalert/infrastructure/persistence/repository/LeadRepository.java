package com.kamath.propalert.infrastructure.persistence.repository;

import com.kamath.propalert.infrastructure.persistence.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity,String> {
}
