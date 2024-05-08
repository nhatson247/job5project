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
public class EmployerDTO {
    private long employerId;

    @NotNull(message = "EMPLOYER_NAME_NOT_NULL")
    private String employerName;

    private String description;

    private long rankId;

    @NotNull(message = "PHONE_NOT_NULL")
    private String phone;

    @NotNull(message = "EMAIL_NOT_NULL")
    private String email;

    private String photo;

    private String background;

    private String provinceName;

    private String address;

    private String reviewScore;

    private boolean approved;
}