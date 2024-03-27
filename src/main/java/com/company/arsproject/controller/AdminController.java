package com.company.arsproject.controller;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.enums.Role;
import com.company.arsproject.enums.UserStatus;
import com.company.arsproject.service.AdminService;
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

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Tag(name = "Admin controller")
@ApiResponses({
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
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/status/{id}")
    @Operation(summary = "Tizim foydalanuvchilari (customer yoki agent) ni block qilinishi yoki blockdan chiqarish mumkin")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Foydalanuvchi statusi o'zgartirildi!",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponseDto.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> setUserStatus(@PathVariable("id") long id,
                                                    @RequestParam("status") UserStatus status) {
        return ResponseEntity.ok(
                adminService.setStatus(id, status)
        );
    }

    @GetMapping("/setRole/{userId}")
    @Operation(summary = "Berilgan id li userni roleni o'zgartiradi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK!",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponseDto.class)
                            )
                    })
    })
    public HttpEntity<ApiResponseDto> setRole(@PathVariable("userId") long userId,
                                              @RequestParam("role") Role role) {
        return ResponseEntity.ok(adminService.setRole(userId, role));
    }
}
