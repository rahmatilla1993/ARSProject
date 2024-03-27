package com.company.arsproject.service.impl;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.bookings.BookingsCreateDto;
import com.company.arsproject.dto.bookings.BookingsReadDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.entity.Bookings;
import com.company.arsproject.entity.Flights;
import com.company.arsproject.entity.User;
import com.company.arsproject.enums.BookingStatus;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.mailing.SendMailMessage;
import com.company.arsproject.mappers.BookingMapper;
import com.company.arsproject.mappers.FlightsMapper;
import com.company.arsproject.mappers.UserMapper;
import com.company.arsproject.repository.BookingsRepository;
import com.company.arsproject.repository.FlightsRepository;
import com.company.arsproject.security.SessionUser;
import com.company.arsproject.service.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {
    private final BookingsRepository bookingsRepository;
    private final UserMapper userMapper;
    private final FlightsMapper flightsMapper;
    private final BookingMapper bookingMapper;
    private final SessionUser sessionUser;
    private final FlightsRepository flightsRepository;
    private final SendMailMessage sendMailMessage;

    @Override
    public List<BookingsReadDto> findAll() {
        List<Bookings> bookings = bookingsRepository.findAll();
        return bookings.stream()
                .map(booking ->
                        bookingMapper.toDto(
                                flightsMapper.toDto(booking.getFlights()),
                                userMapper.toDto(booking.getUser()),
                                booking
                        )
                )
                .toList();
    }

    @Override
    public List<BookingsReadDto> findAllByUser() {
        User user = sessionUser.getUser();
        List<Bookings> bookings = bookingsRepository.findAllByUser(user);
        return bookings.stream()
                .map(booking ->
                        bookingMapper.toDto(
                                flightsMapper.toDto(booking.getFlights()),
                                userMapper.toDto(booking.getUser()),
                                booking
                        )
                )
                .toList();
    }

    @Override
    public void notifyAllBookings(Flights flights) {
        String message = "Flight %s has been amended".formatted(flights.getNumber());
        String template = "change_flight.ftlh";
        List<Bookings> bookings = bookingsRepository.findAllByFlights(flights);
        bookings.forEach(booking -> {
            String username = booking.getUser().getUsername();
            FlightReadDto dto = flightsMapper.toDto(flights);
            sendMailMessage.sendNewFlightToMail(dto, username, message, template);
        });
    }

    @Override
    public BookingsReadDto findOne(long id) {
        Bookings bookings = getOne(id);
        return bookingMapper.toDto(
                flightsMapper.toDto(bookings.getFlights()),
                userMapper.toDto(bookings.getUser()),
                bookings
        );
    }

    @Override
    public ApiResponseDto save(BookingsCreateDto dto) {
        long flightId = dto.getFlightId();
        Flights flights = flightsRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("Flight with '%d' id not found".formatted(flightId)));
        flights.setTicketCount(flights.getTicketCount() - 1);
        User user = sessionUser.getUser();
        Bookings bookings = Bookings.builder()
                .flights(flights)
                .user(user)
                .place(dto.getPlace())
                .status(BookingStatus.ORDERED)
                .build();
        bookingsRepository.save(bookings);
        return new ApiResponseDto("Booking saved", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto changeStatusBooking(long id, BookingStatus status) {
        Bookings bookings = getOne(id);
        bookings.setStatus(status);
        bookingsRepository.save(bookings);
        return new ApiResponseDto("Booking declined", HttpStatus.OK.value());
    }

    public Bookings getOne(long id) {
        return bookingsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with '%d' id not found"));
    }
}
