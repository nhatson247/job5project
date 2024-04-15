package com.fpt.job5project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.PermissionDTO;
import com.fpt.job5project.entity.Permission;
import com.fpt.job5project.mapper.PermissionMapper;
import com.fpt.job5project.repository.PermissionRepository;
import com.fpt.job5project.service.IPermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public PermissionDTO createpermission(PermissionDTO request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionDTO(permission);
    }

    @Override
    public List<PermissionDTO> listOfPermission() {

        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toPermissionDTO)
                .toList();
    }

    @Override
    public void delete(String id) {
        permissionRepository.deleteById(id);
    }

}
