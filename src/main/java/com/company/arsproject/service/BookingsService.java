package com.company.arsproject.service;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.bookings.BookingsCreateDto;
import com.company.arsproject.dto.bookings.BookingsReadDto;
import com.company.arsproject.entity.Flights;
import com.company.arsproject.enums.BookingStatus;

import java.util.List;

public interface BookingsService {

    List<BookingsReadDto> findAll();

    List<BookingsReadDto> findAllByUser();
    void notifyAllBookings(Flights flights);

    BookingsReadDto findOne(long id);

    ApiResponseDto save(BookingsCreateDto dto);

    ApiResponseDto changeStatusBooking(long id, BookingStatus status);
}
