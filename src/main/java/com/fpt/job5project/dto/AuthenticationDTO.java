package com.fpt.job5project.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthenticationDTO {
    String userName;
    String password;
    String token;
    boolean authenticated;
}
