package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.NotificationMapper;
import com.example.liu.model.domain.Notification;
import com.example.liu.model.request.NotificationRequest;
import com.example.liu.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class NotificationServiceTest {

    @Mock
    private NotificationMapper notificationMapper;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetManagerNotifications_Success() {
        Notification notification = new Notification();
        doReturn(Collections.singletonList(notification)).when(notificationMapper).selectList(any(QueryWrapper.class));

        R result = notificationService.getManagerNotifications();

        assertNotEquals(0, result.getCode());
        verify(notificationMapper).selectList(any(QueryWrapper.class));
    }

    @Test
    public void testGetManagerNotifications_NoData() {
        doReturn(Collections.emptyList()).when(notificationMapper).selectList(any(QueryWrapper.class));

        R result = notificationService.getManagerNotifications();

        assertNotEquals(0, result.getCode());
        assertEquals(Collections.emptyList(), result.getData());
        verify(notificationMapper).selectList(any(QueryWrapper.class));
    }

    @Test
    public void testAddNotification_Success() {
        NotificationRequest request = new NotificationRequest();
        Notification notification = new Notification();
        BeanUtils.copyProperties(request, notification);
        
        doReturn(1).when(notificationMapper).insert(any(Notification.class));
        doReturn(notification).when(notificationMapper).selectById(any(Integer.class));

        R result = notificationService.addNotification(request);

        assertNotEquals(0, result.getCode());
        verify(notificationMapper).insert(any(Notification.class));
    }

    @Test
    public void testAddNotification_Failure() {
        NotificationRequest request = new NotificationRequest();
        doReturn(0).when(notificationMapper).insert(any(Notification.class));

        R result = notificationService.addNotification(request);

        assertNotEquals(1, result.getCode());
        verify(notificationMapper).insert(any(Notification.class));
    }
}
