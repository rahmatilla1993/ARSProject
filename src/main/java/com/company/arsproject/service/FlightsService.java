package com.company.arsproject.service;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.flights.FlightsByAirport;
import com.company.arsproject.dto.flights.FlightCreateDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.entity.Flights;

import java.util.List;

public interface FlightsService {

    List<FlightReadDto> findAll();
    FlightReadDto getOne(long id);
    Flights findById(long id);
    ApiResponseDto save(FlightCreateDto dto);
    ApiResponseDto edit(FlightCreateDto dto, long id);
    ApiResponseDto delete(long id);
    List<FlightsByAirport> findAllByAirport(String city);
}
