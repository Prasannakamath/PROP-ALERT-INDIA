package com.kamath.propalert.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lead {
    private String id;
    private String customerName;
    private String phoneNumber;
    private String propertyInterest;

    /**
     * Business Logic: A lead is only valid if it has a name
     * and a valid 10-digit Indian mobile number.
     */
    public boolean isValid() {
        return customerName != null && !customerName.isBlank()
                && phoneNumber != null && phoneNumber.matches("\\d{10}");
    }
}
