package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;

import com.fpt.job5project.dto.PermissionDTO;
import com.fpt.job5project.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionDTO request);

    PermissionDTO toPermissionDTO(Permission permission);
}
