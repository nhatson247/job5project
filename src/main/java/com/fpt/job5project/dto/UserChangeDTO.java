package com.fpt.job5project.dto;

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
public class UserChangeDTO {
    @NotEmpty(message = "Old Password is required")
    String password;

    @NotEmpty(message = "New Password is required")
    String newPassword;

    @NotEmpty(message = "Confirm New Password is required")
    String confirmNewPassword;
}
