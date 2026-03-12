package com.kamath.propalert.infrastructure.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.kamath.propalert.domain.context.BrokerContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Aspect
@Component
public class BrokerAspect {
    @PersistenceContext
    private EntityManager entityManager;

    // This method will be called before any repository method is executed
    @Before("execution(* org.springframework.data.repository.Repository+.*(..))")
    public void beforeRepositoryMethod() {
        String brokerId = BrokerContext.getBrokerId();
        if (brokerId != null) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("brokerFilter").setParameter("brokerId", brokerId);
        }
    }
}
