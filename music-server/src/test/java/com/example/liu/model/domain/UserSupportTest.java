package com.example.liu.model.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserSupportTest {

    @Test
    public void testUserSupport() {
        UserSupport userSupport = new UserSupport();

        // 初始化对象，断言初始值为null
        assertNull(userSupport.getId());
        assertNull(userSupport.getCommentId());
        assertNull(userSupport.getUserId());

        // 设置属性值，断言设置值成功
        userSupport.setId(1);
        userSupport.setCommentId(100);
        userSupport.setUserId(200);

        assertEquals(1, userSupport.getId());
        assertEquals(100, userSupport.getCommentId());
        assertEquals(200, userSupport.getUserId());
    }

    @Test
    public void testToString() {
        UserSupport userSupport = new UserSupport();
        userSupport.setId(1);
        userSupport.setCommentId(100);
        userSupport.setUserId(200);

        String userSupportString = userSupport.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(userSupportString.contains("id=1"));
        assertTrue(userSupportString.contains("commentId=100"));
        assertTrue(userSupportString.contains("userId=200"));
    }

    @Test
    public void testEqualsAndHashCode() {
        UserSupport userSupport1 = new UserSupport();
        userSupport1.setId(1);
        userSupport1.setCommentId(100);
        userSupport1.setUserId(200);

        UserSupport userSupport2 = new UserSupport();
        userSupport2.setId(1);
        userSupport2.setCommentId(100);
        userSupport2.setUserId(200);

        UserSupport userSupport3 = new UserSupport();
        userSupport3.setId(2);
        userSupport3.setCommentId(101);
        userSupport3.setUserId(201);

        // 测试equals方法
        assertEquals(userSupport1, userSupport2);
        assertNotEquals(userSupport1, userSupport3);

        // 测试hashCode方法
        assertEquals(userSupport1.hashCode(), userSupport2.hashCode());
        assertNotEquals(userSupport1.hashCode(), userSupport3.hashCode());
    }
}
