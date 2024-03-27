package com.company.arsproject.mappers;

import com.company.arsproject.dto.AuthUserDto;
import com.company.arsproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {

    @Mappings({
            @Mapping(target = "address.city", source = "city"),
            @Mapping(target = "address.district", source = "district"),
    })
    User toEntity(AuthUserDto dto);
}
