package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fpt.job5project.dto.RoleDTO;
import com.fpt.job5project.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleDTO request);

    RoleDTO toRoleDTO(Role role);
}
