package com.company.arsproject.controller;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.flights.FlightsByAirport;
import com.company.arsproject.dto.flights.FlightCreateDto;
import com.company.arsproject.dto.flights.FlightReadDto;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.service.FlightsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@PreAuthorize("isFullyAuthenticated()")
@RequiredArgsConstructor
@Tag(name = "Flight Controller", description = "Reyslar uchun controller")
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
public class FlightController {
    private final FlightsService flightsService;

    @GetMapping("/all")
    @Operation(summary = "Barcha reyslar ro'yxatini ko'rsatadi")
    public HttpEntity<List<FlightReadDto>> getAll() {
        return ResponseEntity.ok(flightsService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_AGENT')")
    @Operation(summary = "Berilgan id li reysni ko'rsatadi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli reys topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<FlightReadDto> getOne(@PathVariable("id") long id) {
        return ResponseEntity.ok(flightsService.getOne(id));
    }

    @GetMapping("/byCity")
    @Operation(summary = "Berilgan shaharda joylashgan airportlardagi reyslar ro'yxatini ko'rsatadi")
    public HttpEntity<List<FlightsByAirport>> getAllByAirport(@RequestParam("city") String city) {
        return ResponseEntity.ok(flightsService.findAllByAirport(city));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @Operation(summary = "Yangi reysni ro'yxatdan o'tkazadi")
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
    public HttpEntity<ApiResponseDto> save(@RequestBody FlightCreateDto dto) {
        return ResponseEntity.ok(flightsService.save(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @Operation(summary = "Berilgan idli reysni tahrir qiladi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli reys topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> edit(@PathVariable("id") long id, @RequestBody FlightCreateDto dto) {
        return ResponseEntity.ok(flightsService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @Operation(summary = "Berilgan idli reysni delete qiladi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli reys topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(flightsService.delete(id));
    }
}
