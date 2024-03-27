package com.company.arsproject.service.impl;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.entity.User;
import com.company.arsproject.enums.Role;
import com.company.arsproject.enums.UserStatus;
import com.company.arsproject.exception.NotFoundException;
import com.company.arsproject.repository.AuthUserRepository;
import com.company.arsproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthUserRepository authUserRepository;

    @Override
    public ApiResponseDto setStatus(long userId, UserStatus status) {
        User user = getUser(userId);
        user.setStatus(status);
        authUserRepository.save(user);
        return new ApiResponseDto("User status changed", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto setRole(long userId, Role role) {
        User user = getUser(userId);
        user.setRole(role);
        authUserRepository.save(user);
        return new ApiResponseDto("User role changed", HttpStatus.OK.value());
    }

    private User getUser(long userId) {
        return authUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with '%d' id not found".formatted(userId)));
    }
}
