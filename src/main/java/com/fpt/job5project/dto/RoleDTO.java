package com.fpt.job5project.dto;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {
    String name;
    String description;
    Set<PermissionDTO> permissions;
    Set<String> permissions1;
}
