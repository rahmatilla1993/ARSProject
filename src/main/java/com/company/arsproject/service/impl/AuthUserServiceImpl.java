package com.company.arsproject.service.impl;

import com.company.arsproject.dto.ApiResponseDto;
import com.company.arsproject.dto.AuthUserDto;
import com.company.arsproject.entity.User;
import com.company.arsproject.enums.Role;
import com.company.arsproject.enums.UserStatus;
import com.company.arsproject.exception.ExistsException;
import com.company.arsproject.mappers.AuthUserMapper;
import com.company.arsproject.repository.AuthUserRepository;
import com.company.arsproject.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return authUserRepository.findAll();
    }

    @Override
    public ApiResponseDto register(AuthUserDto dto) {
        authUserRepository.findByUsername(dto.getUsername())
                .ifPresentOrElse(user -> {
                    throw new ExistsException("Username with '%s' already taken".formatted(user.getUsername()));
                }, () -> {
                    User user = authUserMapper.toEntity(dto);
                    user.setPassword(passwordEncoder.encode(dto.getPassword()));
                    user.setRole(Role.ROLE_CUSTOMER);
                    user.setStatus(UserStatus.ACTIVE);
                    authUserRepository.save(user);
                });
        return new ApiResponseDto("User registered successfully", HttpStatus.OK.value());
    }
}
