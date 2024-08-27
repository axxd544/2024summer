package com.example.socialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.socialservice.common.R;
import com.example.socialservice.model.domain.Notification;
import com.example.socialservice.model.request.NotificationRequest;

public interface NotificationService extends IService<Notification> {
    R getManagerNotifications();
    R getConsumerNotifications(int userId);
    R getNotifications();

    R addNotification(NotificationRequest addNotificationRequest);
    R markNotificationAsRead(int id);
}
