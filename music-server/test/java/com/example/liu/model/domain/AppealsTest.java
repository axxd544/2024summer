package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

public class AppealsTest {

    @Test
    public void testAppeals() {
        Appeals appeals = new Appeals();
        
        // 初始化对象，断言初始值为null或0
        assertEquals(0, appeals.getId());
        assertEquals(0, appeals.getComplaintId());
        assertEquals(0, appeals.getUserId());
        assertNull(appeals.getReason());
        assertNull(appeals.getStatus());
        assertNull(appeals.getCreateAt());
        assertNull(appeals.getUpdateAt());

        // 设置属性值，断言设置值成功
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        appeals.setId(1);
        appeals.setComplaintId(100);
        appeals.setUserId(200);
        appeals.setReason("Test reason");
        appeals.setStatus(Appeals.Status.PENDING);
        appeals.setCreateAt(currentTime);
        appeals.setUpdateAt(currentTime);

        assertEquals(1, appeals.getId());
        assertEquals(100, appeals.getComplaintId());
        assertEquals(200, appeals.getUserId());
        assertEquals("Test reason", appeals.getReason());
        assertEquals(Appeals.Status.PENDING, appeals.getStatus());
        assertEquals(currentTime, appeals.getCreateAt());
        assertEquals(currentTime, appeals.getUpdateAt());

        // 反面测试：设置负数id
        appeals.setId(-1);
        assertEquals(-1, appeals.getId());
    }

    @Test
    public void testToString() {
        Appeals appeals = new Appeals();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        appeals.setId(1);
        appeals.setComplaintId(100);
        appeals.setUserId(200);
        appeals.setReason("Test reason");
        appeals.setStatus(Appeals.Status.PENDING);
        appeals.setCreateAt(currentTime);
        appeals.setUpdateAt(currentTime);

        String appealString = appeals.toString();

        // 使用contains来断言 toString 方法是否包含特定字段
        assertTrue(appealString.contains("id=1"));
        assertTrue(appealString.contains("complaintId=100"));
        assertTrue(appealString.contains("userId=200"));
        assertTrue(appealString.contains("reason=Test reason"));
        assertTrue(appealString.contains("status=PENDING"));
        assertTrue(appealString.contains("createAt=" + currentTime));
        assertTrue(appealString.contains("updateAt=" + currentTime));
    }
}
