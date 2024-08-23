package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.controller.MinioUploadController;
import com.example.liu.mapper.SongListMapper;
import com.example.liu.model.domain.SongList;
import com.example.liu.model.request.SongListRequest;
import com.example.liu.service.impl.SongListServiceImpl;
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

public class SongListServiceTest {

    @InjectMocks
    private SongListServiceImpl songListService;

    @Mock
    private SongListMapper songListMapper;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private MinioUploadController minioUploadController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testUpdateSongListMsg_Failure() {
        SongListRequest request = new SongListRequest();

        when(songListMapper.updateById(any(SongList.class))).thenReturn(0);

        R result = songListService.updateSongListMsg(request);

        assertEquals(200, result.getCode());
        assertEquals("修改失败", result.getMessage());
    }

    @Test
    public void testDeleteSongList_Success() {
        when(songListMapper.deleteById(anyInt())).thenReturn(1);

        R result = songListService.deleteSongList(1);

        assertEquals(200, result.getCode());
        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteSongList_Failure() {
        when(songListMapper.deleteById(anyInt())).thenReturn(0);

        R result = songListService.deleteSongList(1);

        assertEquals(200, result.getCode());
        assertEquals("删除失败", result.getMessage());
    }

    @Test
    public void testAddSongList_Success() {
        SongListRequest request = new SongListRequest();
        SongList songList = new SongList();
        songList.setPic("/img/songListPic/123.jpg");

        when(songListMapper.insert(any(SongList.class))).thenReturn(1);

        R result = songListService.addSongList(request);

        assertEquals(200, result.getCode());
        assertEquals("添加成功", result.getMessage());
    }

    @Test
    public void testAddSongList_Failure() {
        SongListRequest request = new SongListRequest();

        when(songListMapper.insert(any(SongList.class))).thenReturn(0);

        R result = songListService.addSongList(request);

        assertEquals(200, result.getCode());
        assertEquals("添加失败", result.getMessage());
    }

    @Test
    public void testUpdateSongListImg_Success() {
        when(multipartFile.getOriginalFilename()).thenReturn("img.jpg");
      
        when(songListMapper.updateById(any(SongList.class))).thenReturn(1);
    }

    @Test
    public void testUpdateSongListImg_Failure() {
        when(songListMapper.updateById(any(SongList.class))).thenReturn(0);
    }

    @Test
    public void testAllSongList() {
        SongList songList = new SongList();
        List<SongList> songLists = Collections.singletonList(songList);

        when(songListMapper.selectList(null)).thenReturn(songLists);

        R result = songListService.allSongList();

        assertEquals(200, result.getCode());
        assertEquals(songLists, result.getData());
    }

    @Test
    public void testFindAllSong() {
        SongList songList = new SongList();
        List<SongList> songLists = Collections.singletonList(songList);

        when(songListMapper.selectList(null)).thenReturn(songLists);

        List<SongList> result = songListService.findAllSong();

        assertEquals(songLists, result);
    }

    @Test
    public void testLikeTitle() {
        SongList songList = new SongList();
        List<SongList> songLists = Collections.singletonList(songList);

        when(songListMapper.selectList(any(QueryWrapper.class))).thenReturn(songLists);

        R result = songListService.likeTitle("Title");

        assertEquals(200, result.getCode());
        assertEquals(songLists, result.getData());
    }

    @Test
    public void testLikeStyle() {
        SongList songList = new SongList();
        List<SongList> songLists = Collections.singletonList(songList);

        when(songListMapper.selectList(any(QueryWrapper.class))).thenReturn(songLists);

        R result = songListService.likeStyle("Style");

        assertEquals(200, result.getCode());
        assertEquals(songLists, result.getData());
    }
}

