package com.example.liu.model.domain;



import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    @Test
    public void testSong() {
        Song song = new Song();

        // 初始化对象，断言初始值为null或默认值
        assertNull(song.getId());
        assertNull(song.getSingerId());
        assertNull(song.getName());
        assertNull(song.getIntroduction());
        assertNull(song.getCreateTime());
        assertNull(song.getUpdateTime());
        assertNull(song.getPic());
        assertNull(song.getLyric());
        assertNull(song.getUrl());

        // 设置属性值，断言设置值成功
        song.setId(1);
        song.setSingerId(123);
        song.setName("Test Song");
        song.setIntroduction("Test Introduction");
        Date createTime = new Date();
        song.setCreateTime(createTime);
        Date updateTime = new Date();
        song.setUpdateTime(updateTime);
        song.setPic("testPic.jpg");
        song.setLyric("Test Lyric");
        song.setUrl("http://testurl.com");

        assertEquals(1, song.getId());
        assertEquals(123, song.getSingerId());
        assertEquals("Test Song", song.getName());
        assertEquals("Test Introduction", song.getIntroduction());
        assertEquals(createTime, song.getCreateTime());
        assertEquals(updateTime, song.getUpdateTime());
        assertEquals("testPic.jpg", song.getPic());
        assertEquals("Test Lyric", song.getLyric());
        assertEquals("http://testurl.com", song.getUrl());
    }

    @Test
    public void testToString() {
        Song song = new Song();
        song.setId(1);
        song.setSingerId(123);
        song.setName("Test Song");
        song.setIntroduction("Test Introduction");
        Date createTime = new Date();
        song.setCreateTime(createTime);
        Date updateTime = new Date();
        song.setUpdateTime(updateTime);
        song.setPic("testPic.jpg");
        song.setLyric("Test Lyric");
        song.setUrl("http://testurl.com");

        String songString = song.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(songString.contains("id=1"));
        assertTrue(songString.contains("singerId=123"));
        assertTrue(songString.contains("name=Test Song"));
        assertTrue(songString.contains("introduction=Test Introduction"));
        assertTrue(songString.contains("createTime=" + createTime.toString()));
        assertTrue(songString.contains("updateTime=" + updateTime.toString()));
        assertTrue(songString.contains("pic=testPic.jpg"));
        assertTrue(songString.contains("lyric=Test Lyric"));
        assertTrue(songString.contains("url=http://testurl.com"));
    }

    @Test
    public void testEqualsAndHashCode() {
        Song song1 = new Song();
        song1.setId(1);
        song1.setSingerId(123);
        song1.setName("Test Song");
        song1.setIntroduction("Test Introduction");
        Date createTime = new Date();
        song1.setCreateTime(createTime);
        Date updateTime = new Date();
        song1.setUpdateTime(updateTime);
        song1.setPic("testPic.jpg");
        song1.setLyric("Test Lyric");
        song1.setUrl("http://testurl.com");

        Song song2 = new Song();
        song2.setId(1);
        song2.setSingerId(123);
        song2.setName("Test Song");
        song2.setIntroduction("Test Introduction");
        song2.setCreateTime(createTime);
        song2.setUpdateTime(updateTime);
        song2.setPic("testPic.jpg");
        song2.setLyric("Test Lyric");
        song2.setUrl("http://testurl.com");

        Song song3 = new Song();
        song3.setId(2);
        song3.setSingerId(456);
        song3.setName("Another Song");
        song3.setIntroduction("Another Introduction");
        song3.setCreateTime(new Date());
        song3.setUpdateTime(new Date());
        song3.setPic("anotherPic.jpg");
        song3.setLyric("Another Lyric");
        song3.setUrl("http://anotherurl.com");

        // 测试equals方法
        assertEquals(song1, song2);
        assertNotEquals(song1, song3);

        // 测试hashCode方法
        assertEquals(song1.hashCode(), song2.hashCode());
        assertNotEquals(song1.hashCode(), song3.hashCode());
    }

    @Test
    public void testConstructorWithSongDeleted() {
        SongDeleted songDeleted = new SongDeleted();
        songDeleted.setId(1);
        songDeleted.setSingerId(123);
        songDeleted.setName("Test Song");
        songDeleted.setIntroduction("Test Introduction");
        Date createTime = new Date();
        songDeleted.setCreateTime(createTime);
        Date updateTime = new Date();
        songDeleted.setUpdateTime(updateTime);
        songDeleted.setPic("testPic.jpg");
        songDeleted.setLyric("Test Lyric");
        songDeleted.setUrl("http://testurl.com");

        Song song = new Song(songDeleted);

        // 验证通过 SongDeleted 构造的 Song 对象的属性
        assertEquals(1, song.getId());
        assertEquals(123, song.getSingerId());
        assertEquals("Test Song", song.getName());
        assertEquals("Test Introduction", song.getIntroduction());
        assertEquals(createTime, song.getCreateTime());
        assertEquals(updateTime, song.getUpdateTime());
        assertEquals("testPic.jpg", song.getPic());
        assertEquals("Test Lyric", song.getLyric());
        assertEquals("http://testurl.com", song.getUrl());
    }
}
