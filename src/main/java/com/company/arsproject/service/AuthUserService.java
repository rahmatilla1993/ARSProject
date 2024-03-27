package com.company.arsproject.service;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.AuthUserDto;
import com.company.arsproject.entity.User;
import com.company.arsproject.enums.UserStatus;

import java.util.List;

public interface AuthUserService {
    List<User> findAll();
    ApiResponseDto register(AuthUserDto dto);
}
