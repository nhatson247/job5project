package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.RoleDTO;

public interface IRoleService {
    public RoleDTO createRole(RoleDTO request);

    public List<RoleDTO> listOfRole();

    public void deleteRole(String id);
}
