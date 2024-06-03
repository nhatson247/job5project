package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.NotificationDTO;
import com.fpt.job5project.entity.Notification;
import com.fpt.job5project.mapper.NotificationMapper;
import com.fpt.job5project.repository.NotificationRepository;
import com.fpt.job5project.service.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements INotificationService {
    NotificationMapper notificationMapper;
    NotificationRepository notificationRepository;

    @Override
    public NotificationDTO addNotification(NotificationDTO newNotification) {
        Notification notificationEntity = notificationMapper.toEntity(newNotification);
        System.out.println(notificationEntity.getUserId());

        return notificationMapper.toDTO(notificationRepository.save(notificationEntity));
    }

    @Override
    public List<NotificationDTO> findByUserIdOrderByNotificationIdDesc(long userId) {
        List<NotificationDTO> listDTOs = new ArrayList<>();
        List<Notification> listEntities = notificationRepository.findByUserIdOrderByNotificationIdDesc(userId);
        for (Notification notificationEntity : listEntities) {
            listDTOs.add(notificationMapper.toDTO(notificationEntity));
        }
        return listDTOs;
    }


    @Override
    public void deleteNotification(long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public void deleteAllNotification(long userId) {
        notificationRepository.deleteAllNotification(userId);
    }

    @Override
    public int updateNotificationStatus(long userId) {
        return notificationRepository.updateNotificationStatus(userId);
    }


}
