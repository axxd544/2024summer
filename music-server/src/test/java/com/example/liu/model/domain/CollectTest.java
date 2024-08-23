package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;

public class CollectTest {

    @Test
    public void testCollect() {
        Collect collect = new Collect();

        // 初始化对象，断言初始值为null或默认值
        assertNull(collect.getId());
        assertNull(collect.getUserId());
        assertNull(collect.getType());
        assertNull(collect.getSongId());
        assertNull(collect.getSongListId());
        assertNull(collect.getCreateTime());

        // 设置属性值，断言设置值成功
        collect.setId(1);
        collect.setUserId(123);
        collect.setType((byte) 1);
        collect.setSongId(456);
        collect.setSongListId(789);
        Date now = new Date();
        collect.setCreateTime(now);

        assertEquals(1, collect.getId());
        assertEquals(123, collect.getUserId());
        assertEquals((byte)1, collect.getType());
        assertEquals(456, collect.getSongId());
        assertEquals(789, collect.getSongListId());
        assertEquals(now, collect.getCreateTime());
    }

    @Test
    public void testEqualsAndHashCode() {
        Collect collect1 = new Collect();
        collect1.setId(1);
        collect1.setUserId(123);
        collect1.setType((byte) 1);
        collect1.setSongId(456);
        collect1.setSongListId(789);

        Collect collect2 = new Collect();
        collect2.setId(1);
        collect2.setUserId(123);
        collect2.setType((byte) 1);
        collect2.setSongId(456);
        collect2.setSongListId(789);

        Collect collect3 = new Collect();
        collect3.setId(2);
        collect3.setUserId(124);
        collect3.setType((byte) 2);
        collect3.setSongId(457);
        collect3.setSongListId(790);

        // 测试equals方法
        assertEquals(collect1, collect2);
        assertNotEquals(collect1, collect3);

        // 测试hashCode方法
        assertEquals(collect1.hashCode(), collect2.hashCode());
        assertNotEquals(collect1.hashCode(), collect3.hashCode());
    }

    @Test
    public void testToString() {
        Collect collect = new Collect();
        collect.setId(1);
        collect.setUserId(123);
        collect.setType((byte) 1);
        collect.setSongId(456);
        collect.setSongListId(789);

        String collectString = collect.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(collectString.contains("id=1"));
        assertTrue(collectString.contains("userId=123"));
        assertTrue(collectString.contains("type=1"));
        assertTrue(collectString.contains("songId=456"));
        assertTrue(collectString.contains("songListId=789"));
    }
}

