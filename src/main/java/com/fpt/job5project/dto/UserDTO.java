package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Username is required")
    private String userName;

    @JsonIgnore
    @NotEmpty(message = "Password is required")
    private String password;

    boolean blocked;

    @NotEmpty(message = "Role is required")
    String role;
}