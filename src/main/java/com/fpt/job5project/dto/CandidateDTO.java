package com.fpt.job5project.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateDTO {
    long candidateId;
    @NotEmpty(message = "FULLNAME_NOT_NULL")
    String fullName;
    @NotNull(message = "BIRTHDATE_NOT_NULL")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    Date birthDate;
    int yearExperience;
    String bio;
    @NotNull(message = "PHONE_NOT_NULL")
    String phone;
    @NotNull(message = "EMAIL_NOT_NULL")
    String email;
    String photo;
    String provinceName;
    String address;

}
