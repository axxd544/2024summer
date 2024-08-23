package com.example.liu.service;

import com.example.liu.service.impl.SingerServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;

import com.example.liu.mapper.SingerMapper;
import com.example.liu.model.domain.Singer;
import com.example.liu.model.request.SingerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class SingerServiceTest {

    @Mock
    private SingerMapper singerMapper;

    @InjectMocks
    private SingerServiceImpl singerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateSingerMsg_Success() {
        SingerRequest request = new SingerRequest();
        request.setId(1);
        request.setName("New Name");

        Singer singer = new Singer();
        singer.setId(1);
        singer.setName("New Name");

        doReturn(1).when(singerMapper).updateById(any(Singer.class));

        R result = singerService.updateSingerMsg(request);

        assertEquals(200, result.getCode());
        assertEquals("修改成功", result.getMessage());
        verify(singerMapper).updateById(any(Singer.class));
    }

    @Test
    public void testUpdateSingerMsg_Failure() {
        SingerRequest request = new SingerRequest();
        request.setId(1);
        request.setName("New Name");

        doReturn(0).when(singerMapper).updateById(any(Singer.class));

        R result = singerService.updateSingerMsg(request);

        assertEquals(200, result.getCode());
        assertEquals("修改失败", result.getMessage());
    }


    @Test
    public void testDeleteSinger_Success() {
        doReturn(1).when(singerMapper).deleteById(any(Integer.class));

        R result = singerService.deleteSinger(1);

        assertEquals(200, result.getCode());
        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteSinger_Failure() {
        doReturn(0).when(singerMapper).deleteById(any(Integer.class));

        R result = singerService.deleteSinger(1);

        assertEquals(200, result.getCode());
        assertEquals("删除失败", result.getMessage());
    }

    @Test
    public void testAllSinger() {
        doReturn(null).when(singerMapper).selectList(null);

        R result = singerService.allSinger();

        assertEquals(200, result.getCode());
        assertEquals(null, result.getData());
    }

    @Test
    public void testAddSinger_Success() {
        SingerRequest request = new SingerRequest();
        request.setName("New Singer");

        Singer singer = new Singer();
        singer.setName("New Singer");
        singer.setPic("/img/avatorImages/user.jpg");

        doReturn(1).when(singerMapper).insert(any(Singer.class));

        R result = singerService.addSinger(request);

        assertEquals(200, result.getCode());
        assertEquals("添加成功", result.getMessage());
    }

    @Test
    public void testAddSinger_Failure() {
        SingerRequest request = new SingerRequest();
        request.setName("New Singer");

        doReturn(0).when(singerMapper).insert(any(Singer.class));

        R result = singerService.addSinger(request);

        assertEquals(200, result.getCode());
        assertEquals("添加失败", result.getMessage());
    }

    @Test
    public void testSingerOfName() {
        String name = "John";
        doReturn(null).when(singerMapper).selectList(any(QueryWrapper.class));

        R result = singerService.singerOfName(name);

        assertEquals(200, result.getCode());
        assertEquals(null, result.getData());
    }

    @Test
    public void testSingerOfSex() {
        Integer sex = 1;
        doReturn(null).when(singerMapper).selectList(any(QueryWrapper.class));

        R result = singerService.singerOfSex(sex);

        assertEquals(200, result.getCode());
        assertEquals(null, result.getData());
    }
}
