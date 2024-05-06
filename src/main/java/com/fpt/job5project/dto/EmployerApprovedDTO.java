package com.fpt.job5project.dto;

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
public class EmployerApprovedDTO {
    long employerId;

    String employerName;

    String phone;

    String email;

    String provinceName;

    String address;

    boolean approved;
}
