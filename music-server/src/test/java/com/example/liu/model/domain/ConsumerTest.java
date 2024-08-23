package com.example.liu.model.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;

public class ConsumerTest {

    @Test
    public void testConsumer() {
        Consumer consumer = new Consumer();

        // 初始化对象，断言初始值为默认值或null
        assertNull(consumer.getId());
        assertNull(consumer.getUsername());
        assertNull(consumer.getPassword());
        assertNull(consumer.getSex());
        assertNull(consumer.getPhoneNum());
        assertNull(consumer.getEmail());
        assertNull(consumer.getBirth());
        assertNull(consumer.getIntroduction());
        assertNull(consumer.getLocation());
        assertNull(consumer.getAvator());
        assertNull(consumer.getCreateTime());
        assertNull(consumer.getUpdateTime());

        // 设置属性值，断言设置值成功
        consumer.setId(1);
        consumer.setUsername("testuser");
        consumer.setPassword("password");
        consumer.setSex((byte) 1);
        consumer.setPhoneNum("1234567890");
        consumer.setEmail("testuser@example.com");
        consumer.setBirth(new Date());
        consumer.setIntroduction("This is a test user.");
        consumer.setLocation("Test Location");
        consumer.setAvator("test-avatar.png");
        Date now = new Date();
        consumer.setCreateTime(now);
        consumer.setUpdateTime(now);

        assertEquals(1, consumer.getId());
        assertEquals("testuser", consumer.getUsername());
        assertEquals("password", consumer.getPassword());
        assertEquals((byte) 1, consumer.getSex());
        assertEquals("1234567890", consumer.getPhoneNum());
        assertEquals("testuser@example.com", consumer.getEmail());
        assertNotNull(consumer.getBirth());
        assertEquals("This is a test user.", consumer.getIntroduction());
        assertEquals("Test Location", consumer.getLocation());
        assertEquals("test-avatar.png", consumer.getAvator());
        assertEquals(now, consumer.getCreateTime());
        assertEquals(now, consumer.getUpdateTime());
    }

    @Test
    public void testEqualsAndHashCode() {
        Consumer consumer1 = new Consumer();
        consumer1.setId(1);
        consumer1.setUsername("testuser");
        consumer1.setPassword("password");
        consumer1.setSex((byte) 1);
        consumer1.setPhoneNum("1234567890");
        consumer1.setEmail("testuser@example.com");
        consumer1.setBirth(new Date());
        consumer1.setIntroduction("This is a test user.");
        consumer1.setLocation("Test Location");
        consumer1.setAvator("test-avatar.png");
        Date now = new Date();
        consumer1.setCreateTime(now);
        consumer1.setUpdateTime(now);

        Consumer consumer2 = new Consumer();
        consumer2.setId(1);
        consumer2.setUsername("testuser");
        consumer2.setPassword("password");
        consumer2.setSex((byte) 1);
        consumer2.setPhoneNum("1234567890");
        consumer2.setEmail("testuser@example.com");
        consumer2.setBirth(new Date());
        consumer2.setIntroduction("This is a test user.");
        consumer2.setLocation("Test Location");
        consumer2.setAvator("test-avatar.png");
        consumer2.setCreateTime(now);
        consumer2.setUpdateTime(now);

        Consumer consumer3 = new Consumer();
        consumer3.setId(2);
        consumer3.setUsername("differentuser");
        consumer3.setPassword("newpassword");
        consumer3.setSex((byte) 2);
        consumer3.setPhoneNum("0987654321");
        consumer3.setEmail("differentuser@example.com");
        consumer3.setBirth(new Date());
        consumer3.setIntroduction("Different user.");
        consumer3.setLocation("Different Location");
        consumer3.setAvator("different-avatar.png");
        consumer3.setCreateTime(new Date());
        consumer3.setUpdateTime(new Date());

        // 测试equals方法
        assertEquals(consumer1, consumer2);
        assertNotEquals(consumer1, consumer3);

        // 测试hashCode方法
        assertEquals(consumer1.hashCode(), consumer2.hashCode());
        assertNotEquals(consumer1.hashCode(), consumer3.hashCode());
    }

    @Test
    public void testToString() {
        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setUsername("testuser");
        consumer.setPassword("password");
        consumer.setSex((byte) 1);
        consumer.setPhoneNum("1234567890");
        consumer.setEmail("testuser@example.com");
        consumer.setBirth(new Date());
        consumer.setIntroduction("This is a test user.");
        consumer.setLocation("Test Location");
        consumer.setAvator("test-avatar.png");
        Date now = new Date();
        consumer.setCreateTime(now);
        consumer.setUpdateTime(now);

        String consumerString = consumer.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(consumerString.contains("id=1"));
        assertTrue(consumerString.contains("username=testuser"));
        assertTrue(consumerString.contains("password=password"));
        assertTrue(consumerString.contains("sex=1"));
        assertTrue(consumerString.contains("phoneNum=1234567890"));
        assertTrue(consumerString.contains("email=testuser@example.com"));
        assertTrue(consumerString.contains("birth="));
        assertTrue(consumerString.contains("introduction=This is a test user."));
        assertTrue(consumerString.contains("location=Test Location"));
        assertTrue(consumerString.contains("avator=test-avatar.png"));
        assertTrue(consumerString.contains("createTime="));
        assertTrue(consumerString.contains("updateTime="));
    }
}
