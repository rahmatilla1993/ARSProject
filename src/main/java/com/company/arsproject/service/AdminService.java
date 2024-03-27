package com.company.arsproject.service;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.enums.Role;
import com.company.arsproject.enums.UserStatus;

public interface AdminService {

    ApiResponseDto setStatus(long userId, UserStatus status);

    ApiResponseDto setRole(long userId, Role role);
}
