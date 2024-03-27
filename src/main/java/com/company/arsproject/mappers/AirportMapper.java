package com.company.arsproject.mappers;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.entity.Airport;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirportMapper {

    @Mappings({
            @Mapping(target = "address.city", source = "addressCity"),
            @Mapping(target = "address.district", source = "addressDistrict")
    })
    Airport toEntity(AirportDto dto);

    @InheritInverseConfiguration
    AirportDto toDto(Airport airport);
}
