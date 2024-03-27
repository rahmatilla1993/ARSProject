package com.company.arsproject.controller;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.bookings.BookingsCreateDto;
import com.company.arsproject.dto.bookings.BookingsReadDto;
import com.company.arsproject.enums.BookingStatus;
import com.company.arsproject.service.BookingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@PreAuthorize("isFullyAuthenticated()")
@Tag(name = "Bookings controller", description = "Reyslar haqida controller")
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "OK!",
                content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = AirportDto.class)
                        )
                }),
        @ApiResponse(
                responseCode = "401",
                description = "UnAuthorized!",
                content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = AirportDto.class)
                        )
                })
})
public class BookingsController {

    private final BookingsService bookingsService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Barcha buyurtmalar ro'yxatini ko'rsatadi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    })
    })
    public HttpEntity<List<BookingsReadDto>> findAll() {
        return ResponseEntity.ok(bookingsService.findAll());
    }

    @GetMapping("/byUser")
    @Operation(summary = "Tizimga kirgan userni reyslarga buyurtmalar ro'yxatini ko'rsatadi")
    public HttpEntity<List<BookingsReadDto>> findAllByUser() {
        return ResponseEntity.ok(bookingsService.findAllByUser());
    }

    @PostMapping
    @Operation(summary = "Reysga buyurtma berish mumkin")
    public HttpEntity<ApiResponseDto> save(@RequestBody BookingsCreateDto dto) {
        return ResponseEntity.ok(bookingsService.save(dto));
    }

    @PutMapping("/bookingStatus/{id}")
    @Operation(summary = "Reysga buyurtmani bekor qilish mumkin")
    public HttpEntity<ApiResponseDto> changeStatusBooking(@PathVariable("id") long id,
                                                          @RequestParam("bookingStatus") BookingStatus status) {
        return ResponseEntity.ok(bookingsService.changeStatusBooking(id, status));
    }
}
