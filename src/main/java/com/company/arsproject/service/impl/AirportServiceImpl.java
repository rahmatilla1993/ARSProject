package com.company.arsproject.service.impl;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.entity.Address;
import com.company.arsproject.entity.Airport;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.mappers.AirportMapper;
import com.company.arsproject.repository.AirportRepository;
import com.company.arsproject.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public ApiResponseDto save(AirportDto dto) {
        Airport airport = airportMapper.toEntity(dto);
        airportRepository.save(airport);
        return new ApiResponseDto("Airport saved", HttpStatus.OK.value());
    }

    @Override
    public List<AirportDto> getAll() {
        return airportRepository.findAll()
                .stream()
                .map(airportMapper::toDto)
                .toList();
    }

    @Override
    public AirportDto findOne(long id) {
        Airport airport = findById(id);
        return airportMapper.toDto(airport);
    }

    @Override
    public ApiResponseDto edit(AirportDto dto, long id) {
        Airport airport = findById(id);
        airport.setName(dto.getName());
        airport.setAddress(Address.builder()
                .city(dto.getAddressCity())
                .district(dto.getAddressDistrict())
                .build());
        airportRepository.save(airport);
        return new ApiResponseDto("Airport edit", HttpStatus.NO_CONTENT.value());
    }

    @Override
    public List<Airport> findAllByAddressCity(String city) {
        return airportRepository.findAllByAddress_City(city);
    }

    @Override
    public ApiResponseDto delete(long id) {
        Airport airport = findById(id);
        airportRepository.delete(airport);
        return new ApiResponseDto("Airport deleted", HttpStatus.ACCEPTED.value());
    }

    @Override
    public Airport findById(long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airport with '%d' id not found".formatted(id)));
    }
}
