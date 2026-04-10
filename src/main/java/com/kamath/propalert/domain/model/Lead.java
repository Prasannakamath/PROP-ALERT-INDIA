package com.kamath.propalert.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lead {
    private String id;
    private String brokerId;
    private String customerName;
    private String mobileNumber;
    private String propertyInterest;
    private String appointmentDate;

    // Core Business Rule: A valid Lead must be tied to a Broker and have basic contact info
    public boolean isValid() {
        return brokerId != null && !brokerId.isBlank()
                && customerName != null && !customerName.isBlank()
                && mobileNumber != null && mobileNumber.length() == 10;
    }
}
