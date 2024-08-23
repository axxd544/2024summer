package com.example.liu.model.domain;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SongListConsumerTest {

    @Test
    public void testSongListConsumer() {
        // 创建 SongListConsumer 实例
        SongListConsumer songListConsumer = new SongListConsumer();

        // 正向测试：初始值为 null
        assertNull(songListConsumer.getId());
        assertNull(songListConsumer.getUserId());
        assertNull(songListConsumer.getTitle());
        assertNull(songListConsumer.getPic());
        assertNull(songListConsumer.getStyle());
        assertNull(songListConsumer.getIntroduction());

        // 设置属性值
        songListConsumer.setId(1);
        songListConsumer.setUserId(123);
        songListConsumer.setTitle("My Playlist");
        songListConsumer.setPic("playlist.jpg");
        songListConsumer.setStyle("Pop");
        songListConsumer.setIntroduction("This is a pop music playlist.");

        // 验证设置值成功
        assertEquals(1, songListConsumer.getId());
        assertEquals(123, songListConsumer.getUserId());
        assertEquals("My Playlist", songListConsumer.getTitle());
        assertEquals("playlist.jpg", songListConsumer.getPic());
        assertEquals("Pop", songListConsumer.getStyle());
        assertEquals("This is a pop music playlist.", songListConsumer.getIntroduction());
    }

    @Test
    public void testNegativeValues() {
        // 创建 SongListConsumer 实例
        SongListConsumer songListConsumer = new SongListConsumer();

        // 设置负值
        songListConsumer.setId(-1);
        songListConsumer.setUserId(-123);
        songListConsumer.setTitle("Negative Playlist");
        songListConsumer.setPic("negative.jpg");
        songListConsumer.setStyle("Rock");
        songListConsumer.setIntroduction("This is a rock music playlist.");

        // 验证是否可以接受负值
        assertEquals(-1, songListConsumer.getId());
        assertEquals(-123, songListConsumer.getUserId());
        assertEquals("Negative Playlist", songListConsumer.getTitle());
        assertEquals("negative.jpg", songListConsumer.getPic());
        assertEquals("Rock", songListConsumer.getStyle());
        assertEquals("This is a rock music playlist.", songListConsumer.getIntroduction());
    }

    @Test
    public void testToString() {
        // 创建 SongListConsumer 实例并设置属性
        SongListConsumer songListConsumer = new SongListConsumer();
        songListConsumer.setId(1);
        songListConsumer.setUserId(123);
        songListConsumer.setTitle("My Playlist");
        songListConsumer.setPic("playlist.jpg");
        songListConsumer.setStyle("Pop");
        songListConsumer.setIntroduction("This is a pop music playlist.");

        // 验证 toString() 的输出
        String toStringOutput = songListConsumer.toString();
        assertTrue(toStringOutput.contains("id=1"));
        assertTrue(toStringOutput.contains("userId=123"));
        assertTrue(toStringOutput.contains("title=My Playlist"));
        assertTrue(toStringOutput.contains("pic=playlist.jpg"));
        assertTrue(toStringOutput.contains("style=Pop"));
        assertTrue(toStringOutput.contains("introduction=This is a pop music playlist."));
    }

    @Test
    public void testConstructorWithSongListConsumerDeleted() {
        // 假设 SongListConsumerDeleted 是一个已经存在的类
        SongListConsumerDeleted deleted = new SongListConsumerDeleted();
        deleted.setId(2);
        deleted.setUserId(456);
        deleted.setTitle("Deleted Playlist");
        deleted.setPic("deleted.jpg");
        deleted.setStyle("Jazz");
        deleted.setIntroduction("This is a jazz music playlist.");

        // 使用 SongListConsumerDeleted 实例化 SongListConsumer
        SongListConsumer songListConsumer = new SongListConsumer(deleted);

        // 验证属性值正确复制
        assertEquals(2, songListConsumer.getId());
        assertEquals(456, songListConsumer.getUserId());
        assertEquals("Deleted Playlist", songListConsumer.getTitle());
        assertEquals("deleted.jpg", songListConsumer.getPic());
        assertEquals("Jazz", songListConsumer.getStyle());
        assertEquals("This is a jazz music playlist.", songListConsumer.getIntroduction());
    }
}
