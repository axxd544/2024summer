package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ListSongTest {

    @Test
    public void testListSong() {
        ListSong listSong = new ListSong();

        // 初始化对象，断言初始值为默认值或null
        assertNull(listSong.getId());
        assertNull(listSong.getSongId());
        assertNull(listSong.getSongListId());

        // 设置属性值，断言设置值成功
        listSong.setId(1);
        listSong.setSongId(100);
        listSong.setSongListId(200);
        assertEquals(1, listSong.getId());
        assertEquals(100, listSong.getSongId());
        assertEquals(200, listSong.getSongListId());

        listSong.setId(-1);
        assertEquals(-1, listSong.getId());

    }

    @Test
    public void testToString() {
        ListSong listSong = new ListSong();
        listSong.setId(1);
        listSong.setSongId(100);
        listSong.setSongListId(200);
        
        String listSongString = listSong.toString();
        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(listSongString.contains("id=1"));
        assertTrue(listSongString.contains("songId=100"));
        assertTrue(listSongString.contains("songListId=200"));

        listSong.setSongId(-100);
        assertEquals(-100, listSong.getSongId());

        
    }
}
