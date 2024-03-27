package com.company.arsproject.service.impl;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.flights.FlightCreateDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.dto.flights.FlightsByAirport;
import com.company.arsproject.entity.Airport;
import com.company.arsproject.entity.Company;
import com.company.arsproject.entity.Flights;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.mailing.SendMailMessage;
import com.company.arsproject.mappers.AirportMapper;
import com.company.arsproject.mappers.FlightsMapper;
import com.company.arsproject.repository.CompanyRepository;
import com.company.arsproject.repository.FlightsRepository;
import com.company.arsproject.service.AirportService;
import com.company.arsproject.service.AuthUserService;
import com.company.arsproject.service.BookingsService;
import com.company.arsproject.service.FlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightsServiceImpl implements FlightsService {
    private final FlightsRepository flightsRepository;
    private final FlightsMapper flightsMapper;
    private final CompanyRepository companyRepository;
    private final AirportService airportService;
    private final AirportMapper airportMapper;
    private final BookingsService bookingsService;
    private final SendMailMessage sendMailMessage;
    private final AuthUserService authUserService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public List<FlightReadDto> findAll() {
        return flightsRepository.findAll()
                .stream()
//                .peek(System.out::println)
                .map(flightsMapper::toDto)
                .toList();
    }

    @Override
    public FlightReadDto getOne(long id) {
        return flightsMapper.toDto(findById(id));
    }

    @Override
    public ApiResponseDto save(FlightCreateDto dto) {
        Company company = getCompany(dto.getCompanyId());
        Airport airport = airportService.findById(dto.getAirportId());
        Flights flights = flightsMapper.toEntity(dto);
        flights.setCompany(company);
        flights.setAirport(airport);
        flightsRepository.save(flights);
        FlightReadDto flight = flightsMapper.toDto(flights);
        sendNewFlightMessage(flight);
        return new ApiResponseDto("Flight saved", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto edit(FlightCreateDto dto, long id) {
        Airport airport = airportService.findById(dto.getAirportId());
        Company company = getCompany(dto.getCompanyId());
        Flights flights = findById(id);
        flights.setFlightTime(LocalDateTime.parse(dto.getFlight_time(), formatter));
        flights.setTicketCount(dto.getTicketCount());
        flights.setPrice(dto.getPrice());
        flights.setPlane(dto.getPlane());
        flights.setNumber(dto.getNumber());
        flights.setTownFrom(dto.getTownFrom());
        flights.setTownTo(dto.getTownTo());
        flights.setCompany(company);
        flights.setAirport(airport);
        flightsRepository.save(flights);
        bookingsService.notifyAllBookings(flights);
        return new ApiResponseDto("Flight edited", HttpStatus.NO_CONTENT.value());
    }

    @Override
    public ApiResponseDto delete(long id) {
        Flights flights = findById(id);
        flightsRepository.delete(flights);
        return new ApiResponseDto("Flight deleted", HttpStatus.ACCEPTED.value());
    }

    @Override
    public Flights findById(long id) {
        return flightsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight with '%d' id not found".formatted(id)));
    }

    @Override
    public List<FlightsByAirport> findAllByAirport(String city) {
        List<Airport> airports = airportService.findAllByAddressCity(city);
        Map<Airport, List<Flights>> map = airports.stream()
                .collect(Collectors.toMap(
                        airport -> airport,
                        (flightsRepository::findAllByAirport))
                );
        List<FlightsByAirport> flights = new ArrayList<>();
        map.forEach((key, value) -> {
            AirportDto airportDto = airportMapper.toDto(key);
            List<FlightReadDto> list = value.stream()
                    .map(flightsMapper::toDto)
                    .toList();
            flights.add(FlightsByAirport.builder()
                    .airport(airportDto)
                    .flights(list)
                    .build());
        });
        return flights;
    }

    private void sendNewFlightMessage(FlightReadDto dto) {
        String message = "About of new flight";
        String template = "new_flight.ftlh";
        authUserService.findAll()
                .forEach(user -> {
                    log.info("Sending message to mail for {}", user.getUsername());
                    sendMailMessage.sendNewFlightToMail(dto, user.getUsername(), message, template);
                });
    }

    private Company getCompany(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company with '%d' id not found".formatted(id)));
    }
}
