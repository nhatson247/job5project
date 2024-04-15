package com.fpt.job5project.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.RoleDTO;
import com.fpt.job5project.mapper.RoleMapper;
import com.fpt.job5project.repository.PermissionRepository;
import com.fpt.job5project.repository.RoleRepository;
import com.fpt.job5project.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public RoleDTO createRole(RoleDTO request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions1());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public List<RoleDTO> listOfRole() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleDTO)
                .toList();
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

}
