package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListSongConsumerTest {

    @Test
    void testListSongConsumerCreation() {
        // 创建一个 ListSongConsumer 对象并设置属性
        ListSongConsumer listSongConsumer = new ListSongConsumer();
        listSongConsumer.setId(1);
        listSongConsumer.setSongId(100);
        listSongConsumer.setSongListConsumerId(200);

        // 验证属性是否正确设置
        assertEquals(1, listSongConsumer.getId());
        assertEquals(100, listSongConsumer.getSongId());
        assertEquals(200, listSongConsumer.getSongListConsumerId());
    }

    @Test
    void testToString() {
        // 创建一个 ListSongConsumer 对象并设置属性
        ListSongConsumer listSongConsumer = new ListSongConsumer();
        listSongConsumer.setId(1);
        listSongConsumer.setSongId(100);
        listSongConsumer.setSongListConsumerId(200);

        // 使用 toString() 方法并验证输出格式
        assertTrue(listSongConsumer.toString().contains("id=1"));
        assertTrue(listSongConsumer.toString().contains("songId=100"));
        assertTrue(listSongConsumer.toString().contains("songListConsumerId=200"));
    }

    @Test
    void testListSongConsumerEquality() {
        // 创建两个相同的 ListSongConsumer 对象
        ListSongConsumer listSongConsumer1 = new ListSongConsumer();
        listSongConsumer1.setId(1);
        listSongConsumer1.setSongId(100);
        listSongConsumer1.setSongListConsumerId(200);

        ListSongConsumer listSongConsumer2 = new ListSongConsumer();
        listSongConsumer2.setId(1);
        listSongConsumer2.setSongId(100);
        listSongConsumer2.setSongListConsumerId(200);

        // 验证两个对象的等同性
        assertEquals(listSongConsumer1, listSongConsumer2);

        // 修改一个对象的属性后验证不等同性
        listSongConsumer2.setId(2);
        assertNotEquals(listSongConsumer1, listSongConsumer2);
    }
}
