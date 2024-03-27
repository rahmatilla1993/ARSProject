package com.company.arsproject.mappers;

import com.company.arsproject.dto.flights.FlightCreateDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.entity.Flights;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightsMapper {

    @Mappings({
            @Mapping(target = "flightTime", source = "flight_time", dateFormat = "dd-MM-yyyy HH:mm")
    })
    Flights toEntity(FlightCreateDto dto);

    @Mappings({
            @Mapping(target = "flight_time", source = "flightTime", dateFormat = "dd-MM-yyyy HH:mm"),
            @Mapping(target = "company", source = "company.name"),
            @Mapping(target = "airport", source = "airport.name"),
            @Mapping(target = "ticket_count", source = "ticketCount")
    })
    FlightReadDto toDto(Flights flights);
}
