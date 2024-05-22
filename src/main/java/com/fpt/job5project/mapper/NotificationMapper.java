package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.dto.NotificationDTO;
import com.fpt.job5project.entity.Job;
import com.fpt.job5project.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    Notification toEntity(NotificationDTO notificationDTO);
}
