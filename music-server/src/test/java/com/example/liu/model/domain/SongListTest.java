package com.example.liu.model.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongListTest {

    @Test
    public void testSongList() {
        SongList songList = new SongList();

        // 初始化对象，断言初始值为null
        assertNull(songList.getId());
        assertNull(songList.getTitle());
        assertNull(songList.getPic());
        assertNull(songList.getStyle());
        assertNull(songList.getIntroduction());

        // 设置属性值，断言设置值成功
        songList.setId(1);
        songList.setTitle("Test Title");
        songList.setPic("testPic.jpg");
        songList.setStyle("Pop");
        songList.setIntroduction("Test Introduction");

        assertEquals(1, songList.getId());
        assertEquals("Test Title", songList.getTitle());
        assertEquals("testPic.jpg", songList.getPic());
        assertEquals("Pop", songList.getStyle());
        assertEquals("Test Introduction", songList.getIntroduction());
    }

    @Test
    public void testToString() {
        SongList songList = new SongList();
        songList.setId(1);
        songList.setTitle("Test Title");
        songList.setPic("testPic.jpg");
        songList.setStyle("Pop");
        songList.setIntroduction("Test Introduction");

        String songListString = songList.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(songListString.contains("id=1"));
        assertTrue(songListString.contains("title=Test Title"));
        assertTrue(songListString.contains("pic=testPic.jpg"));
        assertTrue(songListString.contains("style=Pop"));
        assertTrue(songListString.contains("introduction=Test Introduction"));
    }

    @Test
    public void testEqualsAndHashCode() {
        SongList songList1 = new SongList();
        songList1.setId(1);
        songList1.setTitle("Test Title");
        songList1.setPic("testPic.jpg");
        songList1.setStyle("Pop");
        songList1.setIntroduction("Test Introduction");

        SongList songList2 = new SongList();
        songList2.setId(1);
        songList2.setTitle("Test Title");
        songList2.setPic("testPic.jpg");
        songList2.setStyle("Pop");
        songList2.setIntroduction("Test Introduction");

        SongList songList3 = new SongList();
        songList3.setId(2);
        songList3.setTitle("Another Title");
        songList3.setPic("anotherPic.jpg");
        songList3.setStyle("Rock");
        songList3.setIntroduction("Another Introduction");

        // 测试equals方法
        assertEquals(songList1, songList2);
        assertNotEquals(songList1, songList3);

        // 测试hashCode方法
        assertEquals(songList1.hashCode(), songList2.hashCode());
        assertNotEquals(songList1.hashCode(), songList3.hashCode());
    }
}
