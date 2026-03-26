package com.kamath.propalert.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Broker {
    private final String id;
    private final String organizationName;
    private final String contactEmail;
    private final String mobileNumber;
    private final String city;
    private final String state;
    private final boolean active;

    // Business Logic: A broker is only valid if they have a name and email
    public boolean isValid() {
        return organizationName != null && !organizationName.isBlank()
                && contactEmail != null && contactEmail.contains("@")
                && mobileNumber != null && mobileNumber.length() == 10;
    }
}
