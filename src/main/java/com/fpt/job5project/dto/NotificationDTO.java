package com.fpt.job5project.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private long notificationId;
    private long userId;
    private String message;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    private Date postDate;
    private boolean seen;
}
