package com.fpt.job5project.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.dto.RoleDTO;
import com.fpt.job5project.service.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class RoleController {
    IRoleService iroleService;

    @GetMapping
    ResponseObject<List<RoleDTO>> getAll() {
        return ResponseObject.<List<RoleDTO>>builder()
                .data(iroleService.listOfRole())
                .build();
    }

    @PostMapping
    ResponseObject<RoleDTO> create(@ModelAttribute RoleDTO request) {
        return ResponseObject.<RoleDTO>builder()
                .data(iroleService.createRole(request))
                .build();
    }

    @DeleteMapping("/{roleId}")
    ResponseObject<Void> delete(@PathVariable String roleId) {
        iroleService.deleteRole(roleId);
        return ResponseObject.<Void>builder().build();
    }
}
