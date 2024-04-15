package com.fpt.job5project.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fpt.job5project.dto.PermissionDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IPermissionService;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class PermissionController {

    @Autowired
    IPermissionService ipermissionService;

    @PostMapping
    ResponseObject<PermissionDTO> create(@ModelAttribute PermissionDTO request) {
        return ResponseObject.<PermissionDTO>builder()
                .data(ipermissionService.createpermission(request))
                .build();
    }

    @GetMapping
    ResponseObject<List<PermissionDTO>> getAll() {
        return ResponseObject.<List<PermissionDTO>>builder()
                .data(ipermissionService.listOfPermission())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ResponseObject<Void> delete(@PathVariable String permissionId) {
        ipermissionService.delete(permissionId);
        return ResponseObject.<Void>builder().build();
    }
}
