package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;

import com.fpt.job5project.dto.NotificationDTO;
import com.fpt.job5project.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);

    Notification toEntity(NotificationDTO notificationDTO);
}
