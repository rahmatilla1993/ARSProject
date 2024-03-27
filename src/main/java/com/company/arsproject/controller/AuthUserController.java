package com.company.arsproject.controller;

import com.company.arsproject.dto.AirportDto;
import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.AuthUserDto;
import com.company.arsproject.exception.ExistsException;
import com.company.arsproject.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth User controller", description = "User register yoki login qilishi mumkin")
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "OK!",
                content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = AirportDto.class)
                        )
                })
})
public class AuthUserController {

    private final AuthUserService authUserService;

    @PostMapping("/register")
    @Operation(summary = "Tizimdan ro'yxatdan o'tkazadi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "409",
                    description = "Bunday username tizimda mavjud",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExistsException.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> register(@Valid @RequestBody AuthUserDto dto) {
        return ResponseEntity.ok(authUserService.register(dto));
    }
}
