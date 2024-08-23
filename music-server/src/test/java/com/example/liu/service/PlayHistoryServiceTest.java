package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.mapper.PlayHistoryMapper;
import com.example.liu.mapper.SongMapper;
import com.example.liu.model.domain.Consumer;
import com.example.liu.model.domain.PlayHistory;
import com.example.liu.model.domain.Song;
import com.example.liu.model.request.PlayHistoryRequest;
import com.example.liu.service.impl.PlayHistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class PlayHistoryServiceTest {

    @Mock
    private PlayHistoryMapper playHistoryMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private SongMapper songMapper;

    @InjectMocks
    private PlayHistoryServiceImpl playHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecodePlayHistory_UpdateSuccess() {
        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setUserId(1);
        request.setSongId(1);
        request.setPlayCount(1);
        request.setPlayTimeStamp(new Timestamp(Instant.now().toEpochMilli()));

        Consumer consumer = new Consumer();
        Song song = new Song();
        PlayHistory existingPlayHistory = new PlayHistory();
        existingPlayHistory.setPlayCount(1);

        doReturn(consumer).when(consumerMapper).selectById(any(Integer.class));
        doReturn(song).when(songMapper).selectById(any(Integer.class));
        doReturn(existingPlayHistory).when(playHistoryMapper).selectOne(any(QueryWrapper.class));
        doReturn(1).when(playHistoryMapper).updateById(any(PlayHistory.class));

        R result = playHistoryService.recodePlayHistory(request);

        assertEquals(200, result.getCode());
        assertEquals("更新播放历史成功", result.getMessage());
        verify(playHistoryMapper).updateById(any(PlayHistory.class));
    }

    @Test
    public void testRecodePlayHistory_AddSuccess() {
        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setUserId(1);
        request.setSongId(1);
        request.setPlayCount(1);
        request.setPlayTimeStamp(new Timestamp(Instant.now().toEpochMilli()));

        Consumer consumer = new Consumer();
        Song song = new Song();

        doReturn(consumer).when(consumerMapper).selectById(any(Integer.class));
        doReturn(song).when(songMapper).selectById(any(Integer.class));
        doReturn(null).when(playHistoryMapper).selectOne(any(QueryWrapper.class));
        doReturn(1).when(playHistoryMapper).insert(any(PlayHistory.class));

        R result = playHistoryService.recodePlayHistory(request);

        assertEquals(200, result.getCode());
        assertEquals("添加播放历史成功", result.getMessage());
        verify(playHistoryMapper).insert(any(PlayHistory.class));
    }

    @Test
    public void testRecodePlayHistory_UserNotFound() {
        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setUserId(1);
        request.setSongId(1);

        doReturn(null).when(consumerMapper).selectById(any(Integer.class));

        R result = playHistoryService.recodePlayHistory(request);

        assertEquals(200, result.getCode());
        assertEquals("不存在该用户", result.getMessage());
    }

    @Test
    public void testRecodePlayHistory_SongNotFound() {
        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setUserId(1);
        request.setSongId(1);

        Consumer consumer = new Consumer();
        doReturn(consumer).when(consumerMapper).selectById(any(Integer.class));
        doReturn(null).when(songMapper).selectById(any(Integer.class));

        R result = playHistoryService.recodePlayHistory(request);

        assertEquals(200, result.getCode());
        assertEquals("不存在该歌曲", result.getMessage());
    }

    @Test
    public void testGetPlayHistory_Success() {
        int userId = 1;
        PlayHistory playHistory = new PlayHistory();
        doReturn(Collections.singletonList(playHistory)).when(playHistoryMapper).selectList(any(QueryWrapper.class));

        R result = playHistoryService.getPlayHistory(userId);

        assertEquals(200, result.getCode());
        assertEquals(Collections.singletonList(playHistory), result.getData());
    }

    @Test
    public void testGetPlayHistory_NoData() {
        int userId = 1;
        doReturn(Collections.emptyList()).when(playHistoryMapper).selectList(any(QueryWrapper.class));

        R result = playHistoryService.getPlayHistory(userId);

        assertEquals(200, result.getCode());
        assertEquals("当前没有播放历史记录", result.getMessage());
    }
}
