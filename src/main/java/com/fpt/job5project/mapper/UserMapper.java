package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.UserDTO;
import com.fpt.job5project.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO user);

    UserDTO toUserDTO(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget User user, UserDTO request);

}
