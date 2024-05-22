package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    public List<Notification> findByUserIdOrderByNotificationIdDesc(long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notifications SET seen = 1 WHERE userid = :userId", nativeQuery = true)
    int updateNotificationStatus(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "SELECT count(*) from notifications where userid =: userId and seen = 0", nativeQuery = true)
    int countNewNotification(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM notifications WHERE userid = :userId", nativeQuery = true)
    void deleteAllNotification(@Param("userId") long userId);
}
