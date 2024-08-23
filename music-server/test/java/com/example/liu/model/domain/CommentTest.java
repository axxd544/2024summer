package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;

public class CommentTest {

    @Test
    public void testComment() {
        Comment comment = new Comment();

        // 初始化对象，断言初始值为null或默认值
        assertNull(comment.getId());
        assertNull(comment.getUserId());
        assertNull(comment.getSongId());
        assertNull(comment.getSongListId());
        assertNull(comment.getContent());
        assertNull(comment.getCreateTime());
        assertNull(comment.getType());
        assertNull(comment.getUp());
        assertNull(comment.getSongListConsumerId());

        // 设置属性值，断言设置值成功
        comment.setId(1);
        comment.setUserId(123);
        comment.setSongId(456);
        comment.setSongListId(789);
        comment.setContent("This is a comment");
        Date now = new Date();
        comment.setCreateTime(now);
        comment.setType((byte) 1);
        comment.setUp(100);
        comment.setSongListConsumerId(999);

        assertEquals(1, comment.getId());
        assertEquals(123, comment.getUserId());
        assertEquals(456, comment.getSongId());
        assertEquals(789, comment.getSongListId());
        assertEquals("This is a comment", comment.getContent());
        assertEquals(now, comment.getCreateTime());
        assertEquals((byte)1, comment.getType());
        assertEquals(100, comment.getUp());
        assertEquals(999, comment.getSongListConsumerId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setUserId(123);
        comment1.setSongId(456);
        comment1.setSongListId(789);
        comment1.setContent("This is a comment");
        comment1.setType((byte) 1);
        comment1.setUp(100);
        comment1.setSongListConsumerId(999);

        Comment comment2 = new Comment();
        comment2.setId(1);
        comment2.setUserId(123);
        comment2.setSongId(456);
        comment2.setSongListId(789);
        comment2.setContent("This is a comment");
        comment2.setType((byte) 1);
        comment2.setUp(100);
        comment2.setSongListConsumerId(999);

        Comment comment3 = new Comment();
        comment3.setId(2);
        comment3.setUserId(124);
        comment3.setSongId(457);
        comment3.setSongListId(790);
        comment3.setContent("Another comment");
        comment3.setType((byte) 2);
        comment3.setUp(101);
        comment3.setSongListConsumerId(1000);

        // 测试equals方法
        assertEquals(comment1, comment2);
        assertNotEquals(comment1, comment3);

        // 测试hashCode方法
        assertEquals(comment1.hashCode(), comment2.hashCode());
        assertNotEquals(comment1.hashCode(), comment3.hashCode());
    }

    @Test
    public void testToString() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setUserId(123);
        comment.setSongId(456);
        comment.setSongListId(789);
        comment.setContent("This is a comment");
        comment.setType((byte) 1);
        comment.setUp(100);
        comment.setSongListConsumerId(999);

        String commentString = comment.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(commentString.contains("id=1"));
        assertTrue(commentString.contains("userId=123"));
        assertTrue(commentString.contains("songId=456"));
        assertTrue(commentString.contains("songListId=789"));
        assertTrue(commentString.contains("content=This is a comment"));
        assertTrue(commentString.contains("type=1"));
        assertTrue(commentString.contains("up=100"));
        assertTrue(commentString.contains("songListConsumerId=999"));
    }
}

