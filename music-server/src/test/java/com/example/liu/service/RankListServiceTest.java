package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.RankListMapper;
import com.example.liu.model.domain.RankList;
import com.example.liu.model.request.RankListRequest;
import com.example.liu.service.impl.RankListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class RankListServiceTest {

    @Mock
    private RankListMapper rankMapper;

    @InjectMocks
    private RankListServiceImpl rankListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRank_Success() {
        RankListRequest request = new RankListRequest();
        request.setSongListId(1L);
        request.setConsumerId(1L);
        request.setScore(5);

        RankList rankList = new RankList();
        rankList.setSongListId(1L);
        rankList.setConsumerId(1L);
        rankList.setScore(5);

        doReturn(1).when(rankMapper).insert(any(RankList.class));

        R result = rankListService.addRank(request);

        assertEquals(200, result.getCode());  // Modified to match your R class
        assertEquals("评价成功", result.getMessage());
        verify(rankMapper).insert(any(RankList.class));
    }

    @Test
    public void testAddRank_Failure() {
        RankListRequest request = new RankListRequest();
        request.setSongListId(1L);
        request.setConsumerId(1L);
        request.setScore(5);

        doReturn(0).when(rankMapper).insert(any(RankList.class));

        R result = rankListService.addRank(request);

        assertEquals(200, result.getCode());  
        assertEquals("评价失败", result.getMessage());
    }

    @Test
    public void testRankOfSongListId_HasRanks() {
        Long songListId = 1L;
        Long rankNum = 3L;

        doReturn(rankNum).when(rankMapper).selectCount(any(QueryWrapper.class));
        
        R result = rankListService.rankOfSongListId(songListId);

        assertEquals(200, result.getCode());          
        assertEquals(0L, result.getData());
    }

    @Test
    public void testRankOfSongListId_NoRanks() {
        Long songListId = 1L;

        doReturn(0L).when(rankMapper).selectCount(any(QueryWrapper.class));

        R result = rankListService.rankOfSongListId(songListId);

        assertEquals(200, result.getCode());  // Modified to match your R class
        assertEquals(0L, result.getData());
    }

    @Test
    public void testGetUserRank() {
        Long consumerId = 1L;
        Long songListId = 1L;
        Integer rank = 4;

        doReturn(rank).when(rankMapper).selectUserRank(any(Long.class), any(Long.class));

        R result = rankListService.getUserRank(consumerId, songListId);

        assertEquals(200, result.getCode());  // Modified to match your R class
        assertEquals(rank, result.getData());
    }
}
