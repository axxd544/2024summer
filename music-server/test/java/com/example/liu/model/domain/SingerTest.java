package com.example.liu.model.domain;



import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SingerTest {

    @Test
    public void testSinger() {
        Singer singer = new Singer();

        // 初始化对象，断言初始值为null或默认值
        assertNull(singer.getId());
        assertNull(singer.getName());
        assertNull(singer.getSex());
        assertNull(singer.getPic());
        assertNull(singer.getBirth());
        assertNull(singer.getLocation());
        assertNull(singer.getIntroduction());

        // 设置属性值，断言设置值成功
        singer.setId(1);
        singer.setName("Test Singer");
        singer.setSex((byte) 1);
        singer.setPic("testPic.jpg");
        Date birthDate = new Date();
        singer.setBirth(birthDate);
        singer.setLocation("Test Location");
        singer.setIntroduction("Test Introduction");

        assertEquals(1, singer.getId());
        assertEquals("Test Singer", singer.getName());
        assertEquals((byte) 1, singer.getSex());
        assertEquals("testPic.jpg", singer.getPic());
        assertEquals(birthDate, singer.getBirth());
        assertEquals("Test Location", singer.getLocation());
        assertEquals("Test Introduction", singer.getIntroduction());
    }

    @Test
    public void testToString() {
        Singer singer = new Singer();
        singer.setId(1);
        singer.setName("Test Singer");
        singer.setSex((byte) 1);
        singer.setPic("testPic.jpg");
        Date birthDate = new Date();
        singer.setBirth(birthDate);
        singer.setLocation("Test Location");
        singer.setIntroduction("Test Introduction");

        String singerString = singer.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(singerString.contains("id=1"));
        assertTrue(singerString.contains("name=Test Singer"));
        assertTrue(singerString.contains("sex=1"));
        assertTrue(singerString.contains("pic=testPic.jpg"));
        assertTrue(singerString.contains("birth=" + birthDate.toString()));
        assertTrue(singerString.contains("location=Test Location"));
        assertTrue(singerString.contains("introduction=Test Introduction"));
    }

    @Test
    public void testEqualsAndHashCode() {
        Singer singer1 = new Singer();
        singer1.setId(1);
        singer1.setName("Test Singer");
        singer1.setSex((byte) 1);
        singer1.setPic("testPic.jpg");
        Date birthDate = new Date();
        singer1.setBirth(birthDate);
        singer1.setLocation("Test Location");
        singer1.setIntroduction("Test Introduction");

        Singer singer2 = new Singer();
        singer2.setId(1);
        singer2.setName("Test Singer");
        singer2.setSex((byte) 1);
        singer2.setPic("testPic.jpg");
        singer2.setBirth(birthDate);
        singer2.setLocation("Test Location");
        singer2.setIntroduction("Test Introduction");

        Singer singer3 = new Singer();
        singer3.setId(2);
        singer3.setName("Another Singer");
        singer3.setSex((byte) 0);
        singer3.setPic("anotherPic.jpg");
        singer3.setBirth(new Date());
        singer3.setLocation("Another Location");
        singer3.setIntroduction("Another Introduction");

        // 测试equals方法
        assertEquals(singer1, singer2);
        assertNotEquals(singer1, singer3);

        // 测试hashCode方法
        assertEquals(singer1.hashCode(), singer2.hashCode());
        assertNotEquals(singer1.hashCode(), singer3.hashCode());
    }
}
