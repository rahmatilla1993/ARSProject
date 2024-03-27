package com.company.arsproject.service;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.entity.Airport;

import java.util.List;

public interface AirportService {

    ApiResponseDto save(AirportDto dto);
    List<AirportDto> getAll();
    AirportDto findOne(long id);
    ApiResponseDto edit(AirportDto dto, long id);
    ApiResponseDto delete(long id);
    Airport findById(long id);
    List<Airport> findAllByAddressCity(String city);
}
