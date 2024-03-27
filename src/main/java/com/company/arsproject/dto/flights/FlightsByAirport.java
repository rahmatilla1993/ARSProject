package com.company.arsproject.dto.flights;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightsByAirport {
    private AirportDto airport;
    private List<FlightReadDto> flights;
}
