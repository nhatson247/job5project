package com.fpt.job5project.service;

import com.fpt.job5project.dto.NotificationDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationService {
    public NotificationDTO addNotification(NotificationDTO newNotification);
    public List<NotificationDTO> findByUserIdOrderByNotificationIdDesc(long userId);
    public void deleteNotification(long id);
    public void deleteAllNotification(long userId);
    public int updateNotificationStatus(long userId);
}
