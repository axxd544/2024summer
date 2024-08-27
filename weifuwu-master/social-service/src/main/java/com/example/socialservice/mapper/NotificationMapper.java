package com.example.socialservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.socialservice.model.domain.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMapper extends BaseMapper<Notification> {
    Notification getLatestNotification();
}
