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
}
