package com.example.liu.model.domain;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankListTest {

    @Test
    public void testRankList() {
        RankList rankList = new RankList();

        // 初始化对象，断言初始值
        assertNull(rankList.getId());
        assertNull(rankList.getSongListId());
        assertNull(rankList.getConsumerId());
        assertNull(rankList.getScore());

        // 设置属性值，断言设置值成功
        rankList.setId(1L);
        rankList.setSongListId(2L);
        rankList.setConsumerId(3L);
        rankList.setScore(100);

        assertEquals(1L, rankList.getId());
        assertEquals(2L, rankList.getSongListId());
        assertEquals(3L, rankList.getConsumerId());
        assertEquals(100, rankList.getScore());
    }

    @Test
    public void testToString() {
        RankList rankList = new RankList();
        rankList.setId(1L);
        rankList.setSongListId(2L);
        rankList.setConsumerId(3L);
        rankList.setScore(100);

        String rankListString = rankList.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(rankListString.contains("id=1"));
        assertTrue(rankListString.contains("songListId=2"));
        assertTrue(rankListString.contains("consumerId=3"));
        assertTrue(rankListString.contains("score=100"));
    }

    @Test
    public void testEqualsAndHashCode() {
        RankList rankList1 = new RankList();
        rankList1.setId(1L);
        rankList1.setSongListId(2L);
        rankList1.setConsumerId(3L);
        rankList1.setScore(100);

        RankList rankList2 = new RankList();
        rankList2.setId(1L);
        rankList2.setSongListId(2L);
        rankList2.setConsumerId(3L);
        rankList2.setScore(100);

        RankList rankList3 = new RankList();
        rankList3.setId(2L);
        rankList3.setSongListId(3L);
        rankList3.setConsumerId(4L);
        rankList3.setScore(200);

        // 测试equals方法
        assertEquals(rankList1, rankList2);
        assertNotEquals(rankList1, rankList3);

        // 测试hashCode方法
        assertEquals(rankList1.hashCode(), rankList2.hashCode());
        assertNotEquals(rankList1.hashCode(), rankList3.hashCode());
    }
}

