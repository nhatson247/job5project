package com.fpt.job5project.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    long userId;

    @NotEmpty(message = "USERNAME_INVALID")
    String userName;

    @JsonIgnore
    @NotEmpty(message = "PASSWORD_INVALID")
    String password;

    Set<String> roles;

}
