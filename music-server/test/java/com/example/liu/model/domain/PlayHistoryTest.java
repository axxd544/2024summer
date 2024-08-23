package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;


public class PlayHistoryTest {

    @Test
    public void testPlayHistory() {
        PlayHistory playHistory = new PlayHistory();

        // 初始化对象，断言初始值
        assertEquals(0, playHistory.getId());
        assertEquals(0, playHistory.getUserId());
        assertEquals(0, playHistory.getSongId());
        assertEquals(0, playHistory.getPlayCount());
        assertNull(playHistory.getPlayTimeStamp());

        // 设置属性值，断言设置值成功
        playHistory.setId(1);
        playHistory.setUserId(123);
        playHistory.setSongId(456);
        playHistory.setPlayCount(10);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        playHistory.setPlayTimeStamp(now);

        assertEquals(1, playHistory.getId());
        assertEquals(123, playHistory.getUserId());
        assertEquals(456, playHistory.getSongId());
        assertEquals(10, playHistory.getPlayCount());
        assertEquals(now, playHistory.getPlayTimeStamp());
    }

    @Test
    public void testToString() {
        PlayHistory playHistory = new PlayHistory();
        playHistory.setId(1);
        playHistory.setUserId(123);
        playHistory.setSongId(456);
        playHistory.setPlayCount(10);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        playHistory.setPlayTimeStamp(now);

        String playHistoryString = playHistory.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(playHistoryString.contains("id=1"));
        assertTrue(playHistoryString.contains("userId=123"));
        assertTrue(playHistoryString.contains("songId=456"));
        assertTrue(playHistoryString.contains("playCount=10"));
        assertTrue(playHistoryString.contains("playTimeStamp=" + now.toString()));
    }

    @Test
    public void testEqualsAndHashCode() {
        PlayHistory playHistory1 = new PlayHistory();
        playHistory1.setId(1);
        playHistory1.setUserId(123);
        playHistory1.setSongId(456);
        playHistory1.setPlayCount(10);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        playHistory1.setPlayTimeStamp(now);

        PlayHistory playHistory2 = new PlayHistory();
        playHistory2.setId(1);
        playHistory2.setUserId(123);
        playHistory2.setSongId(456);
        playHistory2.setPlayCount(10);
        playHistory2.setPlayTimeStamp(now);

        PlayHistory playHistory3 = new PlayHistory();
        playHistory3.setId(2);
        playHistory3.setUserId(456);
        playHistory3.setSongId(789);
        playHistory3.setPlayCount(20);
        playHistory3.setPlayTimeStamp(now);

        // 测试equals方法
        assertEquals(playHistory1, playHistory2);
        assertNotEquals(playHistory1, playHistory3);

        // 测试hashCode方法
        assertEquals(playHistory1.hashCode(), playHistory2.hashCode());
        assertNotEquals(playHistory1.hashCode(), playHistory3.hashCode());
    }
}
