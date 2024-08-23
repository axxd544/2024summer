package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.Timestamp;
import java.util.Calendar;

public class ComplaintsTest {

    @Test
    public void testComplaints() {
        Complaints complaints = new Complaints();

        // 初始化对象，断言初始值为默认值或null
        assertEquals(0, complaints.getId());
        assertEquals(0, complaints.getUserId());
        assertNull(complaints.getTargetType());
        assertEquals(0, complaints.getTargetId());
        assertNull(complaints.getReason());
        assertNull(complaints.getStatus());
        assertNull(complaints.getCreateAt());
        assertNull(complaints.getUpdateAt());

        // 设置属性值，断言设置值成功
        complaints.setId(1);
        complaints.setUserId(123);
        complaints.setTargetType(Complaints.TargetType.SONG);
        complaints.setTargetId(456);
        complaints.setReason("This is a complaint reason.");
        complaints.setStatus(Complaints.Status.PENDING);
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        complaints.setCreateAt(now);
        complaints.setUpdateAt(now);

        assertEquals(1, complaints.getId());
        assertEquals(123, complaints.getUserId());
        assertEquals(Complaints.TargetType.SONG, complaints.getTargetType());
        assertEquals(456, complaints.getTargetId());
        assertEquals("This is a complaint reason.", complaints.getReason());
        assertEquals(Complaints.Status.PENDING, complaints.getStatus());
        assertEquals(now, complaints.getCreateAt());
        assertEquals(now, complaints.getUpdateAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Complaints complaints1 = new Complaints();
        complaints1.setId(1);
        complaints1.setUserId(123);
        complaints1.setTargetType(Complaints.TargetType.SONG);
        complaints1.setTargetId(456);
        complaints1.setReason("This is a complaint reason.");
        complaints1.setStatus(Complaints.Status.PENDING);
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        complaints1.setCreateAt(now);
        complaints1.setUpdateAt(now);

        Complaints complaints2 = new Complaints();
        complaints2.setId(1);
        complaints2.setUserId(123);
        complaints2.setTargetType(Complaints.TargetType.SONG);
        complaints2.setTargetId(456);
        complaints2.setReason("This is a complaint reason.");
        complaints2.setStatus(Complaints.Status.PENDING);
        complaints2.setCreateAt(now);
        complaints2.setUpdateAt(now);

        Complaints complaints3 = new Complaints();
        complaints3.setId(2);
        complaints3.setUserId(124);
        complaints3.setTargetType(Complaints.TargetType.PLAYLIST);
        complaints3.setTargetId(457);
        complaints3.setReason("Different reason.");
        complaints3.setStatus(Complaints.Status.REVIEWED);
        complaints3.setCreateAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        complaints3.setUpdateAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        // 测试equals方法
        assertEquals(complaints1, complaints2);
        assertNotEquals(complaints1, complaints3);

        // 测试hashCode方法
        assertEquals(complaints1.hashCode(), complaints2.hashCode());
        assertNotEquals(complaints1.hashCode(), complaints3.hashCode());
    }

    @Test
    public void testToString() {
        Complaints complaints = new Complaints();
        complaints.setId(1);
        complaints.setUserId(123);
        complaints.setTargetType(Complaints.TargetType.SONG);
        complaints.setTargetId(456);
        complaints.setReason("This is a complaint reason.");
        complaints.setStatus(Complaints.Status.PENDING);
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        complaints.setCreateAt(now);
        complaints.setUpdateAt(now);

        String complaintsString = complaints.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(complaintsString.contains("id=1"));
        assertTrue(complaintsString.contains("userId=123"));
        assertTrue(complaintsString.contains("targetType=SONG"));
        assertTrue(complaintsString.contains("targetId=456"));
        assertTrue(complaintsString.contains("reason=This is a complaint reason."));
        assertTrue(complaintsString.contains("status=PENDING"));
        assertTrue(complaintsString.contains("createAt="));
        assertTrue(complaintsString.contains("updateAt="));
    }
}

