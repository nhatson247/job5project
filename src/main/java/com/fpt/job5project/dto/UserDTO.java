package com.fpt.job5project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long userId;

    @NotNull(message = "USERNAME_NOT_NULL")
    private String userName;

    @NotNull(message = "PASSWORD_NOT_NULL")
    private String password;

    boolean isLocked;

    @NotNull(message = "ROLE_NOT_NULL")
    String role;
}