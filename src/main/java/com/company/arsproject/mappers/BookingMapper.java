package com.company.arsproject.mappers;

import com.company.arsproject.dto.UserReadDto;
import com.company.arsproject.dto.bookings.BookingsReadDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.entity.Bookings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    @Mappings({
            @Mapping(target = "flight", source = "flightReadDto"),
            @Mapping(target = "user", source = "userReadDto"),
            @Mapping(target = "place", source = "bookings.place"),
            @Mapping(target = "bookingStatus", source = "bookings.status")
    })
    BookingsReadDto toDto(FlightReadDto flightReadDto, UserReadDto userReadDto, Bookings bookings);
}
