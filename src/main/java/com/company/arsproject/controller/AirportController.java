package com.company.arsproject.controller;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.service.AirportService;
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
@RequestMapping("/api/airport")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Tag(name = "Airport controller")
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
                                schema = @Schema(implementation = String.class)
                        )
                }),

        @ApiResponse(
                responseCode = "403",
                description = "Sizda bu amalni bajarishga vakolatingiz yo'q!",
                content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = String.class)
                        )
                })
})
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/all")
    @Operation(summary = "Barcha airportlar ro'yxatini ko'rsatadi")
    public HttpEntity<List<AirportDto>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Berilgan id li airportni ko'rsatadi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli airport topilmadi!",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<AirportDto> findOne(@PathVariable("id") long id) {
        return ResponseEntity.ok(airportService.findOne(id));
    }

    @PostMapping
    @Operation(summary = "Yangi airport qo'shadi")
    public HttpEntity<ApiResponseDto> save(@RequestBody AirportDto dto) {
        return ResponseEntity.ok(airportService.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Berilgan idli airportni edit qiladi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli airport topilmadi!",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> edit(@PathVariable("id") long id, AirportDto dto) {
        return ResponseEntity.ok(airportService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Berilgan idli airportni o'chiradi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli airport topilmadi!",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(airportService.delete(id));
    }
}
