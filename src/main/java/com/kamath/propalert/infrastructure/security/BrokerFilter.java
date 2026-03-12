package com.kamath.propalert.infrastructure.security;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.kamath.propalert.domain.context.BrokerContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class BrokerFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String brokerId = httpRequest.getHeader("X-Broker-ID");

        if (brokerId != null) {
            // Put the ID in our Domain context storage
            BrokerContext.setBrokerId(brokerId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            // CRITICAL: Clean up after the request finishes
            BrokerContext.clear();
        }
    }
}
