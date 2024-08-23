package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    @Test
    public void testNotification() {
        Notification notification = new Notification();
        
        // 初始化对象，断言初始值为null
        assertNull(notification.getId());
        assertNull(notification.getUserId());
        assertNull(notification.getUserType());
        assertNull(notification.getMessage());
        assertNull(notification.getType());
        assertNull(notification.getCreatedAt());
        assertNull(notification.getIsRead());

        // 设置属性值，断言设置值成功
        notification.setId(1);
        notification.setUserId(123);
        notification.setUserType("admin");
        notification.setMessage("Test message");
        notification.setType(1);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        notification.setCreatedAt(now);
        notification.setIsRead(false);

        assertEquals(1, notification.getId());
        assertEquals(123, notification.getUserId());
        assertEquals("admin", notification.getUserType());
        assertEquals("Test message", notification.getMessage());
        assertEquals(1, notification.getType());
        assertEquals(now, notification.getCreatedAt());
        assertFalse(notification.getIsRead());

        // 反面测试：设置负数id
        notification.setId(-1);
        assertEquals(-1, notification.getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Notification notification1 = new Notification();
        notification1.setId(1);
        notification1.setUserId(123);
        notification1.setUserType("admin");
        notification1.setMessage("Message 1");
        notification1.setType(1);
        notification1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        notification1.setIsRead(true);

        Notification notification2 = new Notification();
        notification2.setId(1);
        notification2.setUserId(123);
        notification2.setUserType("admin");
        notification2.setMessage("Message 1");
        notification2.setType(1);
        notification2.setCreatedAt(notification1.getCreatedAt());
        notification2.setIsRead(true);

        Notification notification3 = new Notification();
        notification3.setId(2);
        notification3.setUserId(456);
        notification3.setUserType("user");
        notification3.setMessage("Message 2");
        notification3.setType(2);
        notification3.setCreatedAt(notification1.getCreatedAt());
        notification3.setIsRead(false);

        // 测试equals方法
        assertEquals(notification1, notification2);
        assertNotEquals(notification1, notification3);

        // 测试hashCode方法
        assertEquals(notification1.hashCode(), notification2.hashCode());
        assertNotEquals(notification1.hashCode(), notification3.hashCode());
    }

    @Test
    public void testToString() {
        Notification notification = new Notification();
        notification.setId(1);
        notification.setUserId(123);
        notification.setUserType("admin");
        notification.setMessage("Test message");
        notification.setType(1);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        notification.setCreatedAt(now);
        notification.setIsRead(false);

        String notificationString = notification.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(notificationString.contains("id=1"));
        assertTrue(notificationString.contains("userId=123"));
        assertTrue(notificationString.contains("userType=admin"));
        assertTrue(notificationString.contains("message=Test message"));
        assertTrue(notificationString.contains("type=1"));
        assertTrue(notificationString.contains("createdAt=" + now.toString()));
        assertTrue(notificationString.contains("isRead=false"));
    }
}
