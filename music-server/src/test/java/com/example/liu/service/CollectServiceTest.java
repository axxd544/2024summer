package com.example.liu.service;
import com.example.liu.service.impl.*;

import com.example.liu.mapper.CollectMapper;
import com.example.liu.model.domain.Collect;
import com.example.liu.model.request.CollectRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;

import java.util.ArrayList;
import java.util.List;

public class CollectServiceTest {

    @Mock
    private CollectMapper collectMapper;

    @InjectMocks
    private CollectServiceImpl collectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddCollection_Failure() {
        CollectRequest request = new CollectRequest();
        request.setUserId(1);
        request.setSongId(100);
        Collect collect = new Collect();
        doReturn(0).when(collectMapper).insert(collect);

        R result = collectService.addCollection(request);

        assertEquals("收藏失败", result.getMessage());
    }



    @Test
    public void testExistSongId_NotExists() {
        CollectRequest request = new CollectRequest();
        request.setUserId(1);
        request.setSongId(100);

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        doReturn(0L).when(collectMapper).selectCount(queryWrapper);

        R result = collectService.existSongId(request);

        assertEquals("未收藏", result.getMessage());
        assertEquals(false, result.getData());
    }



    @Test
    public void testDeleteCollect_Failure() {
        Integer userId = 1;
        Integer songId = 100;

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        doReturn(0).when(collectMapper).delete(queryWrapper);

        R result = collectService.deleteCollect(userId, songId);

        assertEquals("取消收藏失败", result.getMessage());
    }


}
