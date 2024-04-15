package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.PermissionDTO;

public interface IPermissionService {
    public PermissionDTO createpermission(PermissionDTO request);

    public List<PermissionDTO> listOfPermission();

    public void delete(String id);
}
