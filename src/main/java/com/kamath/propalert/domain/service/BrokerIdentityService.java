package com.kamath.propalert.domain.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BrokerIdentityService {
    /**
     * Logic: {STATE}-{CITY}-{ORG}-{LAST_4_MOBILE}
     * Example: MH-PUN-KMT-9876
     */
    public String generateBrokerId(String state, String city, String orgName, String mobileNumber) {
        // 1. Extract first 2 of State (e.g., MH)
        String stateString = state.substring(0, 2).toUpperCase();

        // 2. Extract first 3 of City (e.g., PUN)
        String cityString = city.substring(0, 3).toUpperCase();

        // 3. Extract first 3 of Org Name (e.g., KMT)
        String orgNameString = orgName.length() >= 3
                ? orgName.substring(0, 3).toUpperCase()
                : orgName.toUpperCase();

        // 4. Extract last 4 of Mobile (e.g., 9876)
        String suffix = mobileNumber.substring(mobileNumber.length() - 4);

        return String.format("%s-%s-%s-%s", stateString, cityString, orgNameString, suffix);
    }
}
