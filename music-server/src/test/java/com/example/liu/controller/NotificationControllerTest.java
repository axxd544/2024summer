package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.NotificationRequest;
import com.example.liu.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetManagerNotifications() {
        R expectedResponse = R.success("Manager notifications fetched successfully");
        when(notificationService.getManagerNotifications()).thenReturn(expectedResponse);

        R actualResponse = notificationController.getManagerNotifications();

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).getManagerNotifications();
    }

    @Test
    public void testGetConsumerNotifications() {
        int userId = 1;
        R expectedResponse = R.success("Consumer notifications fetched successfully");
        when(notificationService.getConsumerNotifications(userId)).thenReturn(expectedResponse);

        R actualResponse = notificationController.getConsumerNotifications(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).getConsumerNotifications(userId);
    }

    @Test
    public void testAddNotification() {
        NotificationRequest request = new NotificationRequest();
        R expectedResponse = R.success("Notification added successfully");
        when(notificationService.addNotification(request)).thenReturn(expectedResponse);

        R actualResponse = notificationController.addNotification(request);

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).addNotification(request);
    }

    @Test
    public void testGetNotifications() {
        R expectedResponse = R.success("Notifications fetched successfully");
        when(notificationService.getNotifications()).thenReturn(expectedResponse);

        R actualResponse = notificationController.getNotifications();

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).getNotifications();
    }

    @Test
    public void testMarkNotificationAsRead() {
        int id = 1;
        R expectedResponse = R.success("Notification marked as read");
        when(notificationService.markNotificationAsRead(id)).thenReturn(expectedResponse);

        R actualResponse = notificationController.markNotificationAsRead(id);

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).markNotificationAsRead(id);
    }

    @Test
    public void testGetConsumerNotifications_InvalidUserId() {
        int invalidUserId = -1;
        R expectedResponse = R.error("Invalid user ID");
        when(notificationService.getConsumerNotifications(invalidUserId)).thenReturn(expectedResponse);

        R actualResponse = notificationController.getConsumerNotifications(invalidUserId);

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).getConsumerNotifications(invalidUserId);
    }

    @Test
    public void testAddNotification_InvalidRequest() {
        NotificationRequest invalidRequest = new NotificationRequest();
        R expectedResponse = R.error("Invalid request");
        when(notificationService.addNotification(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = notificationController.addNotification(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(notificationService, times(1)).addNotification(invalidRequest);
    }
}
