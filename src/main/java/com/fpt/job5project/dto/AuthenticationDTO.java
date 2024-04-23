package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationDTO {

    @NotEmpty(message = "Username is required")
    String userName;

    @NotEmpty(message = "Password is required")
    String password;

    String token;

    boolean authenticated;
}
