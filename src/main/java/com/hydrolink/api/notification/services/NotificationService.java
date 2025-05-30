package com.hydrolink.api.notification.services;

import com.hydrolink.api.notification.model.entities.Notification;
import com.hydrolink.api.notification.model.enums.NotificationType;
import com.hydrolink.api.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void createNotification(Long userId, String message, NotificationType type) {
        Notification notification = new Notification(userId, message, type);
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void clearUserNotifications(Long userId) {
        List<Notification> notifications = getUserNotifications(userId);
        notificationRepository.deleteAll(notifications);
    }
}