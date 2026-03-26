package com.kamath.propalert.infrastructure.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kamath.propalert.domain.model.Broker;
import com.kamath.propalert.infrastructure.persistence.entity.BrokerEntity;
import com.propalert.infrastructure.web.dto.BrokerRequest;

@Mapper(componentModel = "spring")
public interface BrokerMapper {

    // request to domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Broker toDomainFromRequest(BrokerRequest request);

    /**
     * STEP 2: Domain Model -> Persistence Entity
     * This is used for the save() operation.
     */
    BrokerEntity toEntityFromDomain(Broker domain);

    /**
     * STEP 3: Persistence Entity -> Domain Model
     * Useful for retrieval and business logic processing.
     */
    Broker toDomainFromEntity(BrokerEntity entity);

    // 4. Domain -> Web DTO (The missing piece!)
    // This allows the Controller to return the Broker details (including the
    // generated ID)
    BrokerRequest toDtoFromDomain(Broker domain);

}
