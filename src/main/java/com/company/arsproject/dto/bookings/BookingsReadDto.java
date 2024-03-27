package com.company.arsproject.dto.bookings;

import com.company.arsproject.dto.UserReadDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingsReadDto {
    private FlightReadDto flight;
    private UserReadDto user;
    private String place;
    private String bookingStatus;
}
