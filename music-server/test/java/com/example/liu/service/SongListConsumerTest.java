package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.liu.common.R;
import com.example.liu.mapper.SongListConsumerDeletedMapper;
import com.example.liu.mapper.FollowMapper;
import com.example.liu.mapper.SongListConsumerMapper;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.model.domain.Consumer;
import com.example.liu.model.domain.Follow;
import com.example.liu.model.domain.SongListConsumer;
import com.example.liu.model.domain.SongListConsumerDeleted;
import com.example.liu.model.request.NotificationRequest;
import com.example.liu.model.request.SongListConsumerRequest;
import com.example.liu.service.impl.SongListConsumerImpl;
import com.example.liu.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongListConsumerTest {

    @InjectMocks
    private SongListConsumerImpl songListConsumerService;

    @Mock
    private SongListConsumerMapper songListConsumerMapper;

    @Mock
    private SongListConsumerDeletedMapper songListConsumerDeletedMapper;

    @Mock
    private FollowMapper followMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private NotificationService notificationService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateSongListConsumerMsg_Success() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        SongListConsumer songListConsumer = new SongListConsumer();
        songListConsumer.setUserId(1);
        songListConsumer.setTitle("Title");

        Consumer consumer = new Consumer();
        consumer.setUsername("Username");

        Follow follow = new Follow();
        follow.setFollowerId(2);

        when(songListConsumerMapper.updateById(any(SongListConsumer.class))).thenReturn(1);
        when(consumerMapper.selectOne(any(QueryWrapper.class))).thenReturn(consumer);
        when(followMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(follow));

        R result = songListConsumerService.updateSongListConsumerMsg(request);

        assertEquals(200, result.getCode());
        assertEquals("修改成功", result.getMessage());
    }

    @Test
    public void testUpdateSongListConsumerMsg_Failure() {
        SongListConsumerRequest request = new SongListConsumerRequest();

        when(songListConsumerMapper.updateById(any(SongListConsumer.class))).thenReturn(0);

        R result = songListConsumerService.updateSongListConsumerMsg(request);

        assertEquals(200, result.getCode());
        assertEquals("修改失败", result.getMessage());
    }

    @Test
    public void testDeleteSongListConsumer_Success() {
        SongListConsumer songListConsumer = new SongListConsumer();
        songListConsumer.setUserId(1);
        songListConsumer.setTitle("Title");

        Consumer consumer = new Consumer();
        consumer.setUsername("Username");

        Follow follow = new Follow();
        follow.setFollowerId(2);

        when(songListConsumerMapper.selectOne(any(QueryWrapper.class))).thenReturn(songListConsumer);
        when(consumerMapper.selectOne(any(QueryWrapper.class))).thenReturn(consumer);
        when(followMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(follow));
        when(songListConsumerMapper.deleteById(anyInt())).thenReturn(1);

        R result = songListConsumerService.deleteSongListConsumer(1);

        assertEquals(200, result.getCode());
        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteSongListConsumer_Failure() {
        when(songListConsumerMapper.deleteById(anyInt())).thenReturn(0);

        R result = songListConsumerService.deleteSongListConsumer(1);

        assertEquals(200, result.getCode());
        assertEquals("删除失败", result.getMessage());
    }

    @Test
    public void testAddSongListConsumer_Success() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        SongListConsumer songListConsumer = new SongListConsumer();
        songListConsumer.setUserId(1);
        songListConsumer.setTitle("Title");

        Consumer consumer = new Consumer();
        consumer.setUsername("Username");

        Follow follow = new Follow();
        follow.setFollowerId(2);

        when(songListConsumerMapper.insert(any(SongListConsumer.class))).thenReturn(1);
        when(consumerMapper.selectOne(any(QueryWrapper.class))).thenReturn(consumer);
        when(followMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.singletonList(follow));

        R result = songListConsumerService.addSongListConsumer(request);

        assertEquals(200, result.getCode());
        assertEquals("添加成功", result.getMessage());
    }

    @Test
    public void testAddSongListConsumer_Failure() {
        SongListConsumerRequest request = new SongListConsumerRequest();

        when(songListConsumerMapper.insert(any(SongListConsumer.class))).thenReturn(0);

        R result = songListConsumerService.addSongListConsumer(request);

        assertEquals(200, result.getCode());
        assertEquals("添加失败", result.getMessage());
    }

    @Test
    public void testUpdateSongListConsumerImg_Success() {
        when(multipartFile.getOriginalFilename()).thenReturn("img.jpg");
        when(songListConsumerMapper.updateById(any(SongListConsumer.class))).thenReturn(1);

    }

    @Test
    public void testUpdateSongListConsumerImg_Failure() {
        when(songListConsumerMapper.updateById(any(SongListConsumer.class))).thenReturn(0);
    }

    @Test
    public void testUnDeleteSongListConsumerByManager_Success() {
        SongListConsumerDeleted deleted = new SongListConsumerDeleted();
        deleted.setTitle("Title");

        when(songListConsumerDeletedMapper.selectById(anyInt())).thenReturn(deleted);
        when(songListConsumerMapper.insert(any(SongListConsumer.class))).thenReturn(1);
        when(songListConsumerDeletedMapper.deleteById(anyInt())).thenReturn(1);

        R result = songListConsumerService.unDeleteSongListConsumerByManager(1, 2, 3);

        assertEquals(200, result.getCode());
        assertEquals("撤销删除成功", result.getMessage());
    }

    @Test
    public void testUnDeleteSongListConsumerByManager_Failure() {
        when(songListConsumerDeletedMapper.selectById(anyInt())).thenReturn(null);

        R result = songListConsumerService.unDeleteSongListConsumerByManager(1, 2, 3);

        assertEquals(200, result.getCode());
        assertEquals("删除的歌单不存在", result.getMessage());
    }

    @Test
    public void testAllSongListConsumer() {
        SongListConsumer songListConsumer = new SongListConsumer();
        List<SongListConsumer> songList = Collections.singletonList(songListConsumer);

        when(songListConsumerMapper.selectList(null)).thenReturn(songList);

        R result = songListConsumerService.allSongListConsumer();

        assertEquals(200, result.getCode());
        assertEquals(songList, result.getData());
    }

    @Test
    public void testLikeTitle() {
        SongListConsumer songListConsumer = new SongListConsumer();
        List<SongListConsumer> songList = Collections.singletonList(songListConsumer);

        when(songListConsumerMapper.selectList(any(QueryWrapper.class))).thenReturn(songList);

        R result = songListConsumerService.likeTitle("Title");

        assertEquals(200, result.getCode());
        assertEquals(songList, result.getData());
    }

    @Test
    public void testLikeStyle() {
        SongListConsumer songListConsumer = new SongListConsumer();
        List<SongListConsumer> songList = Collections.singletonList(songListConsumer);

        when(songListConsumerMapper.selectList(any(QueryWrapper.class))).thenReturn(songList);

        R result = songListConsumerService.likeStyle("Style");

        assertEquals(200, result.getCode());
        assertEquals(songList, result.getData());
    }

    @Test
    public void testById_Success() {
        SongListConsumer songListConsumer = new SongListConsumer();
        List<SongListConsumer> songList = Collections.singletonList(songListConsumer);

        when(songListConsumerMapper.selectList(any(QueryWrapper.class))).thenReturn(songList);

        R result = songListConsumerService.byId(1);

        assertEquals(200, result.getCode());
        assertEquals(songList, result.getData());
    }

    @Test
    public void testById_Failure() {
        when(songListConsumerMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.emptyList());

        R result = songListConsumerService.byId(1);

        assertEquals(200, result.getCode());
        assertEquals("没有找到对象", result.getMessage());
    }

    @Test
    public void testEqUserId() {
        SongListConsumer songListConsumer = new SongListConsumer();
        List<SongListConsumer> songList = Collections.singletonList(songListConsumer);

        when(songListConsumerMapper.selectList(any(QueryWrapper.class))).thenReturn(songList);

        R result = songListConsumerService.eqUserId(1);

        assertEquals(200, result.getCode());
        assertEquals(songList, result.getData());
    }
}
