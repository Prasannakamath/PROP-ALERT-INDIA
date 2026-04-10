package com.kamath.propalert.infrastructure.mapping;

import com.kamath.propalert.domain.model.Lead;
import com.kamath.propalert.infrastructure.persistence.entity.LeadEntity;
import com.propalert.infrastructure.web.dto.LeadRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeadMapper {
    /**
     * 1. Web DTO -> Domain Model
     * We strictly ignore 'id' and 'brokerId' here.
     * This enforces our security rule: The OpenAPI request cannot inject these fields.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brokerId", ignore = true)
    Lead toDomainFromRequest(LeadRequest request);
    /**
     * 2. Domain Model -> Persistence Entity
     * Used right before we save to the database.
     */
    LeadEntity toEntityFromDomain(Lead domain);
    /**
     * 3. Persistence Entity -> Domain Model
     * Used when we retrieve records from the database back into our business logic.
     */
    Lead toDomainFromEntity(LeadEntity entity);
}
