package com.kamath.propalert.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamath.propalert.infrastructure.persistence.entity.LeadEntity;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, String> {
    // JpaRepository provides save(), findById(), findAll(), and delete() out of the
    // box.
    // No code needed here yet!
}
