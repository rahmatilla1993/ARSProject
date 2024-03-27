package com.company.arsproject.mappers;

import com.company.arsproject.dto.UserReadDto;
import com.company.arsproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserReadDto toDto(User user);
}
